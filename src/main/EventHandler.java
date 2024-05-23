package main;

import java.awt.Rectangle;
public class EventHandler {
	
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.width = 2;
		eventRect.height = 2;
		eventRectDefaultX = eventRect.x; 
		eventRectDefaultY = eventRect.y;
	}
	public void checkEvent() {
		
		if(hit(35, 33, "right") == true) {
			//event happens
			damagePit(gp.dialogueState);
		}
		if(hit(35, 33, "left") == true) {
			//event happens
			damagePit(gp.dialogueState);
		}
		if(hit(35, 33, "up") == true) {
			//event happens
			damagePit(gp.dialogueState);
		}
		if(hit(35, 33, "down") == true) {
			//event happens
			damagePit(gp.dialogueState);
		}
		
		if(hit(36, 34, "right") == true) {
			//event happens
			teleport(gp.dialogueState);
		}
		if(hit(36, 34, "left") == true) {
			//event happens
			teleport(gp.dialogueState);
		}
		if(hit(36, 34, "rup") == true) {
			//event happens
			teleport(gp.dialogueState);
		}
		if(hit(36, 34, "down") == true) {
			//event happens
			teleport(gp.dialogueState);
		}
		
//		if(hit(36, 36, "right") == true) {
//			healingPool(gp.dialogueState);
//		}
		
	}
	
	public boolean hit (int eventCol, int eventRow, String reqDirection) {
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect.x = eventCol*gp.tileSize + eventRect.x;
		eventRect.y = eventRow*gp.tileSize + eventRect.y;
		
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		
		return hit;
	}
	
	public void damagePit(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You fell into a pit!";
		gp.player.life -= 1;
		if(gp.player.life == 0) gp.ui.youdied=true;
	}
	
	public void teleport(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "AM AJUNS IN PAPUCESTI!?";
		gp.player.worldX = gp.tileSize*55;
		gp.player.worldY = gp.tileSize*25;
	}
	
	
//	public void healingPool(int gameState) {
//		
//		System.out.println("healing");
//		
//		if(gp.keyH.enterPressed == true) {
//			gp.gameState = gameState;
//			gp.ui.currentDialogue = "You drink the wather.\nYour life has been recovered";
//			gp.player.life = gp.player.maxLife;
//		}
//	}
}