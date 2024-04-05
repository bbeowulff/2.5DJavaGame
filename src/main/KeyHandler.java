package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	//debug
	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		// PLAY TEST
		if(gp.gameState == gp.playState) {
			
			if(code == KeyEvent.VK_W) {
				upPressed=true;
			}
			if(code == KeyEvent.VK_S) {
				downPressed=true;
			}
			if(code == KeyEvent.VK_A) {
				leftPressed=true;
			}
			if(code == KeyEvent.VK_D) {
				rightPressed=true;
			}
			if(code == KeyEvent.VK_P) {
				gp.stopMusic();
				gp.gameState = gp.pauseState;
			}
			if(code == KeyEvent.VK_E) {
				enterPressed = true;
			}
			
			//Debug
			if(code == KeyEvent.VK_T) {
				if(checkDrawTime == false) {
					checkDrawTime = true;
				}
				else if(checkDrawTime == true) {
					checkDrawTime = false;
				}
				
			}
		}
		// PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			gp.gameState = gp.playState;
			gp.playMusic(0);

			
		}
		
		// DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_E) {
				gp.gameState = gp.playState;
			}
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed=false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed=false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed=false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed=false;
		}
	}

	
}