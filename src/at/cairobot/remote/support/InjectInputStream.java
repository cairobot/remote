/*
 * 
 */
package at.cairobot.remote.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author redxef
 */
public class InjectInputStream extends InputStream {

        private final InputStream is;
        private final List<int[]> data;
        private int pos;
        private Thread wait_thread;
        private boolean closed;
        public InjectInputStream(InputStream is)
        {
                this.is = is;
                data = new LinkedList<>();
                pos = 0;
                wait_thread = null;
                closed = false;
        }

        public void inject(int[] d, int offs, int len)
        {
                int[] n = new int[len];
                System.arraycopy(d, offs, n, 0, len);
                data.add(n);
                if (getWaitThread() != null) {
                        synchronized (getWaitThread()) {
                                getWaitThread().notify();
                        }
                }
        }

        private synchronized Thread getWaitThread()
        {
                return wait_thread;
        }

        private synchronized void setWaitThread(Thread t)
        {
                wait_thread = t;
        }

        private int read_() throws IOException
        {
                int ret;
                if (closed)
                        return -1;
                if (data.isEmpty()) {
                        setWaitThread(Thread.currentThread());
                        new Thread("read-helper-thread") {
                                @Override
                                public void run()
                                {
                                        try {
                                                inject(new int[]{is.read()}, 0, 1);
                                        } catch (IOException ex) {
                                        }
                                }
                        }.start();
                        try {
                                synchronized (Thread.currentThread()) {
                                        Thread.currentThread().wait();
                                }
                        } catch (InterruptedException ex) {
                                ex.printStackTrace(System.err);
                        }
                        return read_();
                } else if (pos == data.get(0).length) {
                        data.remove(0);
                        pos = 0;
                        return read_();
                } else {
                        ret = data.get(0)[pos++];
                        return ret;
                }
        }

        @Override
        public int read() throws IOException
        {
                int r = read_();
                return r;
        }
        
        @Override
        public int available() throws IOException {
                if (closed)
                        return 0;
                int val = 0;
                val += is.available();
                val = data.stream().map((a) -> a.length).reduce(val, Integer::sum);
                return val;
        }
        
        @Override
        public void close() throws IOException {
                is.close();
                closed = true;
        }

}
