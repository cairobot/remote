/*
 * 
 */
package at.cairobot.remote;

import at.cairobot.remote.support.ButtonModelClickListener;
import at.cairobot.remote.support.Pair;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.LinkedList;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author redxef
 */
public class Tab1Remote extends JPanel {

        private static final char DIRECTION_CCW = 'q';
        private static final char DIRECTION_UP = 'w';
        private static final char DIRECTION_CW = 'e';
        private static final char DIRECTION_LEFT = 'a';
        private static final char DIRECTION_STOP = '\\';
        private static final char DIRECTION_RIGHT = 'd';
        private static final char DIRECTION_DOWN = 's';

        private static final String BUTTON_TEXT_CCW = "TURN LEFT";
        private static final String BUTTON_TEXT_UP = "UP";
        private static final String BUTTON_TEXT_CW = "TURN RIGHT";
        private static final String BUTTON_TEXT_LEFT = "LEFT";
        private static final String BUTTON_TEXT_STOP = "STOP";
        private static final String BUTTON_TEXT_RIGHT = "RIGHT";
        private static final String BUTTON_TEXT_DOWN = "DOWN";

        private static final String PRG_USE_CCW = "rleft";
        private static final String PRG_USE_UP = "lauf";
        private static final String PRG_USE_CW = "rright";
        private static final String PRG_USE_LEFT = "tleft";
        private static final String PRG_USE_STOP = "stand";
        private static final String PRG_USE_RIGHT = "tright";
        private static final String PRG_USE_DOWN = "back";
        
        
        private static final String TOOLTIP_BTNS = "Tip: Use WASD, QE and \\ for controls!";
        private final List<Pair<JButton, Character>> ll;

        private final JFrame parent;
        private final LayoutManager lm;
        private final LayoutManager lm0;
        private final JPanel cont0;

        private JButton act_ccw;
        private JButton act_up;
        private JButton act_cw;
        private JButton act_left;
        private JButton act_stop;
        private JButton act_right;
        private JButton act_down;

        private final KeyListener kl;

        private char selected;

        public Tab1Remote(JFrame p)
        {
                super();
                parent = p;

                kl = new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e)
                        {
                        }

                        @Override
                        public void keyPressed(KeyEvent e)
                        {
                                doActionKeyPressed(e);
                        }

