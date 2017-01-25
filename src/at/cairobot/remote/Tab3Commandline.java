/*
 * 
 */
package at.cairobot.remote;

import at.cairobot.remote.support.JTextAreaOutputStream;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author redxef
 */
public class Tab3Commandline extends JPanel {

        private final JScrollPane sp;
        private final JTextArea ta;
        private final JTextField tf;
        private final JFrame parent;

        private final JTextAreaOutputStream taos;

        public Tab3Commandline(JFrame p)
        {
                super();
                parent = p;
                super.setLayout(new GridBagLayout());
                ta = new JTextArea(10, 40);
                ((DefaultCaret) ta.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                tf = new JTextField(40);
                tf.addActionListener((ActionEvent e) -> {
                        ((Main) parent).sendCommand(tf.getText());
                        tf.setText("");
                });

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                super.add(sp, gbc);
                gbc.gridy = 1;
                super.add(tf, gbc);

                taos = new JTextAreaOutputStream(ta);
        }

        public void reinitSize()
        {
        }

        public JTextAreaOutputStream getTAOS()
        {
                return taos;
        }
}
