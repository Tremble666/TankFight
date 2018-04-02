package TankWar;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Constant {
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screensize = kit.getScreenSize();
	public static int screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
	public static int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height); 

	public static final int FRAME_WIDTH = (int)((1600/1920.0) * screenWidth);
	public static final int FRAME_HEIGHT = (int)((1000/1080.0) * screenHeight);

}
