/*
 * 
 */
package at.cairobot.remote;

import at.cairobot.remote.support.IdJSlider;
import at.cairobot.remote.support.ImagePanel;
import at.cairobot.remote.support.IntervalChecker;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
//                        sliders[i].setPaintLabels(true);
//                        sliders[i].setPaintTicks(true);
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
                                        switch(s.getID()) {
                                                case 0: sb.append("AA"); break;
                                                case 1: sb.append("AB"); break;
                                                case 2: sb.append("BA"); break;
                                                case 3: sb.append("BB"); break;
                                                case 4: sb.append("BD"); break;
                                                case 5: sb.append("BC"); break;
                                                case 6: sb.append("AD"); break;
                                                case 7: sb.append("AC"); break;
                                                case 8: sb.append("CB"); break;
                                                case 9: sb.append("CA"); break;
                                                case 10: sb.append("CD"); break;
                                                case 11: sb.append("CC"); break;
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
