package TankWar;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	
	private TankWarClient tc;
	private StartPanel sp;

	//敌方坦克的数量
	public static int enemyTanksNum=13;
	

	
	//时间记录显示
	Date beginTime,endWinTime,endLoseTime;
	long duringTime;
	
	//是否是砖墙(用于道具)
	boolean metalFlag=false;
	

	
	// a random for properties
	Random random=new Random();
	
	//集合
	List<myCommonWall>  commonwalls = new ArrayList<>();
	List<myRiverWall>  riverwalls = new ArrayList<>();
	List<myMetalWall>  metalwalls = new ArrayList<>();
	List<myTreeWall>  treewalls = new ArrayList<>();
	List<myCommonWall> homearoundwalls = new ArrayList<>();
	List<myTank> enemyTanks = new ArrayList<>();
	List<myBullet> bullets = new ArrayList<>();
	List<Bomb> bombs = new ArrayList<>();
	List<myMetalWall> homeAroundMetalWall = new ArrayList<>();
	List<myProperty> properties = new ArrayList<>();
	
//	myBullet mb = new myBullet();
	myTank homeTank=new myTank(600,880,true,Direction.stop,this);
	myHome myh=new myHome("src//images//home.jpg",(int)(712/1920.0*Constant.screenWidth),(int)(875/1080.0*Constant.screenHeight));;
	
	
	public GamePanel(TankWarClient tc,StartPanel sp) {
		this.tc = tc;
		this.sp = sp;
		this.insertWall();

	}
		



	public void paint(Graphics g) {
		
		super.paint(g);
		//添加背景图片
		Image background = new ImageIcon("src//images//14-160121114459619[1].jpg").getImage();
		g.drawImage(background, 0, 0,(int)((1700/1920.0)*Constant.screenWidth),(int)((1100-1080.0)*Constant.screenHeight), this);
		
		//draw properties
		for (int j = 0; j < properties.size(); j++) {
			
			if(duringTime>5 && duringTime <15){
				properties.get(j).setLive(true);
				properties.get(j).draw(g);
				homeTank.collideWithProperty(properties.get(j));}
			if(duringTime>25 && duringTime <40){
				properties.get(j).setLive(true);
				properties.get(j).draw(g);
				homeTank.collideWithProperty(properties.get(j));}
			if(duringTime>50 && duringTime <65){
				properties.get(j).setLive(true);
				properties.get(j).draw(g);
				homeTank.collideWithProperty(properties.get(j));}
			if(duringTime>75 && duringTime <85){
				properties.get(j).setLive(true);
				properties.get(j).draw(g);
				homeTank.collideWithProperty(properties.get(j));}
			if(duringTime>95 && duringTime <105){
				properties.get(j).setLive(true);
				properties.get(j).draw(g);
				homeTank.collideWithProperty(properties.get(j));}
			if(duringTime>115 && duringTime <125){
				properties.get(j).setLive(true);
				properties.get(j).draw(g);
				homeTank.collideWithProperty(properties.get(j));}
			if(duringTime>135 && duringTime <145){
				properties.get(j).setLive(true);
				properties.get(j).draw(g);
				homeTank.collideWithProperty(properties.get(j));}
			else{
//				properties.get(j).setLive(false);
			}
		}
		
//		show the time from begin time
		duringTime=(new Date().getTime()-beginTime.getTime())/1000;
		
		//if you lose ,show message
		if(homeTank.isLive()==false || myh.isLive()==false){
			endLoseTime=new Date();
			long duringLoseTime=(endLoseTime.getTime()-beginTime.getTime())/1000;
			printInfo(g, "TimesRoman", "YOU LOSE!", Color.yellow, 100, 450, 420);
			printInfo(g, "TimesRoman", "Time:"+duringLoseTime+" seconds", Color.yellow, 50
					, 540, 500);
			printInfo(g, "TimesRoman", "Level: "+"Rookie", Color.yellow, 50, 560, 600);
			clearAll();

		}
		
		//if you win,show you win
		if(homeTank.isLive()==true && enemyTanks.size()==0 && myh.isLive()==true){
			endWinTime=new Date();
			long duringLoseTime=(endWinTime.getTime()-beginTime.getTime())/1000;
			printInfo(g, "TimesRoman", "YOU WIN!", Color.green, 100, 500, 420);
			printInfo(g, "TimesRoman", "Time:"+duringLoseTime+" seconds", Color.green, 50, 540, 500);
			if(duringTime<30){
				printInfo(g, "TimesRoman", "Level: "+"King", Color.green, 50, 590, 580);
			}else if(duringTime<60){
				printInfo(g, "TimesRoman", "Level: "+"Master", Color.green, 50, 590, 580); 
			}else if(duringTime<90){
				printInfo(g, "TimesRoman", "Level: "+"Elite", Color.green, 50, 590, 580);
			}else{
				printInfo(g, "TimesRoman", "Level: "+"Rookie", Color.green, 50, 590, 580);
			}
			clearAll();
		}
		//commonwall.draw(g);
		for (int i = 0; i < commonwalls.size(); i++) {
			for (int j = 0; j < enemyTanks.size(); j++) {
				enemyTanks.get(j).collideWithCommon(commonwalls.get(i));
			}
			homeTank.collideWithCommon(commonwalls.get(i));
			commonwalls.get(i).draw(g);
		}
		//bullet hit others
		
		
		
		//子弹碰撞
//		for (int i = 0; i < bullets.size(); i++) {
//			for (int j = 0; j < bullets.size(); j++) {
//				if(i!=j){
//					bullets.get(i).hitBullet(bullets.get(j));
//				}
//				
//			}
//		}
		//riverwall.draw(g);
		for (int i = 0; i < riverwalls.size(); i++) {
			for (int j = 0; j < enemyTanks.size(); j++) {
				enemyTanks.get(j).collideWithCommon(riverwalls.get(i));
			}
			homeTank.collideWithCommon(riverwalls.get(i));
			riverwalls.get(i).draw(g);
		}
		for (int i = 0; i < bullets.size(); i++) {
			//hit with tanks
			bullets.get(i).hitTanks(enemyTanks);
			bullets.get(i).hitTank(homeTank);
			
			//hit with common walls
			for (int j = 0; j < commonwalls.size(); j++) {
				bullets.get(i).hitCommon(commonwalls.get(j));
			}
			//hit with home walls
			for (int j = 0; j < homearoundwalls.size(); j++) {
				bullets.get(i).hitCommon(homearoundwalls.get(j));
			}
			//hit with common walls
			for (int j = 0; j < metalwalls.size(); j++) {
				bullets.get(i).hitMetal(metalwalls.get(j));
			}
			//hit with home metal walls
			if(metalFlag==true){
			for (int j = 0; j < homeAroundMetalWall.size(); j++) {
				bullets.get(i).hitMetal(homeAroundMetalWall.get(j));
			}
			}
			//hit home
			bullets.get(i).hitHome(myh);
			bullets.get(i).draw(g);
		}
	
		//metalwall.draw(g);
		for (int i = 0; i < metalwalls.size(); i++) {
			for (int j = 0; j < enemyTanks.size(); j++) {
				enemyTanks.get(j).collideWithCommon(metalwalls.get(i));
			}
			homeTank.collideWithCommon(metalwalls.get(i));
			metalwalls.get(i).draw(g);
		}
		//insert the picture of Tank
		homeTank.draw(g);
		//enemy tanks'.draw(g)
		for (int i = 0; i < enemyTanks.size(); i++) {
			enemyTanks.get(i).collideWithTank(enemyTanks);
			enemyTanks.get(i).draw(g);
		}
		//treewall.draw(g);
		for (int i = 0; i < treewalls.size(); i++) {
			treewalls.get(i).draw(g);
		}
		//homewall.draw(g);
		for (int i = 0; i < homearoundwalls.size(); i++) {
			for (int j = 0; j < enemyTanks.size(); j++) {
				enemyTanks.get(j).collideWithCommon(homearoundwalls.get(i));
			}
			homeTank.collideWithCommon((homearoundwalls.get(i)));
			homearoundwalls.get(i).draw(g);
		}
		//homemetalwall.draw(g);
		if(metalFlag==true){
		for (int i = 0; i < homeAroundMetalWall.size(); i++) {
			for (int j = 0; j < enemyTanks.size(); j++) {
				enemyTanks.get(j).collideWithCommon(homeAroundMetalWall.get(i));
			}
			homeTank.collideWithCommon((homeAroundMetalWall.get(i)));
			homeAroundMetalWall.get(i).draw(g);
		}
		}
		//bomb.draw(g)
		for (int i = 0; i < bombs.size(); i++) {
			bombs.get(i).draw(g);
		}
		//show the time
		printInfo(g, "宋体", "Time:"+duringTime+" seconds", Color.red, 30, 1330, 40);
		
		homeTank.collideWithCommon(myh);
		//enemy tank collide with home
		for (int j = 0; j < enemyTanks.size(); j++) {
			enemyTanks.get(j).collideWithCommon(myh);
		}
		
		//between tanks' collide
		homeTank.collideWithTank(enemyTanks);
		myh.draw(g);
	}
		
		
	

	public void insertWall(){
		insertCommonWall();
		insertRiverWall();
		insertMetalWall();
		insertTreeWall();
		insertHomeWall();
		insertEnemyTank();
		insertHomeMetalWall();
		insertProperty();
	}
	//add common wall to List
	private void insertCommonWall(){
		for (int i = 0; i < 37; i++) {
			
			if (i < 16) {
			commonwalls.add(new myCommonWall("src//images//commonWall.gif",(int)((600 + 20 * i)/1920.0*Constant.screenWidth), (int)(160/1080.0*Constant.screenHeight)));
			
			for(int a = 0; a <= 7; a++){
				commonwalls.add(new myCommonWall("src//images//commonWall.gif",(int)((100+a*200)/1920.0*Constant.screenWidth), (int)((400 + 20 * (i-2))/1080.0*Constant.screenHeight)));
			}
			
			}
			
		 else if (i < 32) {
			commonwalls.add(new myCommonWall("src//images//commonWall.gif",(int)((600 + 20 * (i - 16))/1920.0*Constant.screenWidth), (int)(200/1080.0*Constant.screenHeight)));
			for(int b = 0;b <= 7; b++){
				commonwalls.add(new myCommonWall("src//images//commonWall.gif",(int)((120+b*200)/1920.0*Constant.screenWidth), (int)((400 + 20 * (i - 18))/1080.0*Constant.screenHeight)));
			}
		 }
	}
	}
	//add river wall to List
	private void insertRiverWall(){
		riverwalls.add(new myRiverWall("src//images//river.jpg",(int)(85/1920.0*Constant.screenWidth), (int)(100/1080.0*Constant.screenHeight)));
		riverwalls.add(new myRiverWall("src//images//river.jpg",(int)(1405/1920.0*Constant.screenWidth), (int)(100/1080.0*Constant.screenHeight)));
	}
	//add metal wall to List
	private void insertMetalWall(){
		for (int i = 0; i < 30; i++) {
			if (i < 10) {
				metalwalls.add(new myMetalWall("src//images//metalWall.gif",(int)((140 + 30 * i)/1920.0*Constant.screenWidth), (int)(150/1080.0*Constant.screenHeight)));
				metalwalls.add(new myMetalWall("src//images//metalWall.gif",(int)((1100 + 30 * i)/1920.0*Constant.screenWidth), (int)(150/1080.0*Constant.screenHeight)));
				metalwalls.add(new myMetalWall("src//images//metalWall.gif",(int)((1100 + 30 * i)/1920.0*Constant.screenWidth), (int)(185/1080.0*Constant.screenHeight)));
				
				for(int a = 0;a <= 6;a++){
					metalwalls.add(new myMetalWall("src//images//metalWall.gif",(int)((200+a*200)/1920.0*Constant.screenWidth), (int)((400+20*i)/1080.0*Constant.screenHeight)));

				}
			} else if (i < 20)
				metalwalls.add(new myMetalWall("src//images//metalWall.gif",(int)((140 + 30 * (i - 10))/1920.0*Constant.screenWidth), (int)(180/1080.0*Constant.screenHeight)));
	}
	}
	//add river wall to List
	private void insertTreeWall(){
		for (int i = 0; i < 8; i++) {
			if(i<4){
				for(int a = 0; a <= 6; a++){
					treewalls.add(new myTreeWall("src//images//tree.gif",(int)(((50+a*220) + 30 * i)/1920.0*Constant.screenWidth), (int)(310/1080.0*Constant.screenHeight)));
				}
			}
			else if(i<8){
				for(int b = 0; b <= 6;b++){
					treewalls.add(new myTreeWall("src//images//tree.gif",(int)(((50+b*220) + 30 * (i-4))/1920.0*Constant.screenWidth), (int)(710/1080.0*Constant.screenHeight)));
					
				}
			}
		}
	}

	//add home around wall to List
		private void insertHomeWall(){
			for (int i = 0; i < 10; i++) { // 家的格局
				if (i < 4)
					homearoundwalls.add(new myCommonWall("src//images//commonWall.gif",(int)(690/1920.0*Constant.screenWidth), (int)(((907 - 21 * i))/1080.0*Constant.screenHeight)));
				else if (i < 7)
					homearoundwalls.add(new myCommonWall("src//images//commonWall.gif",(int)((712 + 22 * (i - 4))/1920.0*Constant.screenWidth), (int)(844/1080.0*Constant.screenHeight)));
				else
					homearoundwalls.add(new myCommonWall("src//images//commonWall.gif",(int)(756/1920.0*Constant.screenWidth), (int)((865 + (i - 7) * 21)/1080.0*Constant.screenHeight)));
				}
		}
		//add home around metal wall to List
		private void insertHomeMetalWall(){
			for (int i = 0; i < 10; i++) { // 家的格局
				if (i < 4)
					homeAroundMetalWall.add(new myMetalWall("src//images//metalWall.gif",(int)(675/1920.0*Constant.screenWidth), (int)((897 - 21 * i)/1080.0*Constant.screenHeight)));
				else if (i < 7)
					homeAroundMetalWall.add(new myMetalWall("src//images//metalWall.gif",(int)((711 + 22 * (i - 4))/1920.0*Constant.screenWidth), (int)(834/1080.0*Constant.screenHeight)));
				else
					homeAroundMetalWall.add(new myMetalWall("src//images//metalWall.gif",(int)(755/1920.0*Constant.screenWidth), (int)((865 + (i - 7) * 21)/1080.0*Constant.screenHeight)));
			}
		}
		//add enemy tanks to List
		private void insertEnemyTank(){  
			for (int i = 0; i < enemyTanksNum; i++) {
				if (i < 9) { 
					enemyTanks.add(new myTank((int)((600 + 70 * (i-4))/1920.0*Constant.screenWidth), (int)(40/1080.0*Constant.screenHeight), false, Direction.right, this));
				} else if (i < 15) {
					enemyTanks.add(new myTank((int)(1500/1920.0*Constant.screenWidth), (int)((140 + 50 * (i - 10))/1080.0*Constant.screenHeight), false, Direction.left, this));
				} else if (i<20){
					enemyTanks.add(new myTank((int)(20/1920.0*Constant.screenWidth), (int)((55 * (i - 12))/1080.0*Constant.screenHeight), false, Direction.right, this));
				}  else{
					enemyTanks.add(new myTank((int)((900 + 70 * (i-20))/1920.0*Constant.screenWidth), (int)(300/1080.0*Constant.screenHeight), false, Direction.left, this));
					enemyTanks.add(new myTank((int)((200 + 70 * (i-21))/1920.0*Constant.screenWidth), (int)(300/1080.0*Constant.screenHeight), false, Direction.right, this));
				}
				}
			}
		private void insertProperty(){
			for (int i = 0; i < 4; i++) {
				int x=random.nextInt((int)(1550/1920.0*Constant.screenWidth));
				int y=random.nextInt((int)(950/1080.0*Constant.screenHeight));
				int propertyFlag=random.nextInt(4)+1;
				properties.add(new myProperty(x, y, true,propertyFlag, this));
				properties.add(new myProperty(x, y, true,propertyFlag, this));
				properties.add(new myProperty(x, y, true,propertyFlag, this));
				properties.add(new myProperty(x, y, true,propertyFlag, this));
			}
		}
	

		//show the string that you want to show
		public void printInfo(Graphics g,String strStyle,String str,Color color,int size,int x,int y){
			Font f=new Font(strStyle,Font.BOLD,size);
			Color c=g.getColor();
			g.setColor(color);
			g.setFont(f);
			g.drawString(str,x,y);
			g.setColor(c);
		}
		//over ,clear all
		public void clearAll(){
			enemyTanks.clear();
			bullets.clear();
			commonwalls.clear();
			metalwalls.clear();
			treewalls.clear();
			riverwalls.clear();
			properties.clear();
			homearoundwalls.clear();
			bombs.clear();
			tc.printable=false;
			if(metalFlag){
				homeAroundMetalWall.clear();
			}
		}
	
}
