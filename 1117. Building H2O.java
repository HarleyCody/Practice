_________________________________________________________________________________Semaphore________________________________________________________________________________
// use thread to control numbers
// 2 threads for H 1 thread for O
import java.util.concurrent.*;
class H2O {
    
    Semaphore h, o;
    public H2O() {
        h = new Semaphore(2, true);
        o = new Semaphore(0, true);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		    h.acquire();
        releaseHydrogen.run();
        o.release();
        
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
		    releaseOxygen.run();
        h.release(2);
    }
}
___________________________________________________________________________________My Solution(Synchronized)__________________________________________________________
// Synchronized
// condition numO and numH
// invalid wait, valid execute and notifyAll
class H2O {
    int numO = 0, numH = 0;
    public H2O() {
        //Sephamore s = new Sephamore(1);
    }

    public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		while(2 * numO < numH){
            wait();
        }
        releaseHydrogen.run();
        ++numH;
        notifyAll();
    }

    public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
        while(2 * numO > numH){
            wait();
        } 
		    releaseOxygen.run();
        ++numO;
        notifyAll();
    }
}
