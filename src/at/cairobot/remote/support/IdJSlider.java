/*
 * 
 */
package at.cairobot.remote.support;

import javax.swing.JSlider;

/**
 *
 * @author redxef
 */
public class IdJSlider extends JSlider {

        private int ID = 0;

        public IdJSlider(int i, int i0, int i1)
        {
                super(i, i0, i1);
        }

        public void setID(int i)
        {
                ID = i;
        }

        public int getID()
        {
                return ID;
        }

}
