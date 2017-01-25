/*
 * 
 */
package at.cairobot.remote;

import at.cairobot.remote.support.InjectInputStream;
import at.cairobot.remote.support.PfuschScanner;
import at.cairobot.remote.support.PopupIcon;
import at.cairobot.remote.support.SplitOutputStream;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author redxef
 */
public class Main extends JFrame {

        abstract class SockReadThread extends Thread {

                private final Socket s;
                private final InjectInputStream iis;
                private final PfuschScanner sc;
                private boolean running;
                private boolean is_fin;

                public SockReadThread(Socket s) throws IOException // doesnt actually throw, just to make java happy
                {
                        boolean err = false;
                        this.s = s;
                        running = false;
                        is_fin = true;
                        try {
                                this.s.getInputStream();
                        } catch (IOException ex) {
                                err = true;
                                ex.printStackTrace(System.err);
                        }
                        if (!err) {
                                iis = new InjectInputStream(this.s.getInputStream());
                        } else {
                                iis = new InjectInputStream(null);
                        }
                        sc = new PfuschScanner(iis);
                }

                @Override
                public void run()
                {
                        String line;
                        while (running) {
                                if ((line = sc.nextLine_()) != null) {
                                        doWrite(line);
                                }
                        }
                        is_fin = true;
                }

                public abstract void doWrite(String s);

                @Override
                public void start()
                {
                        running = true;
                        is_fin = false;
                        super.start();
                }

                public void terminate()
                {
                        running = false;
                        try {
                                Thread.sleep(0, 500);
                        } catch (InterruptedException ex) {
                                ex.printStackTrace(System.err);
                        }
                        if (!is_fin) {
                                iis.inject(new int[]{0x0d, 0x0a}, 0, 2);
                        }
                        while (!is_fin) {
                                try {
                                        Thread.sleep(0, 500);
                                } catch (InterruptedException ex) {
                                        ex.printStackTrace(System.err);
                                }
                        }
                }
        }

        private final JTabbedPane tp;
        private final Tab0Connect tab0;
        private final Tab1Remote tab1;
        private final Tab2Manual tab2;
        private final Tab3Commandline tab3;

        private Socket sock;
        private final PrintStream taos;
        private final SplitOutputStream sock_out;
        private SockReadThread sock_rd_thrd;

        private final PopupIcon pi;
        
        public Main(boolean set_os)
        {
                tab0 = new Tab0Connect(this);
                tab1 = new Tab1Remote(this);
                tab2 = new Tab2Manual(this);
                tab3 = new Tab3Commandline(this);
                
                pi = new PopupIcon();
                
                tp = new JTabbedPane();
                tp.addTab("Connect", tab0);
                tp.addTab("Remote", tab1);
                tp.addTab("Manual", tab2);
                tp.addTab("Command Line", pi, tab3);
                tp.addChangeListener((ChangeEvent e) -> {
                        if (((JTabbedPane)e.getSource()).getSelectedIndex() == 3) {
                                pi.setAlert(false);
                        }
                });
                super.getContentPane().add(tp);

                super.pack();
                super.setMinimumSize(super.getSize());
                super.setMaximumSize(super.getSize());
                super.setPreferredSize(super.getSize());
                super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tab0.requestFocus();

                sock = null;
                taos = new PrintStream(tab3.getTAOS());
                sock_out = new SplitOutputStream();
                sock_out.addOs(taos);

                tab0.reinitSize();
                tab1.reinitSize();
                tab2.reinitSize();
                tab3.reinitSize();

        }

        public void setSocket(String s_)
        {
                String[] vals = s_.split(":");
                Socket s;

                if (vals.length != 2) {
                        taos.println("Invalid address format");
                        return;
                } else {
                        try {
                                Integer.parseInt(vals[1]);
                        } catch (NumberFormatException ex) {
                                taos.println("Second argument must be a (port)number");
                                return;
                        }
                }

                try {
                        s = new Socket(vals[0], Integer.parseInt(vals[1]));
                } catch (IOException ex) {
                        s = null;
                }

                if (s == null) {
                        taos.println("Failed to open connection");
                        return;
                }

                if (sock != null) {
                        if (sock_rd_thrd != null) {
                                sock_rd_thrd.terminate();
                                sock_rd_thrd = null;
                        }
                        try {
                                sock_out.removeOs(sock.getOutputStream());
                                sock.getOutputStream().close();
                        } catch (IOException ex) {
                                ex.printStackTrace(System.err);
                        }
                        try {
                                sock.close();
                        } catch (IOException ex) {
                                ex.printStackTrace(System.err);
                        }
                        sock = null;
                        try {
                                Thread.sleep(100);
                        } catch (InterruptedException ex) {
                                ex.printStackTrace(System.err);
                        }
                }

                sock = s;
                try {
                        sock_out.addOs(sock.getOutputStream());
                } catch (IOException ex) {
                        ex.printStackTrace(System.err);
                }

                try {
                        sock_rd_thrd = new SockReadThread(sock) {

                                byte[] NEWLINE_WRITE = new byte[]{'\r', '\n'};

                                @Override
                                public void doWrite(String s)
                                {
                                        taos.println(s);
                                        pi.setAlert(true);
                                        tp.repaint();
                                }
                        };
                        sock_rd_thrd.start();
                } catch (IOException ex) {
                        ex.printStackTrace(System.err);
                }
                
                taos.println("Opened connection");
        }

        public void sendCommand(String s)
        {
                taos.print('>');
                if (sock != null) {
                        byte[] bs = s.getBytes(Charset.forName("UTF-8"));
                        try {
                                sock_out.write(bs, 0, bs.length);
                                sock_out.write(new byte[]{0x0d, 0x0a}, 0, 2);
                                sock_out.flush();
                        } catch (SocketException ex) {
                                taos.println();
                                taos.println("lost connection");
                        } catch (IOException ex) {
                                ex.printStackTrace(System.err);
                        }
                }
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args)
        {
                java.awt.EventQueue.invokeLater(() -> {
                        new Main(true).setVisible(true);
                });
        }

}
