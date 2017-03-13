/*
 * 
 */
package at.cairobot.remote.support;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author redxef
 */
public class IntervalChecker {

        private final int i;
        private boolean lock;
        
        public IntervalChecker(int interval) {
                i = interval;
                lock = false;
        }
        
        public boolean intervalPassed() {
                if (lock)
                        return false;
                lock = true;
                new Thread() {
                        @Override
                        public void run() {
                                try {
                                        Thread.sleep(i);
                                } catch (InterruptedException ex) {
                                } finally {
                                }
                                lock = false;
                                System.out.println("unlocked");
                        }
                }.start();
                return true;
        }
}
