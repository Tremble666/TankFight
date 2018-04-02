package TankWar;

import java.awt.Graphics;

import javax.swing.ImageIcon;
//common wall
public class myMetalWall extends WallObject{
	//draw a common wall
	public void draw(Graphics g){
		g.drawImage(img,(int)x,(int)y,this.width,this.height,null);
		}
	public myMetalWall(String path,int x,int y){
		this.img=new ImageIcon(path).getImage();
		this.x=x;
		this.y=y;
		this.width = (int)(35/1920.0*Constant.screenWidth);
		this.height = (int)(35/1080.0*Constant.screenHeight);
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
