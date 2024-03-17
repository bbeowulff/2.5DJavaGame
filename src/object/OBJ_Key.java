package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {

	public OBJ_Key() {
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile000.png"));
			key1 = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile001.png"));
			key2 = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile002.png"));
			key3 = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile003.png"));
			key4 = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile004.png"));
			key5 = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile005.png"));
			key6 = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile006.png"));
			key7 = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile007.png"));
			key8 = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile008.png"));
			key9 = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile009.png"));
			key10 = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile010.png"));
			key11 = ImageIO.read(getClass().getResourceAsStream("/objects/key_tile011.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
