package bestris.bcarlson;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BlockPreview extends JPanel{
	private boolean sixbysix;
	private Map<String, BufferedImage> colorToBlock;
	private String[][] blocks;
	
	public BlockPreview() {
		setPreferredSize( new Dimension( 60, 60 ) );
		Map<String, BufferedImage> temp = new HashMap<>();
		try {
			temp.put("Z", HelperMethods.resize(ImageIO.read(new File("art/Red Tetris Block.png")), 12, 12));
			temp.put("I", HelperMethods.resize(ImageIO.read(new File("art/LBlue Tetris Block.png")), 10, 10));
			temp.put("J", HelperMethods.resize(ImageIO.read(new File("art/DBlue Tetris Block.png")), 12, 12));
			temp.put("T", HelperMethods.resize(ImageIO.read(new File("art/Purple Tetris Block.png")), 12, 12));
			temp.put("S", HelperMethods.resize(ImageIO.read(new File("art/Green Tetris Block.png")), 10, 10));
			temp.put("O", HelperMethods.resize(ImageIO.read(new File("art/Yellow Tetris Block.png")), 12, 12));
			temp.put("L", HelperMethods.resize(ImageIO.read(new File("art/Orange Tetris Block.png")), 12, 12));
			temp.put("C1", HelperMethods.resize(ImageIO.read(new File("art/C.png")), 12, 12));
			temp.put("C2", HelperMethods.resize(ImageIO.read(new File("art/C.png")), 10, 10));
		} catch (IOException e) {
			System.out.println("IOError");
			return;
		}
		colorToBlock = Collections.unmodifiableMap(temp);
	}
	
	public void displayBlock(char block) {
		sixbysix = false;
		switch (block) {
		case 'I':
			sixbysix = true;
			blocks = new String[6][6];
			for (int i = 0; i < blocks.length; i++) {
				for (int j = 0; j < blocks[i].length; j++) {
					blocks[i][j] = "C2";
				}
			}
			blocks[1][3] = "I";
			blocks[2][3] = "I";
			blocks[3][3] = "I";
			blocks[4][3] = "I";
			break;
		case 'J':
			blocks = new String[5][5];
			for (int i = 0; i < blocks.length; i++) {
				for (int j = 0; j < blocks[i].length; j++) {
					blocks[i][j] = "C1";
				}
			}
			blocks[1][1] = "J";
			blocks[1][2] = "J";
			blocks[2][2] = "J";
			blocks[3][2] = "J";
			break;
		case 'L':
			blocks = new String[5][5];
			for (int i = 0; i < blocks.length; i++) {
				for (int j = 0; j < blocks[i].length; j++) {
					blocks[i][j] = "C1";
				}
			}
			blocks[3][1] = "L";
			blocks[1][2] = "L";
			blocks[2][2] = "L";
			blocks[3][2] = "L";
			break;
		case 'O':
			sixbysix = true;
			blocks = new String[6][6];
			for (int i = 0; i < blocks.length; i++) {
				for (int j = 0; j < blocks[i].length; j++) {
					blocks[i][j] = "C2";
				}
			}
			blocks[2][2] = "O";
			blocks[2][3] = "O";
			blocks[3][2] = "O";
			blocks[3][3] = "O";
			break;
		case 'S':
			blocks = new String[5][5];
			for (int i = 0; i < blocks.length; i++) {
				for (int j = 0; j < blocks[i].length; j++) {
					blocks[i][j] = "C1";
				}
			}
			blocks[2][1] = "S";
			blocks[3][1] = "S";
			blocks[1][2] = "S";
			blocks[2][2] = "S";
			break;
		case 'T':
			blocks = new String[5][5];
			for (int i = 0; i < blocks.length; i++) {
				for (int j = 0; j < blocks[i].length; j++) {
					blocks[i][j] = "C1";
				}
			}
			blocks[2][1] = "T";
			blocks[1][2] = "T";
			blocks[2][2] = "T";
			blocks[3][2] = "T";
			break;
		case 'Z':
			blocks = new String[5][5];
			for (int i = 0; i < blocks.length; i++) {
				for (int j = 0; j < blocks[i].length; j++) {
					blocks[i][j] = "C1";
				}
			}
			blocks[1][1] = "Z";
			blocks[2][1] = "Z";
			blocks[2][2] = "Z";
			blocks[3][2] = "Z";
			break;
		default:
			System.out.println("Non-existant block type");
			return;
		}
		repaint();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (sixbysix) {
        	for (int i = 0; i < blocks.length; i++) {
            	for (int j = 0; j < blocks[i].length; j++) {
                    g.drawImage(colorToBlock.get(blocks[i][j]), i * 10, j * 10, this);
            	}
            }
        } else {
        	for (int i = 0; i < blocks.length; i++) {
            	for (int j = 0; j < blocks[i].length; j++) {
                    g.drawImage(colorToBlock.get(blocks[i][j]), i * 12, j * 12, this);
            	}
            }
        }
    }

}
