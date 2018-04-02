package TankWar;

import java.awt.Graphics;

import javax.swing.ImageIcon;
//common wall
public class myTreeWall extends WallObject{
	//draw a common wall
	public void draw(Graphics g){
			g.drawImage(img,(int)x,(int)y,this.width,this.height,null);
		}
	public myTreeWall(String path,int x,int y){
		this.img=new ImageIcon(path).getImage();
		this.x=x;
		this.y=y;
		this.width = (int)(36/1920.0*Constant.screenWidth);
		this.height = (int)(36/1080.0*Constant.screenHeight);
	}		
}
