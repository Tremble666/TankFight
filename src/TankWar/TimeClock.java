package TankWar;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Calendar;  
import java.util.Date;  
import java.util.Timer;  
import java.util.TimerTask; 

public class TimeClock {

	public static void timer1() {
	    Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
	      public void run() {
	        System.out.println("-------设定要指定任务--------");
	      }
	    }, 2000);// 设定指定的时间time,此处为2000毫秒
	  }

}
