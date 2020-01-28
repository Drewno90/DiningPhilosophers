import java.util.Random;

class Philosopher implements Runnable {
		String threadName;
		int n;
		Fork leftFork, rightFork;
		
		public Philosopher(String threadName, int n, Fork leftFork, Fork rightFork) {
			this.threadName = threadName;
			this.n = n;
			this.leftFork=leftFork;
			this.rightFork=rightFork;
		}
		
        public boolean accessingFork(Fork leftFork, Fork rightFork) {
			boolean isLeftForkFree = false;
			boolean isRightForkFree = false;
            try {
            	isLeftForkFree = leftFork.lock.tryLock();
            	isRightForkFree = rightFork.lock.tryLock();
            } finally {
                if (! (isLeftForkFree && isRightForkFree)) {
                    if (isLeftForkFree) {
                    	leftFork.lock.unlock();
                    }
                    if (isRightForkFree) {
                    	rightFork.lock.unlock();
                    }
                }
            }
            return isLeftForkFree && isRightForkFree;
        }
        
        
        public void eat(Fork leftFork, Fork rightFork) {
            if (accessingFork(leftFork, rightFork)) {
                try {
                    System.out.println(this.threadName + ">I have both forks so it's time to eat!");
                    System.out.println(this.threadName + "> Yum Yum...");
                } finally {
                	leftFork.lock.unlock();
                	rightFork.lock.unlock();
                }
            } else {
                System.out.println(this.threadName + ">There is no enough forks for me... i have to wait");
              
            }
        }
		
		@Override
		public void run() {
            Random random = new Random();
            for (;;) {
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {}
                eat(leftFork, rightFork);
            }
        }
	}