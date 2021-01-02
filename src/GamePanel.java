import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener
{
	char dir='s';
	char dirShoot='s';

	boolean shoot=false,paintShoot,en1paintShoot,en2paintShoot;
    Player pl;
    Enemy en1,en2;
	ImageIcon bg;
	Shoot sh;
	EnemyShoot en1sh,en2sh;
	boolean isShot=false,DrawEnemy1=true,DrawEnemy2=true,isPaused=false;
	int shootx,shooty,en1shx,en1shy,en2shx,en2shy;
	int en1dirsh,en2dirsh;
	int lvl=1;
	Timer t=new Timer(1000,this);
	public GamePanel()
	{
		setFocusable(true);
		setBackground(Color.BLACK);
		bg=new ImageIcon ("bg.png");
		pl = new Player(this);
		en1= new Enemy(150,100,this);
		en2= new Enemy(750,100,this);
		addKeyListener(this);
		System.out.println("Good luck, life: 3");
		t.start();
		
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            pl.x+=15;
            dir='r';
            //repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            pl.x-=15;
            dir='l';
            //repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
            pl.y-=15;
            dir='u';
           // repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            pl.y+=15;
            dir='d';
           // repaint();
    } 
      else if(e.getKeyCode()==KeyEvent.VK_P)
		{
			pl.isPaused=true;	
			
		}
      else if(e.getKeyCode()==KeyEvent.VK_R)
      {
    	  synchronized(en1) {
    		  en1.notify(); }
    	  synchronized(en2) {
    		  en2.notify(); }
    	  pl.isPaused=false;
      }
      else if (e.getKeyCode() == KeyEvent.VK_9) {
    	  
      	en1.ishit=true;
      	en1.kill();
      	en2.ishit=true;
      	en2.kill();
      	System.out.println("cheats activated :(");
      	String st = "NOT TODAY CHEATER, TRY HARDER ";
  		 JOptionPane.showMessageDialog(null, st);
  		 pl.interrupt();
  		 JFrame f = new JFrame("Pro Shooter by the Shermanator");
  			f.setFocusable(false);
  			GamePanel gp = new GamePanel();
  			f.add(gp);
  			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  			f.setSize(1024, 700);
  			f.setResizable(false);
  			//f.setUndecorated(true);
  			f.setVisible(true);
  			gp.hideMouseCursor();
  			lvl++;
  			pl.life=3;
  			en1.ishit=false;
  			en2.ishit=false;
      	
      }
	
    else if (e.getKeyCode()==KeyEvent.VK_SPACE ) {
    	dirShoot=dir;
       shoot=true;
       if(shoot)
		{
			switch(dir)
			{
			case 'u': {
				shootx=pl.x+40;
				shooty=pl.y-30; 
				}
				break;
					
			case 'd': {
				shootx=pl.x+17;
				shooty=pl.y+87;
				}
				break;
				
			case 'l': {
				shootx=pl.x-24;
				shooty=pl.y+17;
				}
				break;
				
			case 'r': {
				shootx=pl.x+86;
				shooty=pl.y+40;
				}
				break;
				
			case 's': {
				shootx=pl.x+40;
				shooty=pl.y-30;
				}
				break;
				
			}

			sh=new Shoot(shootx,shooty,dirShoot,this);
			paintShoot=true;
 
    }
       repaint();
    }
	en1dirsh=en1.dir;
	
		switch(en1.dir)
		{
		case 3: {
			en1shx=en1.x+40;
			en1shy=en1.y-30; 
			}
			break;
				
		case 4: {
			en1shx=en1.x+17;
			en1shy=en1.y+87;
			}
			break;
			
		case 2: {
			en1shx=en1.x-24;
			en1shy=en1.y+17;
			}
			break;
			
		case 1: {
			en1shx=en1.x+86;
			en1shy=en1.y+40;
			}
			break;
		}
		
		en1sh=new EnemyShoot(en1shx,en1shy,en1dirsh,this);
		en1paintShoot=true;
	
	
	en2dirsh=en2.dir;
	
	switch(en2.dir)
	{
	case 3: {
		en2shx=en2.x+40;
		en2shy=en2.y-30; 
		}
		break;
			
	case 4: {
		en2shx=en2.x+17;
		en2shy=en2.y+87;
		}
		break;
		
	case 2: {
		en2shx=en2.x-24;
		en2shy=en2.y+17;
		}
		break;
		
	case 1: {
		en2shx=en2.x+86;
		en2shy=en2.y+40;
		}
		break;
		
	}
	en2sh=new EnemyShoot(en2shx,en2shy,en2dirsh,this);
	en2paintShoot=true;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		en1.start();
		en2.start();
		
		
		
	}
		
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image s = bg.getImage();
		g.drawImage(s,0, 0, getWidth()+100, getHeight()+100, null);
		
		
		if(paintShoot)
		{
			if(sh.isAlive())
			{
			if(dirShoot=='r' || dirShoot=='l')
			{
				sh.DrawShoothorizon(g,sh.getX(), sh.getY());	
			}
			else
			{
				sh.DrawShoot(g, sh.getX(), sh.getY());
				
			}
			}
			{ repaint(); }
		}
			
			
			if(en1paintShoot)
			{
				if(en1sh.isAlive())
				{
				if(en1dirsh==1 || en1dirsh==2)
				{
					en1sh.DrawEnemyShoothorizon(g,en1sh.getX(), en1sh.getY());	
				}
				else
				{
					en1sh.DrawEnemyShoot(g, en1sh.getX(), en1sh.getY());
					
				}
				}
				{ repaint(); }
		}
			
			if(en2paintShoot)
			{
				if(en2sh.isAlive())
				{
				if(en2dirsh==1 || en2dirsh==2)
				{
					en2sh.DrawEnemyShoothorizon(g,en2sh.getX(), en2sh.getY());	
				}
				else
				{
					en2sh.DrawEnemyShoot(g, en2sh.getX(), en2sh.getY());
					
				}
				}
			
		}
		switch(dir)
		{
		case 'u': {
			pl.drawPlayerup(g);
			repaint();
			}
			break;
				
		case 'd': {
			pl.drawPlayerdown(g);
			repaint();
			}
			break;
			
		case 'l': {
			pl.drawPlayerleft(g);
			repaint(); 
			}
			break;
			
		case 'r': {
			pl.drawPlayerright(g);
			repaint(); 
			}
			break;
			
		case 's': {
			pl.drawPlayerup(g);
			repaint();
			}
			break;
		}
		
		
			switch(en1.dir)
			{
			case 1: {
				en1.drawEnemyright(g);
				if(!(en1.isInterrupted()))
				repaint();
				break; }
				
			case 2: {
				en1.drawEnemyleft(g);
				if(!(en1.isInterrupted()))
				repaint();
				break; }
				
			case 3: {
				en1.drawEnemyup(g);
				if(!(en1.isInterrupted()))
				repaint();
				break; }
				
			case 4: {
				en1.drawEnemydown(g);
				if(!(en1.isInterrupted()))
				repaint();
				break; }
			}
			
			switch(en2.dir)
			{
			case 1: {
				en2.drawEnemyright(g);
				if(!(en2.isInterrupted()))
				repaint();
				break; }
				
			case 2: {
				en2.drawEnemyleft(g);
				if(!(en2.isInterrupted()))
				repaint();
				break; }
				
			case 3: { 
				en2.drawEnemyup(g);
				if(!(en2.isInterrupted()))
				repaint();
				break; }
				
			case 4: {
				en2.drawEnemydown(g);
				if(!(en2.isInterrupted()))
				repaint();
				break; }
			}
			
			if(pl.isPaused)
			{
				g.setColor(new Color (255,255,255,80));
				g.fillRect(0, 0, getWidth(), getHeight());
				Font myFont = new Font("Lucida Calligraphy", Font.BOLD, 36);
				g.setFont(myFont);
				g.setColor(Color.yellow);
				g.drawString("PAUSE",420,350);
			}
			
		}
	
	
			
	
	
	
	public void hideMouseCursor() {
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JPanel.
		setCursor(blankCursor);
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame("Pro Shooter by the Shermanator");
		f.setFocusable(false);
		GamePanel gp = new GamePanel();
		f.add(gp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1024, 700);
		f.setResizable(false);
		//f.setUndecorated(true);
		f.setVisible(true);
		gp.hideMouseCursor();
	}

	
	

	
}

	
