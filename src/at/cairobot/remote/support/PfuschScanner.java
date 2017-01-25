/*
 * 
 */
package at.cairobot.remote.support;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author redxef
 */
public class PfuschScanner implements Closeable {

        private static final char LINE_END = '\n';
        private final InputStream is;
        private final StringBuilder buff;
        private String line;
        
        public PfuschScanner(InputStream is) {
                this.is = is;
                buff = new StringBuilder();
                line = null;
        }
        
        public boolean hasNext() {
                int av;
                try {
                        av = is.available();
                } catch (IOException ex) {
                        av = 0;
                }
                
                return av > 0;
        }
        
        public char next() {
                int n;
                try {
                        n = is.read();
                } catch (IOException ex) {
                        n = -1;
                }
                
                return (char) n;
        }
        
        public boolean hasNextLine() {
                char c;
                while (hasNext()) {
                        if ((c = next()) == LINE_END) {
                                line = buff.toString();
                                buff.delete(0, buff.length());
                                return true;
                        } else {
                                buff.append(c);
                        }
                }
                return false;
        }
        
        public String nextLine() {
                if (hasNextLine()) {
                        String lin = line;
                        line = null;
                        return lin;
                }
                return null;
        }
        
        public boolean hasNextLine_() {
                char c;
                while (line == null) {
                        c = next();
                        if ((c) == LINE_END) {
                                line = buff.toString();
                                buff.delete(0, buff.length());
                        } else {
                                buff.append(c);
                        }
                }
                return true;
        }
        
        public String nextLine_() {
                hasNextLine_();
                String lin = line;
                line = null;
                return lin;
        }
        
        @Override
        public void close() throws IOException
        {
                is.close();
        }
        
}
