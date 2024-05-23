package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;
import MySQL.Rankings;
import MySQL.Rankings.PlayerScore;
import MySQL.Login;

public class UI {
	GamePanel gp;
	Graphics2D g2;


	Font purisaB, simsun, arial_40, arial_80B;
	BufferedImage keyImage;
	BufferedImage confetti;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	public String message ="";
	public String username;
	public String password;
	int messageCounter = 0;
	public boolean gameFinished = false;
	public boolean youdied = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0;   //0:the first screen, 1:the second screen, 2:for the rankings
	public int loginchecked =0;

	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.000");

	public boolean isGameFinished() {
		return gameFinished;
	}

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial",Font.PLAIN,40);
		arial_80B = new Font("Arial",Font.BOLD,80);

		try {
			InputStream is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		OBJ_Key key = new OBJ_Key(gp);
		keyImage = key.image;

		// CREATE HUD OBJECT
		SuperObject heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	}

	public void showMEssage(String text)
	{
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {

		this.g2 = g2;
		g2.setFont(purisaB);

		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			
			TitleScreenMain();
		}

		// PLAY STATE
		if(gp.gameState == gp.playState) {

			drawPlayerLife();
			if(youdied == true) {
				gp.stopMusic();
				g2.setFont(arial_40);
				g2.setColor(Color.white);
				String text;
				int textLength;
				int x;
				int y;
				text = "You died stupid ";
				textLength = (int)g2.getFontMetrics().getStringBounds(text,  g2).getWidth();
				x = gp.screenWidth/2 - textLength/2;
				y =gp.screenHeight/2 + (gp.tileSize*4);
				g2.drawString(text, x, y);
				g2.setFont(arial_80B);
				g2.setColor(Color.yellow);
				text = "Good Job";
				textLength = (int)g2.getFontMetrics().getStringBounds(text,  g2).getWidth();
				x = gp.screenWidth/2 - textLength/2;
				y = gp.screenHeight/2 + (gp.tileSize*3);
				g2.drawString(text, x, y);
				gp.gameThread = null;
			}
			else if(gameFinished == true) {
				g2.setFont(arial_40);
				g2.setColor(Color.white);
				String text;
				int textLength;
				int x;
				int y;
				text = "Try harder "+ dFormat.format(playTime);
				Rankings db = new Rankings();
				int playerid = db.GetID(username);
				if(playTime < db.GetScore(playerid))
				{

					db.UpdateScore(playerid, playTime);
				}
				textLength = (int)g2.getFontMetrics().getStringBounds(text,  g2).getWidth();
				x = gp.screenWidth/2 - textLength/2;
				y =gp.screenHeight/2 + (gp.tileSize*4);
				g2.drawString(text, x, y);
				g2.setFont(arial_80B);
				g2.setColor(Color.yellow);
				text = "Good Job";
				textLength = (int)g2.getFontMetrics().getStringBounds(text,  g2).getWidth();
				x = gp.screenWidth/2 - textLength/2;
				y = gp.screenHeight/2 + (gp.tileSize*3);
				g2.drawString(text, x, y);
				gp.gameThread = null;
			}
			else{
				g2.setFont(arial_40);
				g2.setColor(Color.white);
				g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
				g2.drawString("x "+ gp.player.hasKey, 74, 58);

				playTime +=(double)1/60;
				g2.drawString("Time:" +dFormat.format(playTime), gp.tileSize* 11,65);

				if(messageOn == true) {
					g2.setFont(g2.getFont().deriveFont(30F));
					g2.drawString(message,  gp.tileSize/2,gp.tileSize*5 );
					messageCounter++;
					if(messageCounter > 120) {
						messageCounter = 0;
						messageOn = false;
					}
				}
			}
		}
		// PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
			g2.setFont(purisaB);
			g2.setColor(Color.white);
			g2.drawString("Time:" +dFormat.format(playTime), gp.tileSize* 11,65);
		}

		// DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}


	}

	public void drawPlayerLife() {

		//gp.player.life = 3;

		int x = gp.tileSize/2;
		int y = gp.tileSize + gp.tileSize/2;
		int i = 0;

		// draw max life
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}

		//reset
		x = gp.tileSize/2;
		y = gp.tileSize + gp.tileSize/2;
		i = 0;

		// draw max life
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i <gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
	}
	public void TitleScreenMain() {
		
		
		if (loginchecked == 0) {
			
			Login login = new Login();
			Scanner scanner = new Scanner(System.in);
			System.out.println("1. Login");
			System.out.println("2. Create Account");
			System.out.print("Choose an option: ");
			int option = scanner.nextInt();
			scanner.nextLine(); // consume the newline
			
			
			if (option == 1) {
				System.out.print("Enter username: ");
				username = scanner.nextLine();

				System.out.print("Enter password: ");
				password = scanner.nextLine();

				login.clearConsole();

				if (login.authenticate(username, password)) {
					loginchecked = 1;
					scanner.close();
					drawTitleScreen();
				} else {
					System.out.println("Invalid username or password.");
				}
			} else if (option == 2) {
				System.out.print("Enter new username: ");
				String newUsername = scanner.nextLine();

				System.out.print("Enter new password: ");
				String newPassword = scanner.nextLine();

				// Assuming you have a method to create an account
				if (login.createAccount(newUsername, newPassword)) {
					System.out.println("Account created successfully. Please login.");
				} else {
					System.out.println("Failed to create account. Username might already exist.");
				}
			} else {
				System.out.println("Invalid option.");
			}
		} else {
			drawTitleScreen();
		}
	}
	private void drawTitleScreen() {
		if (titleScreenState == 0) {
			g2.setColor(new Color(0, 102, 102));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

			// Title name
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 86F));
			Rankings db = new Rankings();
			int playerid = db.GetID(username);
			String text = db.GetUsername(playerid);
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 3;

			// shadow
			g2.setColor(Color.black);
			g2.drawString(text, x + 5, y + 5);

			// main color
			g2.setColor(Color.white);
			g2.drawString(text, x, y);

			String scoretext = "Best score:";
			double score = db.GetScore(playerid);
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
			g2.drawString(scoretext + score + " seconds", x -= gp.tileSize, y -= gp.tileSize * 2 - 10);

			// image
			x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
			y += gp.tileSize * 2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

			// menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 43F));
			text = "PLAY";
			x = getXforCenteredText(text);
			y += gp.tileSize * 3.5;
			g2.drawString(text, x, y);

			if (commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "RANKINGS";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}
		} else if (titleScreenState == 1) {
			// CLASS SELECTION SCREEN
			g2.setColor(new Color(0, 102, 102));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

			g2.setFont(g2.getFont().deriveFont(42F));
			String text = "Select your class!";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 3;
			// shadow
			g2.setColor(Color.black);
			g2.drawString(text, x + 5, y + 5);

			g2.setColor(Color.white);
			g2.drawString(text, x, y);

			text = "Fighter";
			x = getXforCenteredText(text);
			y += gp.tileSize * 3;
			g2.drawString(text, x, y);
			if (commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "Thief";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "Sorcerer";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "Back";
			x = getXforCenteredText(text);
			y += gp.tileSize * 2;
			g2.drawString(text, x, y);
			if (commandNum == 3) {
				g2.drawString(">", x - gp.tileSize, y);
			}
		} else if (titleScreenState == 2) {
			// RANKINGS SCREEN
			g2.setColor(new Color(0, 102, 102));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

			g2.setFont(g2.getFont().deriveFont(42F));
			String text = "Rankings";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 2;
			// shadow
			g2.setColor(Color.black);
			g2.drawString(text, x + 5, y + 5);

			g2.setColor(Color.white);
			g2.drawString(text, x, y);

			Rankings rankings = new Rankings();
			List<Rankings.PlayerScore> top5Scores = rankings.Get5Scores();
			for (int i = 0; i < 5; i++) {
				Rankings.PlayerScore ps = top5Scores.get(i);
				String usernameranking = rankings.GetUsername(ps.getId());
				text = (i + 1) + ". " + usernameranking + " : " + ps.getScore() + " seconds";
				x = getXforCenteredText(text);
				y += gp.tileSize;
				g2.drawString(text, x, y);
			}

			text = "Back";
			x = getXforCenteredText(text);
			y += gp.tileSize * 2;
			g2.drawString(text, x, y);
			g2.drawString(">", x - gp.tileSize, y);
		}
	}
	public void drawPauseScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		g2.setColor(Color.white);
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);

	}

	public void drawDialogueScreen() {

		// DIALOGUE WINDOW
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;

		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
		x += gp.tileSize;
		y += gp.tileSize;

		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}

	}

	public void drawSubWindow(int x, int y, int width, int height) {

		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));					// Define the width of outlines, rendered with Graphics 2D
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}

	public int getXforCenteredText(String text) {
		int lenght = (int)g2.getFontMetrics().getStringBounds(text,  g2).getWidth();
		int x = gp.screenWidth/2 - lenght/2;
		return x;
	}
}
