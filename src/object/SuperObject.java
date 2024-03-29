package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {

	public BufferedImage image;
	public BufferedImage key1, key2, key3, key4, key5,key6, key7, key8, key9, key10, key11, drake;
	public String name;
	int KeyPaintCounter = 0;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX=0;
	public int solidAreaDefaultY = 0;
	
	UtilityTool uTool = new UtilityTool();
	
	public void draw(Graphics g2, GamePanel gp, int i) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize<  gp.player.worldY + gp.player.screenY){
			if(gp.obj[i].name != "Key")
			g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
			else {
				if(KeyPaintCounter<=8)
					g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
				else if(KeyPaintCounter<=16)
					g2.drawImage(key1,screenX,screenY,gp.tileSize,gp.tileSize,null);
				else if(KeyPaintCounter<=24)
					g2.drawImage(key2,screenX,screenY,gp.tileSize,gp.tileSize,null);
				else if(KeyPaintCounter<=32)
					g2.drawImage(key3,screenX,screenY,gp.tileSize,gp.tileSize,null);
				else if(KeyPaintCounter<=40)
					g2.drawImage(key4,screenX,screenY,gp.tileSize,gp.tileSize,null);
				else if(KeyPaintCounter<=48)
					g2.drawImage(key5,screenX,screenY,gp.tileSize,gp.tileSize,null);
				else if(KeyPaintCounter<=56)
					g2.drawImage(key6,screenX,screenY,gp.tileSize,gp.tileSize,null);
				else if(KeyPaintCounter<=64)
					g2.drawImage(key7,screenX,screenY,gp.tileSize,gp.tileSize,null);
				else if(KeyPaintCounter<=72)
					g2.drawImage(key8,screenX,screenY,gp.tileSize,gp.tileSize,null);
				else if(KeyPaintCounter<=80)
					g2.drawImage(key9,screenX,screenY,gp.tileSize,gp.tileSize,null);
				else if(KeyPaintCounter<=88)
					g2.drawImage(key10,screenX,screenY,gp.tileSize,gp.tileSize,null);
				else if(KeyPaintCounter<=96)
					g2.drawImage(key11,screenX,screenY,gp.tileSize,gp.tileSize,null);
				KeyPaintCounter++;
				if(KeyPaintCounter == 97)KeyPaintCounter=0;
			}
			}
	}
	
}
