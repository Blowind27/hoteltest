package testing;

import hotel.DP;
import hotel.ListHome;
public class TestMain implements Runnable{
	private static int threadCount=50000;
	private int iterationCount=5;
	private int pacingTime=50;
	private int thinkTime=100;
	
	public void run(){
		ListHome lh=new ListHome(this.thinkTime);
		for(int i=1;i<iterationCount;i++)
			lh.search();
		   try{Thread.sleep(pacingTime);
		   	} catch(Exception e){}
		  
	}
	public static void main(String[] args) throws Exception {
		Runnable r=new TestMain();
		DP dp=new DP();
		dp.iniRooms();
		for(int i=0;i<threadCount;i++){
			Thread thread=new Thread(r);
			thread.start();
		}
	}

}
