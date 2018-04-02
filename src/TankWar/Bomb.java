package TankWar;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomb {
	private int x;
	private int y;
	
	private boolean live=true;
	
	private GamePanel gp;
	
	Image []img=new Image[10];{
	img[0]=new ImageIcon("src//images//0.gif").getImage();
	img[1]=new ImageIcon("src//images//1.gif").getImage();
	img[2]=new ImageIcon("src//images//2.gif").getImage();
	img[3]=new ImageIcon("src//images//3.gif").getImage();
	img[4]=new ImageIcon("src//images//4.gif").getImage();
	img[5]=new ImageIcon("src//images//5.gif").getImage();
	img[6]=new ImageIcon("src//images//6.gif").getImage();
	img[7]=new ImageIcon("src//images//7.gif").getImage();
	img[8]=new ImageIcon("src//images//8.gif").getImage();
	img[9]=new ImageIcon("src//images//9.gif").getImage();}
	public Bomb(int x,int y,GamePanel gp){
		this.x=x;
		this.y =y;
		this.gp=gp;
	}
	int step=0;
	public void draw(Graphics g){
		if(!live){
			gp.bombs.remove(this);
			return;
		}
		if(step>=img.length){
			step=0;
			this.live=false;
			return;
		}
		g.drawImage(img[step], x, y, null);
		step++;
	}
}