package com.webserver.threadpool;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * @version 1.0
 * @author Cole Willison
 * 
 * This is an a container so that the applet can run as a stand alone
 * application.
 * 
 * */
public class AppletContainer {
	
	public static void main(String[] args) {
		final WebServer applet = new WebServer();
		
		applet.init();//configure applet
		
		//create a frame and fill it with the applet
		JFrame frame = new JFrame();
		frame.setTitle("Threadpool - Cole Willison");
		frame.setLayout(new FlowLayout());
		frame.setSize(1200, 450);
		frame.add(applet);
		frame.setVisible(true);
		
		applet.start();//start applet
		
		
		//needed to properly stop threads
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				applet.stop();
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	

}
