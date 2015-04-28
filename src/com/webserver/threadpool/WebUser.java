package com.webserver.threadpool;

import java.util.Random;

import concurrency.display.ThreadPanel;

/**
 * @version 1.0
 * @author Cole Willison
 * 
 * This class represents a web user on the server. It requests a thread then sleeps to simulate
 * work.
 * 
 * */
public class WebUser implements Runnable {
	private ThreadPoolControl threadPoolControl;
	private int userID;
	
	private int accessCount = 0;
	private Random rand = new Random();

	public WebUser(ThreadPoolControl t, int userID) {
		this.threadPoolControl = t;
		this.userID = userID;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				
				threadPoolControl.getFreeThread(userID, accessCount);
				
				//simulate time required to access inventory and accounting system
				ThreadPanel.rotate(500 + rand.nextInt(500));
				
				//keep track of how many times the user has accessed the system
				accessCount++;
				
				
				threadPoolControl.freeThread();
				
				//sleep thread so to show user inactivity
				Thread.sleep(5000 + rand.nextInt(5000));
				ThreadPanel.rotate(0);
				
			}
			
		} catch (InterruptedException e) {
			
		}	
	}

}
