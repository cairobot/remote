/*
 * 
 */
package at.cairobot.remote.support;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author redxef
 */
public abstract class ButtonModelClickListener implements ChangeListener {

        private boolean lpressed = false;
        
        public ButtonModelClickListener(JButton btn) {
                if (btn != null)
                        lpressed = btn.getModel().isPressed();
        }
        
        @Override
        public void stateChanged(ChangeEvent e)
        {
                ButtonModel btnmodl = (ButtonModel) e.getSource();
                if (btnmodl.isPressed() && !lpressed) {
                        btnPressed(e);
                } else if (!btnmodl.isPressed() && lpressed) {
                        btnReleased(e);
                }
                lpressed = btnmodl.isPressed();
        }
        
        public abstract void btnPressed(ChangeEvent e);
        public abstract void btnReleased(ChangeEvent e);
}
