package me.tedzhang.demo.objects;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WaitNotifyConsumer implements Runnable {

	private final Vector<Integer> sharedObject;

	public WaitNotifyConsumer(Vector<Integer> sharedObject) {
		this.sharedObject = sharedObject;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Consumed: " + consume());
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private int consume() throws InterruptedException {
		while (sharedObject.isEmpty()) {
			synchronized (this.sharedObject) {
				try {
					System.out.println("Consumer is waiting...");
					sharedObject.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		synchronized (this.sharedObject) {
			sharedObject.notifyAll();
			return sharedObject.remove(0);
		}
	}

}
