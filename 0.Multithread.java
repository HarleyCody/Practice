________________________________________________________________________Semaphore______________________________________________________________________
1. Donot need synchronized function
2. Cannot controlled by class variable(as it is not synchronized)
3. Have to maintain the function variable for the out put (loop to update)
4. If acquried, loop will be executed once;
5. Otherwise, it will keep acquiring;
6. Semaphore can be used for control the number of thread used, so it can assure the number of out put of thread.(eg, H20, HSemaphore(2), O(Semaphore(1)))
7. acquire: need how many thread to be able execute. release: release how many threads to pool; 
______________________________________________________________________Synchronized_________________________________________________________________________
1. Can control by class variable, used for condition
2. Loop in a while to determin the range is valid, use if to determine if the value is valid
3. invalid-> wait() + continue(ie check range again), valid() -> execute() + notifyAll();
