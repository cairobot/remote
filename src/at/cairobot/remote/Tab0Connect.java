/*
 * 
 */
package at.cairobot.remote;

import at.cairobot.remote.support.PromptJTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author redxef
 */
public class Tab0Connect extends JPanel {

        private final JFrame parent;
        private final JLabel info;
        private final JTextField loc;
        private final JPanel btn_pnl;
        private final JButton ok;
        private final JButton fill_in;

        public Tab0Connect(JFrame p)
        {
                super();
                parent = p;

                info = new JLabel("Please enter server location:");
                loc = new PromptJTextField("localhost:11111");
                ok = new JButton("OK");
                ok.addActionListener((ActionEvent e) -> {
                        ((Main) parent).setSocket(loc.getText());
                });
                fill_in = new JButton("Search...");
                fill_in.addActionListener((ActionEvent e) -> {
                        new Thread() {
                                @Override
                                public void run()
                                {
                                        loc.setEnabled(false);
                                        ok.setEnabled(false);
                                        try {
                                                byte[] buf = new byte[1024];
                                                DatagramPacket dp = new DatagramPacket(buf, 1024);
                                                try (DatagramSocket ds = new DatagramSocket(null)) {
                                                        ds.setSoTimeout(2000);
                                                        ds.setBroadcast(true);
                                                        ds.bind(new InetSocketAddress(11112));
                                                        ds.receive(dp);
                                                }
                                                String data = new String(dp.getData(), 0, dp.getLength(), "UTF-8");
                                                String addr = dp.getAddress().getHostAddress();
                                                if (data.startsWith("main_brain_super_server")) {
                                                        String port = data.substring(data.indexOf('>') + 1, data.indexOf('<'));
                                                        loc.setText(addr + ":" + port);
                                                }

                                        } catch (SocketException ex) {
                                                Logger.getLogger(Tab0Connect.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IOException ex) {
                                                Logger.getLogger(Tab0Connect.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        loc.setEnabled(true);
                                        ok.setEnabled(true);
                                }
                        }.start();
                });
                btn_pnl = new JPanel(new GridLayout(1, 2));
                super.setFocusable(true);
                super.setLayout(new GridLayout(3, 1));
                super.add(info);
                super.add(loc);
                btn_pnl.add(ok);
                btn_pnl.add(fill_in);
                super.add(btn_pnl);
        }

        public void reinitSize()
        {
                info.setFont(info.getFont().deriveFont(0.4f * info.getHeight()));
                loc.setFont(loc.getFont().deriveFont(0.4f * loc.getHeight()));
                ok.setFont(ok.getFont().deriveFont(0.4f * ok.getHeight()));
                fill_in.setFont(ok.getFont().deriveFont(0.4f * ok.getHeight()));
        }
}
