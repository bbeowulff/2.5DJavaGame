package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;



public class NPC_OldMan extends Entity {
	
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		// solid area npc
		solidArea = new Rectangle();
		solidArea.x=0;
		solidArea.y=0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 48;
		solidArea.height = 48;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		up1_idle = setup("/npc/back_idle");
		up2_idle = setup("/npc/back_idle1");
		up1 = setup("/npc/back_run1");
		up2 = setup("/npc/back_run2");
		down1_idle = setup("/npc/down_idle");
		down2_idle = setup("/npc/down_idle1");
		down1 = setup("/npc/down1");
		down2 = setup("/npc/down2");
		left1_idle = setup("/npc/left_idle");
		left2_idle = setup("/npc/left_idle1");
		left1 = setup("/npc/left1");
		left2 = setup("/npc/left2");
		right1_idle = setup("/npc/right_idle");
		right2_idle = setup("/npc/right_idle1");
		right1 = setup("/npc/right1");
		right2 = setup("/npc/right2");
	}
	
	public void setDialogue() {					// NPC dialogue
		
		dialogues[0] = "Leave me alone!";
		dialogues[1] = "Stop bothering me!";
		dialogues[2] = "BLYYYATTTT";
		dialogues[3] = "u mongoloid";



	}
	
	public void setAction() {
		
		actionLockCounter ++;
		
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;  //1-100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";		
			}
			
			actionLockCounter = 0;
		
		
		}
	}
	
	public void speak() {
		
		// Do this character specific stuff
		super.speak();

	}
}
	
