package main;

import object.OBJ_Chest;

import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Rum;
import entity.NPC_OldMan;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 54 * gp.tileSize;
		gp.obj[0].worldY = 24 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 43 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key(gp);
		gp.obj[2].worldX = 36 * gp.tileSize;
		gp.obj[2].worldY = 56 * gp.tileSize;
		
		
		gp.obj[3] = new OBJ_Door(gp);
		gp.obj[3].worldX = 17 * gp.tileSize;
		gp.obj[3].worldY = 14 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door(gp);
		gp.obj[4].worldX = 17 * gp.tileSize;
		gp.obj[4].worldY = 49 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Door(gp);
		gp.obj[5].worldX = 37 * gp.tileSize;
		gp.obj[5].worldY = 16 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Chest(gp);
		gp.obj[6].worldX = 17 * gp.tileSize;
		gp.obj[6].worldY = 12 * gp.tileSize;
		
		gp.obj[7] = new OBJ_Chest(gp);
		gp.obj[7].worldX = 17 * gp.tileSize;
		gp.obj[7].worldY = 47 * gp.tileSize;
		
		gp.obj[8] = new OBJ_Chest(gp);
		gp.obj[8].worldX = 37 * gp.tileSize;
		gp.obj[8].worldY = 14 * gp.tileSize;
		
		gp.obj[9] = new OBJ_Rum(gp);
		gp.obj[9].worldX = 51 * gp.tileSize;
		gp.obj[9].worldY = 51 * gp.tileSize;
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize*30;
		gp.npc[0].worldY = gp.tileSize*29;


		
		
	}
}
