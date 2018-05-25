package bestris.bcarlson;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class HelperMethods {
	
	/**
	 * Used to resize an Image.
	 * Helps resize Blocks so that they fit on the screen.
	 * 
	 * @param img: BufferedImage to be resized.
	 * @param newW: New width of img.
	 * @param newH: New height of img.
	 * @return
	 */
	static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    } 
}
