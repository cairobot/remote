/*
 * 
 */
package at.cairobot.remote;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author redxef
 */
public class Tab2Manual extends JPanel {

        private final JFrame parent;
        private final JButton fwtoggle;

        public Tab2Manual(JFrame p)
        {
                super();
                parent = p;

                fwtoggle = new JButton("start");
                fwtoggle.addActionListener((ActionEvent e) -> {
                        if (fwtoggle.getText().equals("start")) {
                                fwtoggle.setText("stop");
                                ((Main) parent).sendCommand("fwstart");
                        } else {
                                fwtoggle.setText("start");
                                ((Main) parent).sendCommand("fwstop");
                        }
                });
                super.setLayout(new GridLayout(3, 1));
                super.add(fwtoggle);
        }

        public void reinitSize()
        {
                fwtoggle.setFont(fwtoggle.getFont().deriveFont(0.4f * fwtoggle.getHeight()));
        }
}
