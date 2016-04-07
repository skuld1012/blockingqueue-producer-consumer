package me.tedzhang.demo;

import java.util.Vector;

import me.tedzhang.demo.objects.WaitNotifyConsumer;
import me.tedzhang.demo.objects.WaitNotifyProducer;

public class RunApp2 {

	public static void main(String[] args) {
		int SIZE = 4;
		Vector<Integer> sharedQueue = new Vector<>();
		
		Thread consumer = new Thread(new WaitNotifyConsumer(sharedQueue));
		Thread producer = new Thread(new WaitNotifyProducer(sharedQueue, SIZE));
		
		consumer.start();
		producer.start();
		
		System.out.println(0.1*3);
	}

}
