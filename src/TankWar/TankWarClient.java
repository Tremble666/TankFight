package TankWar;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



public class TankWarClient extends JFrame implements ActionListener{

	StartPanel sp;
	GamePanel gp;
	
	//菜单栏
	JMenuBar jmb=null;
	//菜单栏中的菜单
	JMenu jmb1,jmb2,jmb3,jmb4;
	//菜单中的条目
	JMenuItem jm1,jm2,jm3,jm4,jm5,jm6,jm7,jm8,jm9;
	
	// 能否绘制的标量
	public boolean printable = true;
	
	/**
	 * 构造器
	 */
	public TankWarClient() {
		
		gp = new GamePanel(this , sp);
		sp = new StartPanel(this, gp);
	    this.addJMenu();
		this.launchFrame();
		this.addKeyListener(new keyMonitor()); // 键盘监听
		new Thread(new PaintThread()).start();

		this.setVisible(true);
	}
	
	//实现菜单栏，菜单，菜单条目的方法
	public void addJMenu(){
		//创建菜单栏对象和菜单对象以及菜单条目对象
		jmb=new JMenuBar();
		jmb.setVisible(false);
		jmb1=new JMenu("Game");
		jmb2=new JMenu("Stop--Continue");
		jmb3=new JMenu("Level");
		jmb4=new JMenu("Help");
		jm1=new JMenuItem("Begin");
		jm2=new JMenuItem("Over");
		jm3=new JMenuItem("Stop");
		jm4=new JMenuItem("Comtinue");
		jm5=new JMenuItem("Level1");
		jm6=new JMenuItem("Level2");
		jm7=new JMenuItem("Level3");
		jm9=new JMenuItem("Information");
					
		//将菜单放入菜单栏中
		jmb.add(jmb1);
		jmb.add(jmb2);
		jmb.add(jmb3);
		jmb.add(jmb4);
					
		//将菜单栏放入框体
		this.setJMenuBar(jmb);
					
		//设置菜单显示字体
		jmb1.setFont(new Font("TimesRoman",Font.BOLD,20));
		jmb2.setFont(new Font("TimesRoman",Font.BOLD,20));
		jmb3.setFont(new Font("TimesRoman",Font.BOLD,20));
		jmb4.setFont(new Font("TimesRoman",Font.BOLD,20));
						
		//将条目放入菜单中
		jmb1.add(jm1);
		jmb1.add(jm2);
		jmb2.add(jm3);
		jmb2.add(jm4);
		jmb3.add(jm5);
		jmb3.add(jm6);
		jmb3.add(jm7);
		jmb4.add(jm9);
		
		//向事件监听注册自己
		jm1.addActionListener(this);
		jm2.addActionListener(this);
		jm3.addActionListener(this);
		jm4.addActionListener(this);
		jm5.addActionListener(this);
		jm6.addActionListener(this);
		jm7.addActionListener(this);
		jm9.addActionListener(this);
		
		//设置区分自己的标志
		jm1.setActionCommand("NewGame");
		jm2.setActionCommand("Exit");
		jm3.setActionCommand("stop");
		jm4.setActionCommand("continue");
		jm5.setActionCommand("level1");
		jm6.setActionCommand("level2");
		jm7.setActionCommand("level3");
		jm9.setActionCommand("help");
		//设置菜单条目显示字体
		jm1.setFont(new Font("TimesRoman",Font.BOLD,20));
		jm2.setFont(new Font("TimesRoman",Font.BOLD,20));
		jm3.setFont(new Font("TimesRoman",Font.BOLD,20));
		jm4.setFont(new Font("TimesRoman",Font.BOLD,20));
		jm5.setFont(new Font("TimesRoman",Font.BOLD,20));
		jm6.setFont(new Font("TimesRoman",Font.BOLD,20));
		jm7.setFont(new Font("TimesRoman",Font.BOLD,20));
		jm9.setFont(new Font("TimesRoman",Font.BOLD,20));			
		}
		
	
	public static void main(String[] args) {
		
		new TankWarClient();
	}
	
	
	/**
	 * 框架设置
	 */
	public void launchFrame(){
		this.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
		this.setLocation(100, 10);
		this.setTitle("Tank War");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
    /**
     * 点击事件的监听
     */
	public void actionPerformed(ActionEvent e) {
		Object[] options ={"Yes","No"};
		switch(e.getActionCommand()){
			case "NewGame"://表示点击开始了新游戏
				int response=JOptionPane.showOptionDialog(this, "Are You going to open a new game？", "New Game", JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				//根据相应结果做相应操作
				if(response==0){
					//开始新游戏
					this.dispose();
					new TankWarClient();
				}else{
					//取消
				}
				break;
			case "Exit":
				int res=JOptionPane.showOptionDialog(this, "Are You going to exit？", "", JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				//根据相应结果做相应操作
				if(res==0){
					//退出
					System.exit(0);
				}else{
					//取消
				}
				break;
			case "stop":
				printable=false;
				break;
			case "continue":
				if(!printable){
					printable=true;
					new Thread(new PaintThread()).start();
				}
				break;
			case "level1":
				this.dispose();
				new TankWarClient();
				break;
			case "level2":
				setLevel(16);
				this.dispose();
				new TankWarClient();
				break;
			case "level3":
				setLevel(23);
				this.dispose();
				new TankWarClient();
				break;
			case "help":
				JOptionPane.showMessageDialog(this, " F to fire,R to new game,space to clear bullets(three Times)");
				break;
				
			default:
				break;
				
		}
		
	}
	class keyMonitor extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			gp.homeTank.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			gp.homeTank.keyReleased(e);
		}
	}
	

	 class PaintThread implements Runnable {
		public void run() {
			while (printable) {
				// 重新绘制布局
				gp.repaint();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	 }
	

	private void setLevel(int number){
		GamePanel.enemyTanksNum=number;
	}
}
