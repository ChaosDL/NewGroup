package twoGirrafics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
public class MyWindow extends JFrame implements KeyListener{
	int width = 1250;
	int height = 800;
	Sun okamiCircle;
	Sun rays;
	//BufferedImage landscape;
	boolean itemPickedUp;
	public static void main(String[] args) {
		new MyWindow();
	}
	
	public MyWindow(){
		//JFrame methods
		okamiCircle = new Sun("Red", "/images/okami.png", 0, 0, 420, 420);
		//landscape = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		//Graphics2D g2 = (Graphics2D)landscape.getGraphics();
		rays = new Sun("Rays", "/images/rays.png", 0, 0, 1250, 800);
		itemPickedUp = false;
		setVisible(true);
		setSize(width,height);
		setLocation(640, 540);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//static constant reference to close\
		addKeyListener(this);
	}
	
	public void paint(Graphics g){
		//Graphics is a crayon box
		//Graphics2d is like an art kit
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)image.getGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, width, height);
//		g2.setColor(Color.blue);
//		int squareD = 20;
//		int margin = 5;
//		for(int x =0; x< width; x+= squareD+margin){
//			for(int y =0; y< width; y+= squareD+margin){
//				Color c = new Color((int)Math.floor(Math.random()* x%255),(int)Math.floor(Math.random()*  y%255),0);
//				g2.setColor(c);
//				g2.fillRect(x, y, squareD, squareD);
//			}
//			
//		}
//		g2.drawOval(50,100,200,100);
//		g2.setColor(Color.red);
//		//x,y,width,height,StartDeg,lengthdegrees
//		g2.drawArc(50, 300, 200, 100, 0, 90);
//		g2.drawString("words", 300, 300);
//		g2.drawLine(0, 0, width, height);
//		int x = 0;
//		for(int y = 0; y<height; y++){
//			g2.drawLine(0, y, x, height);
//			//g2.drawLine(x, y, 0, 0);
//			x+=2;
//			if(x>=width)x=width;
//		}
//	
		
//		if((Math.abs(okamiCircle.getX() + 210 - rays.getX()+400)) + (Math.abs(okamiCircle.getY() + 210 - rays.getY()+625)) < 10){
//			itemPickedUp = true;
//		}
		if(okamiCircle.getY() > 190 && okamiCircle.getX() > 390){
			itemPickedUp = true;
		}
		//make it rotate
		if(!itemPickedUp){
			g2.drawImage(rays.getImage(), rays.getX(), rays.getY(), null);
		}
		g2.drawImage(okamiCircle.getImage(), okamiCircle.getX(), okamiCircle.getY(), null);
		//draw bufferedImage on canvas
		g.drawImage(image,0,0,null);
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		if(key==KeyEvent.VK_UP){
			okamiCircle.moveUp();
		}
		else if(key==KeyEvent.VK_DOWN){
			okamiCircle.moveDown();
		}
		else if(key==KeyEvent.VK_LEFT){
			okamiCircle.moveLeft();
		}
		else if(key==KeyEvent.VK_RIGHT){
			okamiCircle.moveRight();
		}
		repaint();
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		if(key==KeyEvent.VK_UP);{
			
		}
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
