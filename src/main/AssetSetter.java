package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Rum;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 46 * gp.tileSize;
		gp.obj[0].worldY = 16 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = 15 * gp.tileSize;
		gp.obj[1].worldY = 35 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].worldX = 28 * gp.tileSize;
		gp.obj[2].worldY = 48 * gp.tileSize;
		
		
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 9 * gp.tileSize;
		gp.obj[3].worldY = 6 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 9 * gp.tileSize;
		gp.obj[4].worldY = 41 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Door();
		gp.obj[5].worldX = 29 * gp.tileSize;
		gp.obj[5].worldY = 8 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Chest();
		gp.obj[6].worldX = 9 * gp.tileSize;
		gp.obj[6].worldY = 4 * gp.tileSize;
		
		gp.obj[7] = new OBJ_Chest();
		gp.obj[7].worldX = 9 * gp.tileSize;
		gp.obj[7].worldY = 39 * gp.tileSize;
		
		gp.obj[8] = new OBJ_Chest();
		gp.obj[8].worldX = 29 * gp.tileSize;
		gp.obj[8].worldY = 6 * gp.tileSize;
		
		gp.obj[9] = new OBJ_Rum();
		gp.obj[9].worldX = 43 * gp.tileSize;
		gp.obj[9].worldY = 43 * gp.tileSize;
	}
}
