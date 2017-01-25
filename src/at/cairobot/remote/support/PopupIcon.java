/*
 * 
 */
package at.cairobot.remote.support;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

/**
 *
 * @author redxef
 */
public class PopupIcon implements Icon {

        private static final int HEIGHT = 12;
        private static final int WIDTH = 12;
        private static final Color NOT = Color.LIGHT_GRAY;
        private static final Color YES = Color.BLUE;
        
        private Color co;
        
        public PopupIcon() {
                co = NOT;
        }
        
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y)
        {
                g.setColor(co);
//                g.fillRect(x, y, WIDTH, HEIGHT);
                g.fillOval(x, y, WIDTH, HEIGHT);
        }

        @Override
        public int getIconWidth()
        {
                return WIDTH;
        }

        @Override
        public int getIconHeight()
        {
                return HEIGHT;
        }
        
        public void setAlert(boolean a) {
                if (a) {
                        co = YES;
                } else {
                        co = NOT;
                }
        }
}
