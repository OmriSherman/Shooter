import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Shoot extends Thread
{
	boolean isDrawn=false;
	GamePanel panel;
	int x,y,width,height;
	Image ShootImage,ShootImage2;
    char dir;
	int dir2;
	
	

	public Shoot(int x,int y,char d,GamePanel panel)
	{
		this.panel= panel;
		this.x=x;
		this.y=y;
		dir=d;
		
		ImageIcon img = new ImageIcon("shot.png");
		ShootImage = img.getImage();
		ImageIcon img2 = new ImageIcon("shothorizon.png");
		ShootImage2 = img.getImage();
		width = ShootImage.getWidth(null);
		height = ShootImage.getHeight(null);
		this.start();
	}
	
	public Shoot(int x,int y,int d,GamePanel panel)
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
	public void DrawShoot(Graphics g,int x,int y)
	{
		g.drawImage(ShootImage, x, y, width, height, null);
	}
	
	public void DrawShoothorizon(Graphics g,int x,int y)
	{
		g.drawImage(ShootImage2, x, y, height, width, null);
	}

	

    public static double distance(int a, int b) {
        return Math.sqrt(Math.pow(a, 2.0) + Math.pow(b, 2.0));
    }
		public void run()
		{
			
			if(Player.isPaused)
				{
					this.interrupt();
				}	
			
			
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
				
				if(dir=='u' || dir2==1)
					this.y-=10;
				
				if(dir=='s')
					this.y-=10;
				
				if(dir=='d' || dir2==2)
					this.y+=10;
				
				if(dir=='l' || dir2==3)
					this.x-=10;
				
				if(dir=='r'|| dir2==4)
					this.x+=10;
				
				
				panel.repaint();
//				
				
				 if (distance(this.x - panel.en1.x, this.y - panel.en1.y) < width / 2 + panel.en1.getWidth()
                 && width < panel.en1.getWidth()) {
                	 panel.DrawEnemy1=false;
                	 System.out.println("enemy 1 hit");
                	 panel.en1.ishit=true;
                	 this.interrupt();
                	 panel.en1.interrupt();
                	 panel.en1.x=2000;
                	 panel.en1.y=2000;
                	 
					 
				 }
				 

				 if (distance(this.x - panel.en2.x, this.y - panel.en2.y) < width / 2 + panel.en2.getWidth()
                 && width < panel.en2.getWidth()) {
                	 panel.DrawEnemy2=false;
                	 System.out.println("enemy 2 hit");
                	 panel.en2.ishit=true;
                	 this.interrupt();
                	 panel.en2.interrupt();
                	 panel.en2.x=2000;
                	 panel.en2.y=2000;
                	 
					 
				 }
				 if(panel.lvl==1 && panel.en1.ishit && panel.en2.ishit)
				 {
					 try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 String st = "LVL UP ";
			  		 JOptionPane.showMessageDialog(null, st);
						panel.lvl++;
						
						panel.en1= new Enemy((panel.pl.x+50),panel.pl.y,panel);
						panel.en2= new Enemy((panel.pl.x-50),panel.pl.y,panel);
						Timer t=new Timer(100,panel);
						t.start();
						panel.en1.start();
						panel.en2.start();
				 }

				 if(panel.lvl==2 && panel.en1.ishit && panel.en2.ishit)
				 {
					 System.out.println("You won soldier");
					 System.exit(0);
				 }
		}
		}
}

		
