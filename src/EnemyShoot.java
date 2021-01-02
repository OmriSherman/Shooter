import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EnemyShoot extends Thread {

	GamePanel panel;
	int x,y,width,height;
	Image ShootImage,ShootImage2;
    char dir;
	int dir2;
	Enemy en;
	
	
	public EnemyShoot(int x,int y,int d,GamePanel panel)
	{
		this.panel= panel;
		this.x=x;
		this.y=y;
		dir2=d;
		
		ImageIcon img = new ImageIcon("shot.png");
		ShootImage = img.getImage();
		ImageIcon img2 = new ImageIcon("shothorizon.png");
		ShootImage2 = img.getImage();
		width = ShootImage.getWidth(null);
		height = ShootImage.getHeight(null);
		this.start();
	}

	public char getDir() {
		return dir;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public void DrawEnemyShoot(Graphics g,int x,int y)
	{
		g.drawImage(ShootImage, x, y, width, height, null);
	}
	
	public void DrawEnemyShoothorizon(Graphics g,int x,int y)
	{
		g.drawImage(ShootImage2, x, y, height, width, null);
	}

	

    public static double enemyShootDistance(int a, int b) {
        return Math.sqrt(Math.pow(a, 2.0) + Math.pow(b, 2.0));
    }
		public void run()
		{
			
			
			
			while (y > 0 && y < 700 && x > 0 && x < 1024) {
				if( !(y > 0 && y < 700 && x > 0 && x < 1024))
					break;
					

				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					break;

				}
				
				if(dir=='u' || dir2==3)
					this.y-=10;
				
				if(dir=='s')
					this.y-=10;
				
				if(dir=='d' || dir2==4)
					this.y+=10;
				
				if(dir=='l' || dir2==2)
					this.x-=10;
				
				if(dir=='r'|| dir2==1)
					this.x+=10;
				
				
				panel.repaint();
//				
				
				 if (enemyShootDistance(this.x - panel.pl.x, this.y - panel.pl.y) < width / 2 + panel.pl.getPlayerImageWidth()
                 && width < panel.pl.getPlayerImageWidth()) {
                	 --panel.pl.life;
                	 System.out.println("You've been hit, life left: " + panel.pl.life);
                	this.interrupt();
				 }
				 if(panel.pl.life==0)
				 {
					 System.exit(0);
				 }
				 

				
					 
				 }
			}
}
