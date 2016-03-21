package me.tedzhang.demo.objects;

import java.util.concurrent.LinkedBlockingQueue;

public class ClosableBlockingQueue<E> extends LinkedBlockingQueue<E> {

	private static final long serialVersionUID = -2621388920313830829L;
	
	private volatile boolean isEmpty = false;
	
	public void setIsEmpty(boolean isEmpty) { 
		this.isEmpty = isEmpty;
	}
	
	public boolean isEmpty() { 
		return this.isEmpty;
	}
}
