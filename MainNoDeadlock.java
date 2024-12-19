public class MainNoDeadlock {
    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();
    public static Object Lock3 = new Object();

    public static void main(String args[]) {
        Thread1 T1 = new Thread1();
        Thread2 T2 = new Thread2();
        Thread3 T3 = new Thread3();
        T1.start();
        T2.start();
        T3.start();
    }

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try { 
                	Thread.sleep(10); 
                }
                catch (InterruptedException e) {} 
                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                    try { 
                    	Thread.sleep(10); 
                    }
                    catch (InterruptedException e) {}
                    System.out.println("Thread 1: Waiting for lock 3...");
                    synchronized (Lock3) {
                        System.out.println("Thread 1: Holding lock 1, 2 & 3...");
                    }
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 2: Holding lock 2...");
                try { 
                	Thread.sleep(10); 
                }
                catch (InterruptedException e) {}
                System.out.println("Thread 2: Waiting for lock 3...");
                synchronized (Lock2) {
                    System.out.println("Thread 2: Holding lock 2 & 3...");
                    try { 
                    	Thread.sleep(10); 
                    }
                    catch (InterruptedException e) {}
                    System.out.println("Thread 2: Waiting for lock 1...");
                    synchronized (Lock3) {
                        System.out.println("Thread 2: Holding lock 1, 2 & 3...");
                    }
                }
            }
        }
    }

    private static class Thread3 extends Thread {
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 3: Holding lock 3...");
                try { 
                	Thread.sleep(10); 
                }
                catch (InterruptedException e) {}
                System.out.println("Thread 3: Waiting for lock 1...");
                synchronized (Lock2) {
                    System.out.println("Thread 3: Holding lock 1 & 3...");
                    try { 
                    	Thread.sleep(10); 
                    }
                    catch (InterruptedException e) {}
                    System.out.println("Thread 3: Waiting for lock 2...");
                    synchronized (Lock3) {
                        System.out.println("Thread 3: Holding lock 1, 2 & 3...");
                    }
                }
            }
        }
    }
}
