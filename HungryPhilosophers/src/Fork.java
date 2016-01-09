import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
	public int id;
	
	final Lock lock = new ReentrantLock();
	
	Fork(int id){
		this.id=id;
	}
}
