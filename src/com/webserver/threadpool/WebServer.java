package com.webserver.threadpool;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;

import concurrency.display.StringCanvas;
import concurrency.display.ThreadPanel;

public class WebServer extends Applet {

	/**
	 * @author Cole Willison
	 * @studNum 1208917
	 * @version 1.0
	 * 
	 * This class implements the thread pool in an Applet
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int NUM_WEB_USERS = 6;
	private static final int NUM_THREADS = 4;

	private ThreadPanel[] webUsers = new ThreadPanel[NUM_WEB_USERS];
	private StringCanvas[] threadDisplay = new StringCanvas[NUM_THREADS];

	@Override
	public void init() {
		setLayout(new BorderLayout());
		
		GridBagLayout gridBag1 = new GridBagLayout();
		Panel p1 = new Panel();
		p1.setLayout(gridBag1);
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.NORTH;

		//create StringCanvas to represent threads
		for (int i = 0; i < NUM_THREADS; i++) {
			threadDisplay[i] = new StringCanvas("Thread " + i, Color.GREEN);
			threadDisplay[i].setString("Thread Free");
			threadDisplay[i].setSize(300,200);
			gridBag1.setConstraints(threadDisplay[i], gc);
			p1.add(threadDisplay[i]);
		}
		
		GridBagLayout gridBag2 = new GridBagLayout();
		Panel p2 = new Panel();
		p1.setLayout(gridBag1);
		
		//create ThreadPanels to represent web users
		for(int i = 0; i < NUM_WEB_USERS; i++) {
			webUsers[i] = new ThreadPanel("WebUser: " + i, Color.BLUE);
			webUsers[i].setSize(300*NUM_THREADS/6, 200);
			gridBag2.setConstraints(webUsers[i], gc);
			p2.add(webUsers[i]);			
		}
		
		add(p2, BorderLayout.SOUTH);
		add(p1, BorderLayout.NORTH);	
	}
	
	
	@Override
	public void start() {
		ThreadPoolControl tpc = new ThreadPoolControl(NUM_THREADS, threadDisplay);
		for (int i = 0; i < NUM_WEB_USERS; i++) {
			webUsers[i].start(new WebUser(tpc, i*10));
		}
	}
	
	@Override
	public void stop() {
		for (int i = 0; i < NUM_WEB_USERS; i++) {
			webUsers[i].stop();
		}
	}
	
}
