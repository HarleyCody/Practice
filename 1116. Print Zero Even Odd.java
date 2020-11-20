____________________________________________________________________________________Semaphore______________________________________________________________________
// Semaphore cannot synchronize class variable, so it has to maintain its function variable;
// Release and acquire in order
import java.util.concurrent.*;
class ZeroEvenOdd {
    int n;
    
    Semaphore semaphoreEven, semaphoreOdd, semaphoreZero;
    
    public ZeroEvenOdd(int n) {
        this.n = n;
        
        semaphoreZero = new Semaphore(1);
        semaphoreEven = new Semaphore(0);
        semaphoreOdd = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        int numTimes = this.n;
        boolean printOdd = true;
        
        for(int i = 0; i < numTimes; i++){
            semaphoreZero.acquire();
			
            printNumber.accept(0);

            //print the next number
            if(printOdd)
                semaphoreOdd.release();
            else
                semaphoreEven.release();
            
            printOdd = !printOdd;   //flip it!
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        int numTimes = this.n / 2;
        
        int nextEvenNum = 2;
        for(int i = 0; i < numTimes; i++){
            semaphoreEven.acquire();
            
            printNumber.accept(nextEvenNum);
            nextEvenNum += 2;
            
            semaphoreZero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        int numTimes = (int)Math.ceil((double)this.n / 2);
        
        int nextOdd = 1;
        for(int i = 0; i < numTimes; i++){
            semaphoreOdd.acquire();
            
            printNumber.accept(nextOdd);
            nextOdd += 2;
            
            semaphoreZero.release();
        }
    }
}
____________________________________________________________________________________My Solution______________________________________________________________________
// Synchronized
// invalid -> wait; valid -> execute and notifyAll
class ZeroEvenOdd {
    private int n;
    private int x = 1;
    boolean isZero = true;
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        while(x <= n ){
            if(!isZero){
                wait();
                continue;
            }
            printNumber.accept(0);
            isZero = false;
            notifyAll();
        }
        
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        while(x <= n){
            if(x % 2 != 0 || isZero){
                wait();
                continue;
            }
            printNumber.accept(x);
            isZero = true;
            ++x;
            notifyAll();
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        while(x <= n){
            if(x % 2 != 1 || isZero){
                wait();
                continue;
            }
            printNumber.accept(x);
            isZero = true;
            ++x;
            notifyAll();
        }
    }
}
