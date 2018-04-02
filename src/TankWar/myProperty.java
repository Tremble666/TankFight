package TankWar;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class myProperty extends WallObject{
	private boolean live=true;
	private GamePanel gp;
	//set signal for property
	private int flag;
	//
	Image []img=new Image[4];{
		//enemy tanks' images
		img[0]=new ImageIcon("src//images//mintank.gif").getImage();//smallel
		img[1]=new ImageIcon("src//images//timer.gif").getImage();//quicker
		img[2]=new ImageIcon("src//images//bombpro.gif").getImage();//clear enemy tank distance hometank 300px 
		img[3]=new ImageIcon("src//images//star.gif").getImage();}//add metal wall to home
	public myProperty(int x,int y,boolean live,int flag ,GamePanel gp){
		this.x = x;
		this.y = y;
		this.live=live;
		this.gp=gp;
		this.flag=flag;
		this.width = (int)(15/1920.0*Constant.screenWidth);
		this.height = (int)(15/1080.0*Constant.screenHeight);
	}
	public void draw(Graphics g){
		if(this.live==true){
			if(flag==1)
				g.drawImage(img[0], x, y, width, height, null);
			if(flag==2)
				g.drawImage(img[1], x, y, width, height, null);
			if(flag==3)
				g.drawImage(img[2], x, y, width, height, null);
			if(flag==4)
				g.drawImage(img[3], x, y, width, height, null);
			}	
	}
	public int getFlag(){
		return flag;
	}
	public void setLive(boolean live){
		this.live=live;
	}
	public boolean getLive(){
		return live;
	}
}
