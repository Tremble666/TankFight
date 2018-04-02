package TankWar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class WallObject {
	//the location of the wall
	protected int x,y;
	//the picture of the wall
	protected Image img;
	//width and height
	protected int width,height;
	//get rectangle
	public Rectangle getRect(){
		Rectangle r=new Rectangle((int)x,(int)y,width,height);
		return r;
	}
	public WallObject(int x, int y, Image img, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.img = img;
		this.width = width;
		this.height = height;
	}
	public WallObject(){
	}
}
