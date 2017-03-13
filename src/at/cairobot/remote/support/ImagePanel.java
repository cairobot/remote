/*
 * 
 */
package at.cairobot.remote.support;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author redxef
 */
public class ImagePanel extends JPanel {

        private BufferedImage image;
        private Image resized;

        public ImagePanel(InputStream f)
        {
                try {
                        image = ImageIO.read(f);
                } catch (IOException ex) {
                }
                
                resized = image;
                
                super.addComponentListener(new ComponentAdapter() {
                        
                        @Override
                        public void componentResized(ComponentEvent e) {
                                Dimension d = e.getComponent().getSize();
                                resized = image.getScaledInstance(d.width, d.height, Image.SCALE_DEFAULT);
                        }
                });
        }
        
        public ImagePanel(File f) throws FileNotFoundException {
                this(new FileInputStream(f));
        }
        
        public ImagePanel(URL u) throws URISyntaxException, FileNotFoundException {
                this(new File(u.toURI()));
        }
        
        public ImagePanel(String f) throws FileNotFoundException {
                this(new File(f));
        }

        @Override
        protected void paintComponent(Graphics g)
        {
                super.paintComponent(g);
                g.drawImage(resized, 0, 0, this);         
        }
}
