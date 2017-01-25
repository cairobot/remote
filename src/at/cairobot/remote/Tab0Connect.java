/*
 * 
 */
package at.cairobot.remote;

import at.cairobot.remote.support.PromptJTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
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
        private final JButton ok;

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
                super.setFocusable(true);
                super.setLayout(new GridLayout(3, 1));
                super.add(info);
                super.add(loc);
                super.add(ok);
        }
        
        public void reinitSize() {
                info.setFont(info.getFont().deriveFont(0.4f * info.getHeight()));
                loc.setFont(loc.getFont().deriveFont(0.4f * loc.getHeight()));
                ok.setFont(ok.getFont().deriveFont(0.4f * ok.getHeight()));
        }
}
