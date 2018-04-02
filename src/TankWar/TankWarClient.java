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
	
	//�˵���
	JMenuBar jmb=null;
	//�˵����еĲ˵�
	JMenu jmb1,jmb2,jmb3,jmb4;
	//�˵��е���Ŀ
	JMenuItem jm1,jm2,jm3,jm4,jm5,jm6,jm7,jm8,jm9;
	
	// �ܷ���Ƶı���
	public boolean printable = true;
	
	/**
	 * ������
	 */
	public TankWarClient() {
		
		gp = new GamePanel(this , sp);
		sp = new StartPanel(this, gp);
	    this.addJMenu();
		this.launchFrame();
		this.addKeyListener(new keyMonitor()); // ���̼���
		new Thread(new PaintThread()).start();

		this.setVisible(true);
	}
	
	//ʵ�ֲ˵������˵����˵���Ŀ�ķ���
	public void addJMenu(){
		//�����˵�������Ͳ˵������Լ��˵���Ŀ����
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
					
		//���˵�����˵�����
		jmb.add(jmb1);
		jmb.add(jmb2);
		jmb.add(jmb3);
		jmb.add(jmb4);
					
		//���˵����������
		this.setJMenuBar(jmb);
					
		//���ò˵���ʾ����
		jmb1.setFont(new Font("TimesRoman",Font.BOLD,20));
		jmb2.setFont(new Font("TimesRoman",Font.BOLD,20));
		jmb3.setFont(new Font("TimesRoman",Font.BOLD,20));
		jmb4.setFont(new Font("TimesRoman",Font.BOLD,20));
						
		//����Ŀ����˵���
		jmb1.add(jm1);
		jmb1.add(jm2);
		jmb2.add(jm3);
		jmb2.add(jm4);
		jmb3.add(jm5);
		jmb3.add(jm6);
		jmb3.add(jm7);
		jmb4.add(jm9);
		
		//���¼�����ע���Լ�
		jm1.addActionListener(this);
		jm2.addActionListener(this);
		jm3.addActionListener(this);
		jm4.addActionListener(this);
		jm5.addActionListener(this);
		jm6.addActionListener(this);
		jm7.addActionListener(this);
		jm9.addActionListener(this);
		
		//���������Լ��ı�־
		jm1.setActionCommand("NewGame");
		jm2.setActionCommand("Exit");
		jm3.setActionCommand("stop");
		jm4.setActionCommand("continue");
		jm5.setActionCommand("level1");
		jm6.setActionCommand("level2");
		jm7.setActionCommand("level3");
		jm9.setActionCommand("help");
		//���ò˵���Ŀ��ʾ����
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
	 * �������
	 */
	public void launchFrame(){
		this.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
		this.setLocation(100, 10);
		this.setTitle("Tank War");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
    /**
     * ����¼��ļ���
     */
	public void actionPerformed(ActionEvent e) {
		Object[] options ={"Yes","No"};
		switch(e.getActionCommand()){
			case "NewGame"://��ʾ�����ʼ������Ϸ
				int response=JOptionPane.showOptionDialog(this, "Are You going to open a new game��", "New Game", JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				//������Ӧ�������Ӧ����
				if(response==0){
					//��ʼ����Ϸ
					this.dispose();
					new TankWarClient();
				}else{
					//ȡ��
				}
				break;
			case "Exit":
				int res=JOptionPane.showOptionDialog(this, "Are You going to exit��", "", JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				//������Ӧ�������Ӧ����
				if(res==0){
					//�˳�
					System.exit(0);
				}else{
					//ȡ��
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
				// ���»��Ʋ���
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
