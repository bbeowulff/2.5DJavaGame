package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Rum extends SuperObject{
	
	GamePanel gp;

	public OBJ_Rum(GamePanel gp) {
		
		this.gp = gp;
		
		name = "Rum";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Rum.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
