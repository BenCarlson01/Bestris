package bestris.bcarlson;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class UnknownClass extends JPanel{
	private BufferedImage block;
    private int yPos = 0;
    private int direction = 25;

	
	public UnknownClass() {
		try {
            block = ImageIO.read(new File("art/Purple Tetris Block.png"));
            block = resize(block, 25, 25);
            BufferedImage block2 = joinBufferedImage(block, block);
            block = joinBufferedImage(block2, block);
            Timer timer = new Timer(400, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    yPos += direction;
                    if (yPos + block.getHeight() > getHeight()) {
                        yPos = getHeight() - block.getHeight();
                        direction *= -1;
                    } else if (yPos < 0) {
                        yPos = 0;
                        direction *= -1;
                    }
                    repaint();
                }

            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.start();
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
	}
	
	@Override
    public Dimension getPreferredSize() {
        return block == null ? super.getPreferredSize() : new Dimension(block.getWidth(), block.getHeight() * 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = getWidth() - block.getWidth();
        g.drawImage(block, x, yPos, this);

    }
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }  
    
    public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {
        //do some calculate first
        int offset  = 0;
        int wid = img1.getWidth() + img2.getWidth() + offset;
        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid, height, BufferedImage.TYPE_INT_ARGB) ;
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, wid, height);
        //draw image
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        g2.dispose();
        return newImage;
    }
}