                        @Override
                        public void keyReleased(KeyEvent e)
                        {
                                doActionKeyReleased(e);
                        }
                };

                lm = new BorderLayout();
                super.setLayout(lm);
                cont0 = new JPanel();
                lm0 = new GridLayout(3, 3);
                cont0.setLayout(lm0);

                try {
                        act_ccw = new JButton(BUTTON_TEXT_CCW, new ImageIcon(new URL("")));
                } catch (MalformedURLException ex) {
                        act_ccw = new JButton(BUTTON_TEXT_CCW);
                }
                try {
                        act_up = new JButton(BUTTON_TEXT_UP, new ImageIcon(new URL("")));
                } catch (MalformedURLException ex) {
                        act_up = new JButton(BUTTON_TEXT_UP);
                }
                try {
                        act_cw = new JButton(BUTTON_TEXT_CW, new ImageIcon(new URL("")));
                } catch (MalformedURLException ex) {
                        act_cw = new JButton(BUTTON_TEXT_CW);
                }
                try {
                        act_left = new JButton(BUTTON_TEXT_LEFT, new ImageIcon(new URL("")));
                } catch (MalformedURLException ex) {
                        act_left = new JButton(BUTTON_TEXT_LEFT);
                }
                try {
                        act_stop = new JButton(BUTTON_TEXT_STOP, new ImageIcon(new URL("")));
                } catch (MalformedURLException ex) {
                        act_stop = new JButton(BUTTON_TEXT_STOP);
                }
                try {
                        act_right = new JButton(BUTTON_TEXT_RIGHT, new ImageIcon(new URL("")));
                } catch (MalformedURLException ex) {
                        act_right = new JButton(BUTTON_TEXT_RIGHT);
                }
                try {
                        act_down = new JButton(BUTTON_TEXT_DOWN, new ImageIcon(new URL("")));
                } catch (MalformedURLException ex) {
                        act_down = new JButton(BUTTON_TEXT_DOWN);
                }

                act_ccw.setToolTipText(TOOLTIP_BTNS);
                act_up.setToolTipText(TOOLTIP_BTNS);
                act_cw.setToolTipText(TOOLTIP_BTNS);
                act_left.setToolTipText(TOOLTIP_BTNS);
                act_stop.setToolTipText(TOOLTIP_BTNS);
                act_right.setToolTipText(TOOLTIP_BTNS);
                act_down.setToolTipText(TOOLTIP_BTNS);

                //<editor-fold defaultstate="collapsed" desc="Add ChangeListener for Button Klick Events">
                act_ccw.getModel().addChangeListener(new ButtonModelClickListener(null) {
                        @Override
                        public void btnPressed(ChangeEvent e)
                        {
                                doActionPressed(e);
                        }

                        @Override
                        public void btnReleased(ChangeEvent e)
                        {
                                doActionReleased(e);
                        }
                });
                act_up.getModel().addChangeListener(new ButtonModelClickListener(null) {
                        @Override
                        public void btnPressed(ChangeEvent e)
                        {
                                doActionPressed(e);
                        }

                        @Override
                        public void btnReleased(ChangeEvent e)
                        {
                                doActionReleased(e);
                        }
                });
                act_cw.getModel().addChangeListener(new ButtonModelClickListener(null) {
                        @Override
                        public void btnPressed(ChangeEvent e)
                        {
                                doActionPressed(e);
                        }

                        @Override
                        public void btnReleased(ChangeEvent e)
                        {
                                doActionReleased(e);
                        }
                });
                act_left.getModel().addChangeListener(new ButtonModelClickListener(null) {
                        @Override
                        public void btnPressed(ChangeEvent e)
                        {
                                doActionPressed(e);
                        }

                        @Override
                        public void btnReleased(ChangeEvent e)
                        {
                                doActionReleased(e);
                        }
                });
                act_stop.getModel().addChangeListener(new ButtonModelClickListener(null) {
                        @Override
                        public void btnPressed(ChangeEvent e)
                        {
                                doActionPressed(e);
                        }

                        @Override
                        public void btnReleased(ChangeEvent e)
                        {
                                doActionReleased(e);
                        }
                });
                act_right.getModel().addChangeListener(new ButtonModelClickListener(null) {
                        @Override
                        public void btnPressed(ChangeEvent e)
                        {
                                doActionPressed(e);
                        }

                        @Override
                        public void btnReleased(ChangeEvent e)
                        {
                                doActionReleased(e);
                        }
                });
                act_down.getModel().addChangeListener(new ButtonModelClickListener(null) {
                        @Override
                        public void btnPressed(ChangeEvent e)
                        {
                                doActionPressed(e);
                        }

                        @Override
                        public void btnReleased(ChangeEvent e)
                        {
                                doActionReleased(e);
                        }
                });
