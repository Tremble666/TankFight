package TankWar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.plaf.multi.MultiScrollBarUI;

public class myBullet {
	//bullet's location
	
		private int x;
		private int y;
		//judge bullet is alive
		private boolean live=true;
		//judge if bullet is ours or enemy
		private boolean mine;
		Image []img=new Image[4];{
		img[0]=new ImageIcon("src//images//bulletU.gif").getImage();//up
		img[1]=new ImageIcon("src//images//bulletD.gif").getImage();//down
		img[2]=new ImageIcon("src//images//bulletL.gif").getImage();//left
		img[3]=new ImageIcon("src//images//bulletR.gif").getImage();}//right
		//the width and height of bullet
		static int width=(int)(10/1920.0*Constant.screenWidth);
		static int height=(int)(10/1080.0*Constant.screenHeight);
		//bullet's direction
		public boolean u,d,l,r;
		Direction direction=Direction.stop;
		private GamePanel gp;
		//bullet's speed
		private int speedX,speedY;
		public void draw(Graphics g){
			if(live==false){
				gp.bullets.remove(this);
				return;
			}
			switch (direction) {
			case up:
				g.drawImage(img[0], x, y, null);
				y-=speedY;
				break;
			case down:
				g.drawImage(img[1], x, y, null);
				y+=speedY;
				break;
			case left:
				g.drawImage(img[2], x, y, null);
				x-=speedX;
				break;
			case right:
				g.drawImage(img[3], x, y, null);
				x+=speedY;
				break;
			default:
				break;
			}
		}
		public myBullet(){
			
		}
		public myBullet(int x,int y,int speedX,int speedY,boolean mine, Direction d,GamePanel gp){
			this.x = x;
			this.y = y;
			this.speedX=speedX;
			this.speedY=speedY;
			this.mine = mine;
			this.direction=d;
			this.gp = gp;

		}
		public void setLive(boolean live){
			this.live=live;
		}
		public Rectangle getRect(){
			Rectangle r=new Rectangle(x,y,width,height);
			return r;
		}
		public boolean hitTanks(List<myTank> tanks){
			for (int i = 0; i < tanks.size(); i++) {
				this.hitTank(tanks.get(i));
			}
			return false;
		}
		public boolean hitTank(myTank tank){
			if (this.live == true && this.getRect().intersects(tank.getRect()) 
					&& this.mine!=tank.isMine()) {
				//Bomb
				Bomb bomb=new Bomb(tank.getX(),tank.getY(),gp);
				gp.bombs.add(bomb);
//				sound.playSound();
				//home tank was hitted
				if(tank.isMine()){
					tank.setLive(false);	
				}else{
					tank.setLive(false);
					gp.enemyTanks.remove(tank);//if enemy was hitted,remove it
				}
				this.live=false;
				return true;
			}
			return false;
		}
		//hit common walls
		public boolean hitCommon(myCommonWall c){
			if(this.live==true && this.getRect().intersects(c.getRect())){
				this.live = false;
				if(this.mine==true){
					Bomb bomb;
					int x=c.getX()-8;
					int y=c.getY()+3;
					bomb=new Bomb(x,y,gp);
					gp.bombs.add(bomb);
//					sound.playSound();
				}
				gp.commonwalls.remove(c);
				gp.homearoundwalls.remove(c);
				return true;
			}
			return false;
		}
		//hit metal walls
		public boolean hitMetal(myMetalWall c){
			if(this.live==true && this.getRect().intersects(c.getRect())){
				this.live = false;
				if(this.mine==true){
					Bomb bomb;
					int x=c.getX()-8;
					int y=c.getY()+5;
					bomb=new Bomb(x,y,gp);
					gp.bombs.add(bomb);
//					sound.playSound();
				}
				return true;
			}
			return false;
		}
		//hit home
		public boolean hitHome(myHome h){
			if(this.live==true && this.getRect().intersects(h.getRect())){
//				sound.playSound();
				this.live = false;
				gp.myh.setLive(false);
				return true;
			}
			return false;
		}
		
		
		//×Óµ¯Åö×²¼ì²â
//		public boolean hitBullet(myBullet mb){
//			if(this.live==true && this.getRect().intersects(mb.getRect())){
//				gp.bullets.remove(mb);
//				gp.bullets.remove(this);
//				return true;
//			}
//			
//			return false;
//			
//		}
		
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		public void setSpeedX(int x){
			this.speedX=x;
		}
		public void setSpeedY(int y){
			this.speedY=y;
		}
		public boolean isMine(){
			return mine;
		}
	}