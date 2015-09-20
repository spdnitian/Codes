package freelancer;

import java.util.ArrayList;


class Producer implements Runnable{
	
	Buffer buffer;
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			buffer.add(i);
		}
	}
	
	Producer(Buffer bfr){
		buffer = bfr;
	}
	
}

class Consumer implements Runnable{
	
	Buffer buffer;
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			buffer.remove();
		}
	}
	
	public Consumer(Buffer bfr) {
		buffer = bfr;
	}
	
}

class Buffer{
	private ArrayList<Integer> buffer = new ArrayList<Integer>();
	private int size;
	private int maxSize = 10;
	
	synchronized public void add(int data){
		if(size < maxSize){
			buffer.add(data);
			System.out.println("Added "+ data);
			size++;
			notifyAll();
		}else {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("Got interrupted while waiting to produce.");
			}
		}
	}
	
	synchronized public void remove(){
		if(size > 0){
			int data = buffer.remove(size - 1);
			System.out.println("Got "+ data);
			size--;
			notifyAll();
		}else {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("Got interrupted while waiting to consume.");
			}
		}
	}
}

class ProducerConsumerProblem{
	public static void PC() {
		Buffer bfr = new Buffer();
		Producer producer = new Producer(bfr);
		Consumer consumer = new Consumer(bfr);
		Thread pThread = new Thread(producer);
		Thread cThread = new Thread(consumer);
		pThread.start();
		cThread.start();
	}
}

class NewThreads implements Runnable{
	long sleepTime;
	static volatile int val;
	
	public NewThreads(long sleepTime) {
		this.sleepTime = sleepTime;
	}
	@Override
	public void run() {
		/*try {
			Thread.sleep(this.sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Child Thread " + Thread.currentThread().getName() + " Exited");*/
		/*while (true) {
			Thread.yield();
			System.out.println("Child Thread " + Thread.currentThread().getName());
		}*/
		try {
			val++;
			Thread.sleep(this.sleepTime);
			System.out.println(val);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

public class Threading {
	public static void main(String[] args) throws InterruptedException {
		//ProducerConsumerProblem.PC();
		Thread t1 = new Thread(new NewThreads(3000), "thread1");
		Thread t2 = new Thread(new NewThreads(2000), "thread2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Main Thread exited.");
	}
}
