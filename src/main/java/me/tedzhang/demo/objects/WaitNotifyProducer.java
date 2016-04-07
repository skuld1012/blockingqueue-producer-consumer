package me.tedzhang.demo.objects;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WaitNotifyProducer implements Runnable {

	private final Vector<Integer> sharedQueue;

	private final int size;

	public WaitNotifyProducer(Vector<Integer> sharedQueue, int size) {
		this.sharedQueue = sharedQueue;
		this.size = size;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println("Produced: " + i);
				produce(i);
			} catch (InterruptedException ex) {
				Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex.getMessage());
			}
		}
	}

	private void produce(int i) throws InterruptedException {
		while (sharedQueue.size() == this.size) {
			synchronized (sharedQueue) {
				System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: "
						+ sharedQueue.size());

				sharedQueue.wait();
			}
		}

		synchronized (sharedQueue) {
			sharedQueue.add(i);
			sharedQueue.notifyAll();
		}
	}
}
