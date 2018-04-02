package TankWar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;




public class StartPanel extends JPanel {

	private TankWarClient tc;
	private GamePanel gp;
	
	Music sound;
	
	public StartPanel(final TankWarClient tc,final GamePanel gp) {
		
		sound = new Music("src//music//start.wav");

		this.tc = tc;
		this.gp = gp;
		/**
		 *   新的Panel
		 */
		
		final MyPanel initial = new MyPanel("images/timg.gif");
		final MyPanel introduction = new MyPanel("images/ins.jpg");
		JButton back = new JButton();
		back.setText("BACK");
		back.setBounds((int)((1250/1920.0) * Constant.screenWidth), (int)((850/1080.0) * Constant.screenHeight), (int)((300/1920.0) * Constant.screenWidth), (int)((100/1080.0) * Constant.screenHeight));
		back.setBackground(new Color(90, 90, 90));
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Courier New", Font.BOLD, 22));
		back.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				tc.remove(introduction);
				tc.getContentPane().add(initial);
				initial.setVisible(true);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		introduction.add(back);

		/**
		 *  将 button 加入在 initial 中
		 */
		final MyPanel start = new MyPanel("images/start.gif", 80, 340, 240, 70);
		final MyPanel instructions = new MyPanel("images/instructions.gif", 80, 480, 240, 70);
		
		/**
		 * 将2个按钮放入initial中
		 */
		initial.add(start);		
		initial.add(instructions);		
        
		/**
		 * 将界面设置为开始界面
		 */
		tc.getContentPane().add(initial);
		
		/**
		 * 鼠标点击事件
		 */	
		start.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//点击开始游戏按钮后跳转到游戏界面
				gp.beginTime=new Date();
				tc.jmb.setVisible(true);
				initial.setVisible(false);
				tc.remove(initial);
				tc.getContentPane().add(gp);
//				new Thread(new startmusicThread()).start();
				sound.playSound();
				gp.setVisible(true);
				
			}
			
			
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				start.setBorder(BorderFactory.createRaisedBevelBorder());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				start.setBorder(null);
			}
			
		});
		
		
		
		
		instructions.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//点击
				initial.setVisible(false);
				tc.remove(initial);
				tc.getContentPane().add(introduction);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				instructions.setBorder(BorderFactory.createRaisedBevelBorder());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				instructions.setBorder(null);
			}
			
		});
		
	
	}



}
