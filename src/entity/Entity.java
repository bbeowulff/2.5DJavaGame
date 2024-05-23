package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	GamePanel gp;
	public int worldX,worldY;
	public int speed;
	
	public BufferedImage up1_idle, up2_idle, up1,up2,
	down1_idle, down2_idle, down1, down2,
	left1_idle, left2_idle, left1, left2,
	right1_idle, right2_idle, right1, right2, 
	thief_up1_idle, thief_up2_idle,  thief_up1,thief_up2,
	thief_down1_idle, thief_down2_idle, thief_down1, thief_down2,
	thief_left1_idle, thief_left2_idle, thief_left1, thief_left2,
	thief_right1_idle,  thief_right2_idle,thief_right1, thief_right2,
	fighter_up1_idle, fighter_up2_idle,  fighter_up1,fighter_up2,
	fighter_down1_idle,fighter_down2_idle, fighter_down1, fighter_down2,
	fighter_left1_idle,fighter_left2_idle, fighter_left1, fighter_left2,
	fighter_right1_idle,fighter_right2_idle, fighter_right1, fighter_right2;
	
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum =1;
	public int npc_state = 0;
	public int npc_choice = 0;
	public Rectangle solidArea = new Rectangle(0, 0, 24, 24); //solid area default pentru toate elementele
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	//CHARACTER STATUS
	public int maxLife;
	public int life;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {}
	
	public void speak() {
		
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {					// NPC direction changer while speaking
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
		
	}
	
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		
		//IF COLLISION IS FALSE, NPC CAN MOVE
		if(collisionOn == false && npc_choice == 0) {
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
		
		spriteCounter ++;
		if(spriteCounter > 12) {
			if(npc_state == 14)
			{
				Random random = new Random();
				npc_choice = random.nextInt(2);
				npc_state = 0;
			}else	npc_state++;
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
	
	public void draw(Graphics2D g2) 
	{			//draw method npc
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize<  gp.player.worldY + gp.player.screenY &&
		   collisionOn == false) 
		{
			if(npc_choice == 1 && npc_state < 15) 
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
				g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
			}
			else 
			{
				switch(direction)
				{
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
				g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
			}
		}
		else
			{
				switch(direction) 
				{
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
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			 e.printStackTrace();	
			}
		return image;
	}
}
