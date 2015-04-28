package com.webserver.threadpool;

import concurrency.display.StringCanvas;

/**
 * @version 1.0
 * @author Cole Willison
 * 
 *         This is a monitor class that delegates the thread pool.
 * 
 * */
public class ThreadPoolControl {
	private int threads;
	private int max_threads;

	private StringCanvas[] views;

	/**
	 * @param int numberThreads maximum number of threads in the thread pool
	 * @param StringCanvas[] c Array of StringCanvas objects, used to change the strings
	 *            being display in the window
	 * 
	 * */
	public ThreadPoolControl(int numberThreads, StringCanvas[] c) {
		this.threads = this.max_threads = numberThreads;
		this.views = c;

	}

	/**
	 * Attains a free thread when available
	 * 
	 * @param int userID ID of the web user
	 * @param int accessCount number of times the web user has accessed the
	 *        system
	 * 
	 * @return void
	 * 
	 * */
	synchronized void getFreeThread(int userID, int accessCount) throws InterruptedException {
		while (threads == 0)
			wait();
		--threads;

		views[threads].setString(String.format("UserID: %d Access Count: %d", userID, accessCount));
		notifyAll();
	}

	/**
	 * 
	 * Free thread
	 * 
	 * @return void
	 * 
	 * */
	synchronized void freeThread() throws InterruptedException {
		while (threads == max_threads)
			wait();
		views[threads].setString("Thread Free");
		++threads;
		notifyAll();
	}
}
