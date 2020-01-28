import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {
	
    public static void main(String[] args) {
    	
    	Thread []philosophers = new Thread[5];
    	Fork []forks = new Fork[philosophers.length];
    	
		for(int i=0;i<philosophers.length;i++)
			forks[i] = new Fork(i);
		
		for(int i=0; i<philosophers.length;i++){
			if(i<philosophers.length-1)
				philosophers[i]=new Thread(new Philosopher("philosopher"+i, i, forks[i], forks[i+1]));
			else
				philosophers[i]=new Thread(new Philosopher("philosopher"+i, i, forks[i], forks[0]));
		}
		
		

	    System.out.println("Starting threads");
	    for(int i=0; i<philosophers.length;i++){
			philosophers[i].start();
		};

		System.out.println("Waiting for threads");
	    try {
		    for(int i=0; i<philosophers.length;i++){
				philosophers[i].join();
			};
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}


}