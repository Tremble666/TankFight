package TankWar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
public class myHome extends WallObject{
	private boolean live=true;
	//draw a common wall
		public void draw(Graphics g){
			if(this.isLive()==true){
				g.drawImage(img,(int)x,(int)y,(int)(43/1920.0*Constant.screenWidth),(int)(43/1080.0*Constant.screenHeight),null);
				}
			}
		public myHome(String path,int x,int y){
			this.img=new ImageIcon(path).getImage();
			this.x=x;
			this.y=y;
			this.width = (int)(50/1920.0*Constant.screenWidth);
			this.height = (int)(55/1080.0*Constant.screenHeight);
		}
		public boolean isLive(){
			return live;
		}
		public void setLive(boolean live){
			this.live=live;
		}
}
