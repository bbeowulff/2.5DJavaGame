package entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX,worldY;
	public int speed;
	
	public BufferedImage up1_idle, up2_idle, up1,up2,
	down1_idle, down2_idle, down1, down2,
	left1_idle, left2_idle, left1, left2,
	right1_idle, right2_idle, right1, right2;
	
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum =1;
	
}
