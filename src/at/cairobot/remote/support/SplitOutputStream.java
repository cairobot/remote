/*
 * 
 */
package at.cairobot.remote.support;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author redxef
 */
public class SplitOutputStream extends OutputStream {

        private final List<OutputStream> oss;

        public SplitOutputStream()
        {
                oss = new LinkedList<>();
        }

        public void addOs(OutputStream os)
        {
                oss.add(os);
        }

        public void removeAllOs()
        {
                oss.clear();
        }

        public void removeOs(OutputStream os)
        {
                oss.remove(os);
        }

        @Override
        public void write(int b) throws IOException
        {
                for (OutputStream os : oss) {
                        os.write(b);
                }
        }
        
        @Override
        public void close() throws IOException {
                for (OutputStream o: oss) {
                        o.close();
                }
        }
        
        @Override
        public void flush() throws IOException {
                for (OutputStream o: oss) {
                        o.flush();
                }
        }
}
