package me.tedzhang.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.tedzhang.demo.objects.ClosableBlockingQueue;
import me.tedzhang.demo.objects.Consumer;
import me.tedzhang.demo.objects.Producer;

public class RunApp {

	public static void main(String[] args) {
		BlockingQueue<Integer> sharedQueue = new ClosableBlockingQueue<>();
		
		ExecutorService ecs = Executors.newCachedThreadPool();
		ecs.execute(new Consumer(sharedQueue));
		ecs.execute(new Producer(sharedQueue));
		ecs.shutdown();
		
		Map<String, String> map = new HashMap<>();
		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");
	}

}
