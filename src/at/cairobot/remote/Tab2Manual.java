/*
 * 
 */
package at.cairobot.remote;

import at.cairobot.remote.support.IdJSlider;
import at.cairobot.remote.support.ImagePanel;
import at.cairobot.remote.support.IntervalChecker;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author redxef
 */
public class Tab2Manual extends JPanel {

        private final JFrame parent;
        private final JButton fwtoggle;

        private final JPanel pane_main;
        private final JPanel pane_left;
        private final JPanel pane_mid;
        private final JPanel pane_right;
        private final IdJSlider[] sliders;

        public Tab2Manual(JFrame p) 
        {
                super();
                parent = p;
                fwtoggle = new JButton("start");
//                fwtoggle.setMinimumSize(new Dimension(p.getMinimumSize().width, p.getMinimumSize().height/3));
//                fwtoggle.setPreferredSize(fwtoggle.getMinimumSize());
                fwtoggle.addActionListener((ActionEvent e) -> {
                        if (fwtoggle.getText().equals("start")) {
                                fwtoggle.setText("stop");
                                ((Main) parent).sendCommand("fwstart");
                        } else {
                                fwtoggle.setText("start");
                                ((Main) parent).sendCommand("fwstop");
                        }
                });

                pane_main = new JPanel(new GridLayout(1, 3));
                pane_left = new JPanel(new GridLayout(6, 1));
                pane_mid = new ImagePanel(Main.class.getResourceAsStream("resources/Spider.png"));
                pane_right = new JPanel(new GridLayout(6, 1));
                sliders = new IdJSlider[12];
                for (int i = 0; i < 12; i++) {
                        sliders[i] = new IdJSlider(0, 180, 90);
                        sliders[i].setPaintLabels(true);
                        sliders[i].setPaintTicks(true);
                        sliders[i].setMajorTickSpacing(180);
                        sliders[i].addChangeListener(new ChangeListener() {

                                IntervalChecker ic = new IntervalChecker(500);
                                boolean last;
                                
                                @Override
                                public void stateChanged(ChangeEvent e)
                                {
                                        IdJSlider s;
                                        StringBuilder sb = new StringBuilder("servo ");
                                        if (!(e.getSource() instanceof IdJSlider))
                                                return;
                                        s = (IdJSlider) e.getSource();
                                        if (s.getID() >= 0 && s.getID() <= 3) {
                                                sb.append('C');
                                                sb.append((char) ('A' + s.getID()));
                                        } else if (s.getID() >= 6 && s.getID() <= 9) {
                                                sb.append('B');
                                                sb.append((char) ('A' + s.getID() - 6 ));
                                        } else if (s.getID() >= 4 && s.getID() <= 5) {
                                                sb.append('A');
                                                sb.append((char) ('A' + s.getID() - 4));
                                        } else if (s.getID() >= 10 && s.getID() <= 11) {
                                                sb.append('A');
                                                sb.append((char) ('A' + s.getID() - 10 + 2));
                                        }
                                        sb.append(' ');
                                        sb.append(Integer.toString(s.getValue()));
                                        ((Main) parent).sendCommand(sb.toString());
                                        System.out.println(sb.toString());
                                }
                        });
                        sliders[i].setID(i);
                        if (i < 6) {
                                pane_left.add(sliders[i]);
                        } else {
                                pane_right.add(sliders[i]);
                        }

                }

                pane_main.add(pane_left);
                pane_main.add(pane_mid);
                pane_main.add(pane_right);

                super.setLayout(new BorderLayout());
                super.add(fwtoggle, BorderLayout.NORTH);
                super.add(pane_main, BorderLayout.SOUTH);
        }

        public void reinitSize()
        {
                fwtoggle.setFont(fwtoggle.getFont().deriveFont(0.4f * fwtoggle.getHeight()));
        }
}
