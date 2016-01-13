package twoGirrafics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sun {
	
	BufferedImage sprite;
	String name;
	int x;
	int y;
	public Sun(String name, String imgLoc, int locationX, int locationY, int scaleX, int scaleY){
		x = locationX;
		y = locationY;
		this.name = name;
		sprite = new BufferedImage(scaleX,scaleY, BufferedImage.TYPE_INT_ARGB);
		URL url = getClass().getResource(imgLoc);
		try {
			BufferedImage original = ImageIO.read(url);
			Graphics2D g = (Graphics2D) sprite.getGraphics();
			int w = original.getWidth();
			int h = original.getHeight();
			//what to draw, where to start(x,y)
			//width of canvas (relative to start)
			//Height of canvas (rel to start)
			//where to start from original(x,y)
			//width of original, height
			//null
			g.drawImage(original, 0, 0, scaleX, scaleY, 0, 0, w, h, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public BufferedImage getImage(){
		return sprite;
	}
	public String getName(){
		return name;
	}
	public void moveUp() {
		// TODO Auto-generated method stub
		y-=2;
	}
	public void moveDown() {
		// TODO Auto-generated method stub
		y+=2;
	}
	public void moveLeft() {
		// TODO Auto-generated method stub
		x-=2;
	}
	public void moveRight() {
		// TODO Auto-generated method stub
		x+=2;
	}
}
