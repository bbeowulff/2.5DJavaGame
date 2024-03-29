package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 -(gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x=12;
		solidArea.y=24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 24;
		solidArea.height = 24;
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX=gp.tileSize * 23;
		worldY=gp.tileSize * 21;
		speed=4;
		direction = "down";
	}	
	public void getPlayerImage() {
		try {
			up1_idle = ImageIO.read(getClass().getResourceAsStream("/Player1/back_idle1.png"));
			up2_idle = ImageIO.read(getClass().getResourceAsStream("/Player1/back_idle2.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/Player1/back_run1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/Player1/back_run2.png"));
			down1_idle = ImageIO.read(getClass().getResourceAsStream("/Player1/front_idle1.png"));
			down2_idle = ImageIO.read(getClass().getResourceAsStream("/Player1/front_idle2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/Player1/front_run1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/Player1/front_run2.png"));
			left1_idle = ImageIO.read(getClass().getResourceAsStream("/Player1/left_idle1.png"));
			left2_idle = ImageIO.read(getClass().getResourceAsStream("/Player1/left_idle2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/Player1/left_run1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/Player1/left_run2.png"));
			right1_idle = ImageIO.read(getClass().getResourceAsStream("/Player1/right_idle1.png"));
			right2_idle = ImageIO.read(getClass().getResourceAsStream("/Player1/right_idle2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/Player1/right_run1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/Player1/right_run2.png"));

		}catch(IOException e) {
		 e.printStackTrace();	
		}
	}
	public void update() {
		if(keyH.downPressed == true || keyH.upPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
		{
		if(keyH.upPressed == true)
		  {
			direction = "up";
		  }
		  else  if(keyH.downPressed == true)
		  {
			  direction = "down";
		  }
		  else  if(keyH.leftPressed == true)
		  {
			  direction = "left";
		  }
		  else  if(keyH.rightPressed == true)
		  {
			  direction = "right";
		  }
		
		//CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		//CHECK OBJECT COLLISION
		int objIndex = gp.cChecker.checkObject(this,true);
		pickUpObject(objIndex);
		//IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(collisionOn == false) {
			switch(direction) {
			case"up":
				worldY -= speed;
				break;
			case"down":
				worldY += speed;
				break;
			case"left":
				worldX -= speed;
				break;
			case"right":
				worldX += speed;
				break;
			}
		}
		}
		spriteCounter ++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum =2;
			}
			else if(spriteNum == 2)
			{
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	public void pickUpObject (int i) {
		if(i != 999) {
			String objectName = gp.obj[i].name;
			switch(objectName) {
			case "Key":
				gp.playSE(4);
				hasKey++;
				gp.obj[i]= null;
				gp.ui.showMEssage("You got a key");
				break;
			case "Door":
				if(hasKey >0 && gp.obj[i].collision== true)
				{
					gp.playSE(2);
					hasKey--;
					gp.ui.showMEssage("A door was opened");
					try {
						gp.obj[i].image = ImageIO.read(getClass().getResourceAsStream("/objects/Door_open.png"));
						gp.obj[i].collision= false;
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
				else if(gp.obj[i].collision== true){
					gp.ui.showMEssage("No keys");

				}
				System.out.println("Key: "+hasKey);
				break;
			case "Rum":
				gp.playSE(3);
				speed -= 2;
				gp.obj[i] = null;
				gp.ui.showMEssage("That got me dizzy");
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playMusic(5);
				break;
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		if(keyH.downPressed == true || keyH.upPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
		{
		switch(direction) {
		case "up":
			if(spriteNum == 1)
			{
				image = up1;
			}
			if(spriteNum == 2) 
			{
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1)
			{
				image = down1;
			}
			if(spriteNum == 2) 
			{
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1)
			{
				image = left1;
			}
			if(spriteNum == 2) 
			{
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1)
			{
				image = right1;
			}
			if(spriteNum == 2) 
			{
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX , screenY,  gp.tileSize,  gp.tileSize, null);
		}else
		{
			switch(direction) {
			case "up":
				if(spriteNum == 1)
				{
					image = up1_idle;
				}
				if(spriteNum == 2) 
				{
					image = up2_idle;
				}
				break;
			case "down":
				if(spriteNum == 1)
				{
					image = down1_idle;
				}
				if(spriteNum == 2) 
				{
					image = down2_idle;
				}
				break;
			case "left":
				if(spriteNum == 1)
				{
					image = left1_idle;
				}
				if(spriteNum == 2) 
				{
					image = left2_idle;
				}
				break;
			case "right":
				if(spriteNum == 1)
				{
					image = right1_idle;
				}
				if(spriteNum == 2) 
				{
					image = right2_idle;
				}
				break;
			}
			g2.drawImage(image, screenX , screenY,  gp.tileSize,  gp.tileSize, null);
		}
	}
	
}
