package bestris.bcarlson;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class BlockSkins {
	private String currentSkin;
	
	public BlockSkins() {
		currentSkin = "art/default";
	}
	
	public BlockSkins(String skin) {
		currentSkin = "art/" + skin;
	}
	
	public void updateSkin(String skin) {
		currentSkin = skin;
	}
	
	private File getBlockSkin(char type) {
		switch (type) {
		case 'I':
			return new File(currentSkin + "/I.png");
		case 'J':
			return new File(currentSkin + "/J.png");
		case 'L':
			return new File(currentSkin + "/L.png");
		case 'O':
			return new File(currentSkin + "/O.png");
		case 'S':
			return new File(currentSkin + "/S.png");
		case 'T':
			return new File(currentSkin + "/T.png");
		case 'Z':
			return new File(currentSkin + "/Z.png");
		case 'C':
			return new File(currentSkin + "/C.png");
		case 'G':
			return new File(currentSkin + "/G.png");
		default:
			System.out.println("Error in BlockSkins.getBlockSkin():");
			System.out.println("\tNon-existent Block Type");
			return null;
		}
	}

	public Map<String, BufferedImage> getBlockToArt() {
		Map<String, BufferedImage> temp = new HashMap<>();
		try {
			temp.put("I", HelperMethods.resize(ImageIO.read(getBlockSkin('I')), 25, 25));
			temp.put("J", HelperMethods.resize(ImageIO.read(getBlockSkin('J')), 25, 25));
			temp.put("L", HelperMethods.resize(ImageIO.read(getBlockSkin('L')), 25, 25));
			temp.put("O", HelperMethods.resize(ImageIO.read(getBlockSkin('O')), 25, 25));
			temp.put("S", HelperMethods.resize(ImageIO.read(getBlockSkin('S')), 25, 25));
			temp.put("T", HelperMethods.resize(ImageIO.read(getBlockSkin('T')), 25, 25));
			temp.put("Z", HelperMethods.resize(ImageIO.read(getBlockSkin('Z')), 25, 25));
			temp.put("C", HelperMethods.resize(ImageIO.read(getBlockSkin('C')), 25, 25));
			temp.put("G", HelperMethods.resize(ImageIO.read(getBlockSkin('G')), 25, 25));
		} catch (IOException e) {
			System.out.println("Error in BlockSkins.getBlockToArt():");
			System.out.println("\tRequired art not found in skin folder");
			return null;
		}
		return Collections.unmodifiableMap(temp);
	}

}
