package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;


public class Player extends Entity{
	

	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
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
		worldX=gp.tileSize * 31;
		worldY=gp.tileSize * 29;
		speed=4;
		direction = "down";
		
		//PLAYER STATUS
		maxLife = 6;
		life = maxLife;
	}	
	public void getPlayerImage() {
		//FIGHTER
		fighter_up1_idle = setup("/Player1/fighter_idle1_back");
		fighter_up2_idle = setup("/Player1/fighter_idle2_back");
		fighter_up1 = setup("/Player1/fighter_back1");
		fighter_up2 = setup("/Player1/fighter_back2");
		fighter_down1_idle = setup("/Player1/fighter_idle1_front");
		fighter_down2_idle = setup("/Player1/fighter_idle2_front");
		fighter_down1 = setup("/Player1/fighter_front1");
		fighter_down2 = setup("/Player1/fighter_front2");
		fighter_left1_idle = setup("/Player1/fighter_idle1_left");
		fighter_left2_idle = setup("/Player1/fighter_idle2_left");
		fighter_left1 = setup("/Player1/fighter_left1");
		fighter_left2 = setup("/Player1/fighter_left2");
		fighter_right1_idle = setup("/Player1/fighter_idle1_right");
		fighter_right2_idle = setup("/Player1/fighter_idle2_right");
		fighter_right1 = setup("/Player1/fighter_right1");
		fighter_right2 = setup("/Player1/fighter_right2");
		//SORCERER
		up1_idle = setup("/Player1/back_idle1");
		up2_idle = setup("/Player1/back_idle2");
		up1 = setup("/Player1/back_run1");
		up2 = setup("/Player1/back_run2");
		down1_idle = setup("/Player1/front_idle1");
		down2_idle = setup("/Player1/front_idle2");
		down1 = setup("/Player1/front_run1");
		down2 = setup("/Player1/front_run2");
		left1_idle = setup("/Player1/left_idle1");
		left2_idle = setup("/Player1/left_idle2");
		left1 = setup("/Player1/left_run1");
		left2 = setup("/Player1/left_run2");
		right1_idle = setup("/Player1/right_idle1");
		right2_idle = setup("/Player1/right_idle2");
		right1 = setup("/Player1/right_run1");
		right2 = setup("/Player1/right_run2");
		
		//THIEF
		thief_up1_idle = setup("/Player1/thief_idle1_back");
		thief_up2_idle = setup("/Player1/thief_idle2_back");
		thief_up1 = setup("/Player1/thief_back1");
		thief_up2 = setup("/Player1/thief_back2");
		thief_down1_idle = setup("/Player1/thief_idle1_front");
		thief_down2_idle = setup("/Player1/thief_idle2_front");
		thief_down1 = setup("/Player1/thief_front1");
		thief_down2 = setup("/Player1/thief_front2");
		thief_left1_idle = setup("/Player1/thief_idle1_left");
		thief_left2_idle = setup("/Player1/thief_idle2_left");
		thief_left1 = setup("/Player1/thief_left1");
		thief_left2 = setup("/Player1/thief_left2");
		thief_right1_idle = setup("/Player1/thief_idle1_right");
		thief_right2_idle = setup("/Player1/thief_idle2_right");
		thief_right1 = setup("/Player1/thief_right1");
		thief_right2 = setup("/Player1/thief_right2");

		



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
		
		// CHECK NPC COLLISION
		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
		// CHECK EVENT
		gp.eHandler.checkEvent();
		gp.keyH.enterPressed = false;
		
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
				speed -=2;
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
	
	public void interactNPC(int i) {
		
		if(i != 999) {

			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		if(keyH.downPressed == true || keyH.upPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
		{
		if(gp.ui.commandNum == 2)
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
		}
		else if(gp.ui.commandNum == 1)
		{
			switch(direction) {
			case "up":
				if(spriteNum == 1)
				{
					image = thief_up1;
				}
				if(spriteNum == 2) 
				{
					image = thief_up2;
				}
				break;
			case "down":
				if(spriteNum == 1)
				{
					image = thief_down1;
				}
				if(spriteNum == 2) 
				{
					image = thief_down2;
				}
				break;
			case "left":
				if(spriteNum == 1)
				{
					image = thief_left1;
				}
				if(spriteNum == 2) 
				{
					image = thief_left2;
				}
				break;
			case "right":
				if(spriteNum == 1)
				{
					image = thief_right1;
				}
				if(spriteNum == 2) 
				{
					image = thief_right2;
				}
				break;
			}
		}
		else if(gp.ui.commandNum == 0)
		{
			switch(direction) {
			case "up":
				if(spriteNum == 1)
				{
					image = fighter_up1;
				}
				if(spriteNum == 2) 
				{
					image = fighter_up2;
				}
				break;
			case "down":
				if(spriteNum == 1)
				{
					image = fighter_down1;
				}
				if(spriteNum == 2) 
				{
					image = fighter_down2;
				}
				break;
			case "left":
				if(spriteNum == 1)
				{
					image = fighter_left1;
				}
				if(spriteNum == 2) 
				{
					image = fighter_left2;
				}
				break;
			case "right":
				if(spriteNum == 1)
				{
					image = fighter_right1;
				}
				if(spriteNum == 2) 
				{
					image = fighter_right2;
				}
				break;
			}
		}
		
		g2.drawImage(image, screenX , screenY,  gp.tileSize,  gp.tileSize, null);
		}else
		{
			if(gp.ui.commandNum == 2)
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
			}
			else if(gp.ui.commandNum == 1)
			{
				switch(direction) {
				case "up":
					if(spriteNum == 1)
					{
						image = thief_up1_idle;
					}
					if(spriteNum == 2)
					{
						image = thief_up2_idle;
					}
					break;
				case "down":
					if(spriteNum == 1)
					{
						image = thief_down1_idle;
					}
					if(spriteNum == 2)
					{
						image = thief_down2_idle;
					}
					break;
				case "left":
					if(spriteNum == 1)
					{
						image = thief_left1_idle;
					}
					if(spriteNum == 2)
					{
						image = thief_left2_idle;
					}
					break;
				case "right":
					if(spriteNum == 1)
					{
						image = thief_right1_idle;
					}
					if(spriteNum == 2)
					{
						image = thief_right2_idle;
					}
					break;
				}
			}
			else if(gp.ui.commandNum == 0)
			{
				switch(direction) {
				case "up":
					if(spriteNum == 2)
					{
						image = fighter_up1_idle;
					}
					if(spriteNum == 1)
					{
						image = fighter_up2_idle;
					}
					
					break;
				case "down":
					if(spriteNum == 1)
					{
						image = fighter_down1_idle;
					}
					if(spriteNum == 2)
					{
						image = fighter_down2_idle;
					}
					break;
				case "left":
					if(spriteNum == 1)
					{
						image = fighter_left1_idle;
					}
					if(spriteNum == 2)
					{
						image = fighter_left2_idle;
					}
					break;
				case "right":
					if(spriteNum == 1)
					{
						image = fighter_right1_idle;
					}
					if(spriteNum == 2)
					{
						image = fighter_right2_idle;
					}
					break;
				}
			}
			g2.drawImage(image, screenX , screenY, null);
		}
	}
	
}




