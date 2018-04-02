package TankWar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class myTank {
	
	static Music sound = new Music("src//music//add.wav");
	static Music sound2 = new Music("src//music//fire.wav");
	Bomb bomb;
	
	//自己的坦克子弹只有一发，标量
//	private boolean isMyBullet = true;
	
	
	//tank's location
	private int x;
	private int y;
	//judge tank is alive
	private boolean live=true;
	//tanks's old location
	private int oldX;
	private int oldY;
	//bullet speed up flag
	private boolean bulletSpeedUpFlag=true;
	//set a int value to decide clear the bullet
	private int clearBullet=3;
	//judge if tank is ours or enemy
	private boolean mine;
	Image []img=new Image[9];{
	//enemy tanks' images
		img[0]=new ImageIcon("src//images//tugai.net.20101117235441.gif").getImage();//up
		img[1]=new ImageIcon("src//images//tugai.net.20101117235941.gif").getImage();//down
		img[2]=new ImageIcon("src//images//tugai.net.20101117235819.gif").getImage();//left
		img[3]=new ImageIcon("src//images//enemy3R.gif").getImage();//right
	//home tanks' images
	img[5]=new ImageIcon("src//images//p2tankU.gif").getImage();//up
	img[6]=new ImageIcon("src//images//p2tankD.gif").getImage();//down
	img[7]=new ImageIcon("src//images//p2tankL.gif").getImage();//left
	img[8]=new ImageIcon("src//images//p2tankR.gif").getImage();}//right
	//the width and height of tank
	private int width=(int)(35/1920.0*Constant.screenWidth);
	private int height=(int)(35/1080.0*Constant.screenHeight);
	//tank's direction
	public boolean u,d,l,r;
	//initial state
	Direction direction=Direction.stop;
	//initial direction
	Direction kdDirection=Direction.up;
	private GamePanel gp;
	//tank's speed
	private double speedX=1,speedY=1;
	//after tank move for a while,change direction
	private static Random ran=new Random();
	private int step=ran.nextInt(200)+100;
	public void draw(Graphics g){
		if(this.live==false){
			gp.enemyTanks.remove(this);
			return;
		}
		switch (kdDirection) {
		case up:
			if(!mine)
				g.drawImage(img[0], x, y, width, height, null);
			if(mine)
				g.drawImage(img[5], x, y,width,height, null);
			break;
		case down:
			if(!mine)
				g.drawImage(img[1], x, y, width, height, null);
			if(mine)
				g.drawImage(img[6], x, y,width,height,  null);
			break;
		case left:
			if(!mine)
				g.drawImage(img[2], x, y, width, height, null);
			if(mine)
				g.drawImage(img[7], x, y,width,height,  null);
			break;
		case right:
			if(!mine)
				g.drawImage(img[3], x, y, width, height, null);
			if(mine)
				g.drawImage(img[8], x, y,width,height,  null);
			break;
		default:
			break;
		}
		move();
	}
	public myTank(int x,int y,boolean mine,Direction d,GamePanel gp){
		this.x=x;
		this.y=y;
		this.oldX=x;
		this.oldY=y;
		this.mine=mine;
		this.direction=d;
		this.gp=gp;

	}
	public myTank(){
		
	}
	private void move(){
		this.oldX=x;
		this.oldY=y;
		if(this.direction!=Direction.stop){
			kdDirection=direction;
		}
		
		switch (this.direction) {
		case up:
			y-=speedY;
			break;
		case down:
			y+=speedY;
			break;
		case left:
			x-=speedX;
			break;
		case right:
			x+=speedX;
			break;
		default:
			break;
		}
		//tanks' move restrict
		if(x<0){
			x=0;
		}else if(x > (1555/1920.0) * Constant.screenWidth){
			x=(int)((1555/1920.0) * Constant.screenWidth);
		}else if(y > (895/1080.0) * Constant.screenHeight){
			y=(int)((895/1080.0) * Constant.screenHeight);
		}else if(y < (5/1080.0) * Constant.screenHeight){
			y=(int)((5/1080.0) * Constant.screenHeight);
		}
		//enemy tans' random directions
		if(!mine){
			Direction []directions=Direction.values();
			if(step==0){
				step=ran.nextInt(200)+100;
				int rn=ran.nextInt(directions.length);
				direction=directions[rn];
			}
			step--;
			if(ran.nextInt(400)>398){
				fire();
			}
		}
	}
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_R://start a new game
			
			break;
		case KeyEvent.VK_UP://up key
	
			u=true;
			break;
		case KeyEvent.VK_DOWN://down key
	
			d=true;
			break;
		case KeyEvent.VK_LEFT://left key
	
			l=true;
			break;
		case KeyEvent.VK_RIGHT://right key
	
			r=true;
			break;
		case KeyEvent.VK_F://fire()

		
		default:
			break;
		}
		decideDirection();
	}


	private myBullet fire() {
		if(!live){
			return null;
		}
		int x=this.x+this.width/2-myBullet.width/2;
		int y=this.y+this.height/2-myBullet.height/2;
		myBullet bul;
		if(bulletSpeedUpFlag){
			bul=new myBullet(x,y,2,2,mine,kdDirection,gp);
			gp.bullets.add(bul);
			return bul;
		}
		else{
			bul=new myBullet(x,y,4,4,mine,kdDirection,gp);
			gp.bullets.add(bul);
			return bul;
		}
	}
	private void decideDirection() {
		if(u){
			direction=Direction.up;
		}else if(d){
			direction=Direction.down;
		}else if(l){
			direction=Direction.left;
		}else if(r){
			direction=Direction.right;
		}else if (!l && !r && !d && !u) {
			direction = Direction.stop; // 没有按下就保持不动
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_F://fire()
//			if(isMyBullet){
				fire();
				sound2.playSound();
				sound2=new Music("src//music//fire.wav");
//			}
//			isMyBullet = false;
			break;
		case KeyEvent.VK_UP://up key
			u=false;
			break;
		case KeyEvent.VK_DOWN://down key
			d=false;
			break;
		case KeyEvent.VK_LEFT://left key
			l=false;
			break;
		case KeyEvent.VK_RIGHT://right key
			r=false;
			break;
		case KeyEvent.VK_SPACE://three chances to clear the all bullets in the screen
			if(clearBullet>0){
			for (int i = 0; i < gp.bullets.size(); i++) {
				gp.bullets.get(i).setLive(false);
				}
			clearBullet--;
			}
			break;
		default:
			break;
		}
		decideDirection();
	}
	//collide with some walls which can't move
	public boolean collideWithCommon(WallObject c){//多态
		if(this.live==true && this.getRect().intersects(c.getRect()) ){
			backToOldDir();
			return true;
		}
		return false;
	}
	public boolean collideWithTank(List<myTank> TankList){
		for (int i = 0; i < TankList.size(); i++) {
			myTank tank=TankList.get(i);
			if(this!=tank){
				if(this.live && tank.isLive()&&this.getRect().intersects(tank.getRect())){
					this.backToOldDir();
					tank.backToOldDir();
					return true;
				}
			}
		}
		return false;
	}
	//hit with property
	public boolean collideWithProperty(myProperty p){
		if(this.live==true && p.getLive()==true && this.getRect().intersects(p.getRect())){
			if(p.getFlag()==1){
				p.setLive(false);
				gp.properties.remove(p);
				gp.homeTank.setSize();

				sound.playSound();
				sound=new Music("src//music//add.wav");
				
			return true;
			}
			if(p.getFlag()==2){
				sound.playSound();
				sound=new Music("src//music//add.wav");
				p.setLive(false);
				gp.properties.remove(p);
				gp.homeTank.setSpeedX(2);
				gp.homeTank.setSpeedY(2);
				bulletSpeedUpFlag=false;
				return true;
				}
			if(p.getFlag()==3){
				sound.playSound();
				sound=new Music("src//music//add.wav");
				for (int i = 0; i < gp.enemyTanks.size(); i++) {
					p.setLive(false);
					gp.properties.remove(p);
					int x=gp.enemyTanks.get(i).x-gp.homeTank.getX();
					int y=gp.enemyTanks.get(i).y-gp.homeTank.getY();
					if(Math.sqrt(x*x+y*y)< 400){
						gp.enemyTanks.get(i).setLive(false);
						bomb=new Bomb(gp.enemyTanks.get(i).getX(),gp.enemyTanks.get(i).getY(),gp);
						gp.bombs.add(bomb);
					}
				}
			}
			if(p.getFlag()==4){
				sound.playSound();
				sound=new Music("src//music//add.wav");

				p.setLive(false);
				gp.properties.remove(p);
				gp.metalFlag=true;
				return true;
				}
		}
		return false;
	}
	private void setSize() {
		this.width = (int)(20/1920.0*Constant.screenWidth);
		this.height = (int)(20/1080.0*Constant.screenHeight);
	}
	private void backToOldDir() {
		x=oldX;
		y=oldY;
	}
	public Rectangle getRect(){
		Rectangle r=new Rectangle(x,y,width,height);
		return r;
	}
	public boolean isLive(){
		return live;
	}
	public void setLive(boolean b){
		this.live=b;
	}
	public boolean isMine(){
		return mine;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setSpeedX(int x){
		speedX=x;
	}
	public void setSpeedY(int y){
		speedY=y;
	}


}
