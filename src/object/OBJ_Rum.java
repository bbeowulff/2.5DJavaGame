package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Rum extends SuperObject{

	public OBJ_Rum() {
		name = "Rum";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Rum.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
