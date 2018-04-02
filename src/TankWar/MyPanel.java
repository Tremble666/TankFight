package TankWar;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
	private Image img;
	private String path;
	
	public MyPanel(String path){
		this.setLayout(null);
		this.path = path;
		repaint();
	}
	public MyPanel(String path, int x, int y, int width, int height) {
		this.setBackground(null);
		this.setOpaque(false);
		this.setLayout(null);
		this.path = path;
		this.setBounds(x, y, width, height);
	}
	
	public void paintComponent(Graphics g) {
		
		img = imgUtil.getImage(path);
		super.paintComponent(g);
		// ����������Ϊ�˱���ͼƬ���Ը��洰�����е�����С�������Լ����óɹ̶���С
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	

}
