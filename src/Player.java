import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Player extends Thread {
	GamePanel panel;
	int x=455, y=570, width1, height1,width2,height2;
	Image Playerup,Playerdown,Playerleft,Playerright;
	static boolean isPaused=false;
	int life=3;
	
	public Player(GamePanel panel)
	{
		this.panel= panel;
		ImageIcon img1 = new ImageIcon("playerup.png");
		Playerup = img1.getImage();
		ImageIcon img2 = new ImageIcon("playerdown.png");
		Playerdown = img2.getImage();
		ImageIcon img3 = new ImageIcon("playerleft.png");
		Playerleft = img3.getImage();
		ImageIcon img4 = new ImageIcon("playerright.png");
		Playerright = img4.getImage();
		width1 = Playerup.getWidth(null);
		height1 = Playerup.getHeight(null);
		width2 = Playerleft.getWidth(null);
		height2 = Playerleft.getHeight(null);
		start();
	}
	
	public void run()
	{
		while (!Thread.interrupted()) {

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				break;

			}

			panel.repaint();
		}
		if(Player.isPaused)
		{
			this.interrupt();
			}
		}	


	int getPlayerImageWidth() {
		return width1;
	}

	int getPlayerImageHeight() {
		return height1;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	
	public void drawPlayerup(Graphics g) {
		g.drawImage(Playerup, x, y,width1-35, height1-35, null);
	}
	
	public void drawPlayerdown(Graphics g) {
		g.drawImage(Playerdown, x, y, width1-35, height1-35, null);
	}
	
	public void drawPlayerleft(Graphics g) {
		g.drawImage(Playerleft, x, y,width2-35, height2-35, null);
	}
	
	public void drawPlayerright(Graphics g) {
		g.drawImage(Playerright, x, y, width2-35, height2-35, null);
	}




}