//</editor-fold>

                cont0.add(act_ccw);
                cont0.add(act_up);
                cont0.add(act_cw);
                cont0.add(act_left);
                cont0.add(act_stop);
                cont0.add(act_right);
                cont0.add(new JPanel());
                cont0.add(act_down);
                cont0.add(new JPanel());

                super.add(cont0);

                cont0.addKeyListener(kl);
                act_ccw.addKeyListener(kl);
                act_up.addKeyListener(kl);
                act_cw.addKeyListener(kl);
                act_left.addKeyListener(kl);
                act_stop.addKeyListener(kl);
                act_right.addKeyListener(kl);
                act_down.addKeyListener(kl);

                ll = new LinkedList<>();
                ll.add(new Pair<>(act_ccw, DIRECTION_CCW));
                ll.add(new Pair<>(act_up, DIRECTION_UP));
                ll.add(new Pair<>(act_cw, DIRECTION_CW));
                ll.add(new Pair<>(act_left, DIRECTION_LEFT));
                ll.add(new Pair<>(act_stop, DIRECTION_STOP));
                ll.add(new Pair<>(act_right, DIRECTION_RIGHT));
                ll.add(new Pair<>(act_down, DIRECTION_DOWN));

                selected = '\0';
        }
        
        public void reinitSize() {
                act_ccw.setFont(act_ccw.getFont().deriveFont(0.4f * act_ccw.getHeight()));
                act_up.setFont(act_up.getFont().deriveFont(0.4f * act_up.getHeight()));
                act_cw.setFont(act_cw.getFont().deriveFont(0.4f * act_cw.getHeight()));
                act_left.setFont(act_left.getFont().deriveFont(0.4f * act_left.getHeight()));
                act_stop.setFont(act_stop.getFont().deriveFont(0.4f * act_stop.getHeight()));
                act_right.setFont(act_right.getFont().deriveFont(0.4f * act_right.getHeight()));
                act_down.setFont(act_down.getFont().deriveFont(0.4f * act_down.getHeight()));
                
        }

        private char getButtonChar(JButton b)
        {
                for (Pair<JButton, Character> p : ll) {
                        if (p.getFirst() == b) {
                                return p.getSecond();
                        }
                }
                return '\0';
        }

        private char getButtonModelChar(ButtonModel bm)
        {
                for (Pair<JButton, Character> p : ll) {
                        if (p.getFirst().getModel() == bm) {
                                return p.getSecond();
                        }
                }
                return '\0';
        }

        private JButton getCharButton(char c)
        {
                for (Pair<JButton, Character> p : ll) {
                        if (p.getSecond() == c) {
                                return p.getFirst();
                        }
                }
                return null;
        }

        private void doActionKeyPressed(KeyEvent e)
        {
                JButton btn = getCharButton(e.getKeyChar());
                if (btn != null)
                        simulateButtonPress(btn);
        }

        private void doActionKeyReleased(KeyEvent e)
        {
                JButton btn = getCharButton(e.getKeyChar());
                if (btn != null)
                        simulateButtonRelease(btn);
        }

        private static void simulateButtonPress(JButton btn)
        {
                btn.getModel().setArmed(true);
                btn.getModel().setPressed(true);
                btn.paintImmediately(new Rectangle(0, 0, btn.getSize().width, btn.getSize().height));
        }

        private static void simulateButtonRelease(JButton btn)
        {
                btn.getModel().setPressed(false);
                btn.getModel().setArmed(false);
        }

        private void doActionPressed(ChangeEvent e)
        {
                char c = getButtonModelChar((ButtonModel) e.getSource());
                StringBuilder send = new StringBuilder();
                send.append("fwselect ");

                if (selected != '\0')
                        return;

                selected = c;
                switch (c) {
                        case 'q':
                                send.append(PRG_USE_CCW);
                                break;
                        case 'w':
                                send.append(PRG_USE_UP);
                                break;
                        case 'e':
                                send.append(PRG_USE_CW);
                                break;
                        case 'a':
                                send.append(PRG_USE_LEFT);
                                break;
                        case '\\':
                                send.append(PRG_USE_STOP);
                                break;
                        case 'd':
                                send.append(PRG_USE_RIGHT);
                                break;
                        case 's':
                                send.append(PRG_USE_DOWN);
                                break;
                        default:
                                send.append("idle");
                                break;
                }

                doSend(send.toString());
        }

        private void doActionReleased(ChangeEvent e)
        {
                char c = getButtonModelChar((ButtonModel) e.getSource());
                if (c == selected) {
                        doSend("fwselect " + PRG_USE_STOP);
                        selected = '\0';
                }
        }

        private void doSend(String s)
        {
                if (parent instanceof Main) {
                        ((Main) parent).sendCommand(s);
                        return;
                } else {
                        System.out.print("debug: ");
                }
                System.out.println(s);
        }
}
