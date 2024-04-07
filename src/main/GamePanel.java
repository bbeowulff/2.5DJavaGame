package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import entity.Entity;

public class GamePanel extends JPanel implements Runnable{
	//SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale =3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	public final int maxWorldCol = 66;
	public final int maxWorldRow = 66;
	
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter eSetter = new AssetSetter(this);
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];			//npc array
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	
	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		eSetter.setObject();
		eSetter.setNPC();
		//playMusic(0);
		gameState = titleState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		//gameState = playState;
	}
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta+= (currentTime - lastTime) / drawInterval;
			timer+= (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
			update();
			repaint();
			delta--;
			drawCount++;
			}
			if(timer >= 1000000000) {
				//System.out.println("FPS: "+drawCount);
				drawCount = 0;
				timer  =0;
			}
		}
		
	}
	public void update() {
		if(gameState == playState) {
			// PLAYER
			player.update();
			
			// NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
		}
		if(gameState == pauseState) {
			//nothing
		}
		
	  
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//Debug
		long drawStart = 0;
		if(keyH.checkDrawTime == true) {
			drawStart = System.nanoTime();   //check the time
			
		}
		
		//TITLE SCREEN
		if (gameState == titleState) {
			ui.draw(g2);
		}
		//OTHERS
		else {
			tileM.draw(g2);
			for(int i=0;i<obj.length; i++)
			{
				
				if(obj[i] != null) {
					obj[i].draw(g2,this,i);
				}
			}

			//NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			
			player.draw(g2);
			ui.draw(g2);
			
		}
		

		//Debug - daniela
		if(keyH.checkDrawTime == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time : " + passed, 10, 400);
			System.out.println("Draw Time: " + passed);
			
		}
		
		g2.dispose();
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}

}
