import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Timer;

import javax.swing.ImageIcon;

public class Enemy extends Thread {
	boolean ishit=false;
	int x, y, width1, height1,width2,height2;
	int dir;
	GamePanel panel;
	Image Enemydown, Enemyup,Enemyleft,Enemyright;
	
	public Enemy(int x,int y,GamePanel p)
	{
		this.x=x;
		this.y=y;
		this.panel=p;
		ImageIcon img1 = new ImageIcon("enemyup.png");
		Enemyup = img1.getImage();
		ImageIcon img2 = new ImageIcon("enemydown.png");
		Enemydown = img2.getImage();
		ImageIcon img3 = new ImageIcon("enemyleft.png");
		Enemyleft = img3.getImage();
		ImageIcon img4 = new ImageIcon("enemyright.png");
		Enemyright = img4.getImage();
		this.width1 = Enemydown.getWidth(null);
		this.height1 = Enemydown.getHeight(null);
		this.width2 = Enemyleft.getWidth(null);
		this.height2 = Enemyleft.getHeight(null);
		
		
	}
	
	public int getWidth()
	{
		return this.width1;
	}
	
	public void drawEnemyup(Graphics g) {
		g.drawImage(Enemyup, x, y,width1-35, height1-35, null);
	}
	
	public void drawEnemydown(Graphics g) {
		g.drawImage(Enemydown, x, y, width1-35, height1-35, null);
	}
	
	public void drawEnemyleft(Graphics g) {
		g.drawImage(Enemyleft, x, y,width2-35, height2-35, null);
	}
	
	public void drawEnemyright(Graphics g) {
		g.drawImage(Enemyright, x, y, width2-35, height2-35, null);
	}

	
	public void kill()
	{
		this.x=2500;
		this.y=2500;
	}
	
	public void run()
	{
		if(panel.pl.isPaused)
		{
			synchronized (this)
			{
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		
		
		
		int h = panel.getHeight();
		int w = panel.getWidth(); 
			
			while(x > -20 && x < w+20 && y > -20 && y < h+20)
			{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				break;
			}
			if(Player.isPaused)
			{
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			}
				
		int dir = (int) (Math.random() * 4) + 1;
		int steps = (int) (Math.random()* 4) + 1;
		switch (dir)
		{
		case 1:
			if (x + width1  < 1024 - 60 ) //MOVE RIGHT
			{
				x +=15*steps;
			this.dir=1;
			}
			
			if(x < 1024-60)
			{
				dir=2;
				this.dir=2;
			}	
			break;
			
		case 2:
			if( x - width1  > 60) //MOVE LEFT
			{	
			x-=25*steps;
			this.dir=2;
			}
			
			if(x < 60)
			{
				dir=1;
				this.dir=1;
			}
			
			break;
			
		case 3:
			if(y + height1  < 60) //MOVE UP
			{
			y -=15*steps;
			this.dir=3;
			}
			
			if(y < 60)
			{
				dir=4;
				this.dir=4;
			}
			break;
			
		case 4:
			if(y - height1  < 720 -60) //MOVE DOWN
			{
			y+=25*steps;
			this.dir=4;
			}
			
			if(y > 660)
				dir=3;
			break;
		}
	}
			
			
			
	}
	}
	
	
