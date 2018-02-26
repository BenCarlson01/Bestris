package bestris.bcarlson;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tetris extends JPanel{
	private BufferedImage boat;
    private int xPos = 0;
    private int direction = 1;

	
	public Tetris() {
		try {
            boat = ImageIO.read(new File("art/T-Block.png"));
            Timer timer = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    xPos += direction;
                    if (xPos + boat.getWidth() > getWidth()) {
                        xPos = getWidth() - boat.getWidth();
                        direction *= -1;
                    } else if (xPos < 0) {
                        xPos = 0;
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
        return boat == null ? super.getPreferredSize() : new Dimension(boat.getWidth() * 4, boat.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int y = getHeight() - boat.getHeight();
        g.drawImage(boat, xPos, y, this);

    }
}
