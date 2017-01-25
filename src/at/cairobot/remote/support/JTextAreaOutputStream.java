/*
 * 
 */
package at.cairobot.remote.support;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

/**
 *
 * @author redxef
 */
public class JTextAreaOutputStream extends OutputStream {

        private final JTextArea ta;

        public JTextAreaOutputStream(JTextArea jta)
        {
                ta = jta;
        }

        @Override
        public void write(int b) throws IOException
        {
                ta.append(new String(new int[]{b}, 0, 1));
        }
}
