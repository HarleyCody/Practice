___________________________________________________________Best Solution________________________________________________________________
 class DinnerPlates {
    // use array to record all elements, if array is filled, extend array
    // use second array record size of everystack
    // exactly num == array[idx * cap + size[idx]];
    int[] stackdata; // array contains all stacks' data
    int[] stacksizes; // each elem is the size of the stack at an index
    PriorityQueue<Integer> availableStacks; // not full
    int numStacksCap; // number of stacks that stackdata can currently contain
    int nextNewStackIndex;
    int cap; // capacity for each stack instance
    
    public DinnerPlates(int capacity) {
        this.nextNewStackIndex = 0;
        this.cap = capacity;
        this.numStacksCap = 100; // initial capacity of stacks
        this.stackdata = new int[this.numStacksCap * this.cap];
        this.stacksizes = new int[this.numStacksCap];
        this.availableStacks = new PriorityQueue<Integer>();
    }
    
    public void push(int val) {
        // new stack is needed
        if (this.availableStacks.isEmpty()) {
            // extend capacity
            if (this.nextNewStackIndex >= this.numStacksCap) {
                this.numStacksCap *= 5; // need to grow stackdata, stacksizes
                // grow stackdata
                int[] temp = new int[this.numStacksCap * this.cap];
                for (int i = 0; i < stackdata.length; i++) {
                    temp[i] = stackdata[i];
                }
                stackdata = temp;
                // grow stacksizes
                temp = new int[this.numStacksCap];
                for (int i = 0; i < stacksizes.length; i++) {
                    temp[i] = stacksizes[i];
                }
                stacksizes = temp;
            }
            
            // add new stack to pq for pushing
            // stackdata, stack sizes "inited" already, w/ zeroes
            this.availableStacks.add(this.nextNewStackIndex);
            this.nextNewStackIndex++;
        }
        int index = this.availableStacks.peek();
        this.stackdata[this.cap * index + this.stacksizes[index]] = val;
        this.stacksizes[index]++;
        // length of stack = cap; remove from pushQ
        if (this.stacksizes[index] == this.cap) {
            this.availableStacks.remove(index);
        }
    }
    
    public int pop() {
        
        //find valid right stack to pop
        while (this.nextNewStackIndex > 0 && 0 == this.stacksizes[this.nextNewStackIndex - 1]) {
            this.nextNewStackIndex--;
            this.availableStacks.remove(this.nextNewStackIndex);
        }
        if (0 == this.nextNewStackIndex) {
            return -1; // all stacks empty
        }
        
        // idx of valid right stack
        int index = this.nextNewStackIndex - 1;
        if (1 == this.stacksizes[index]) {
            this.availableStacks.remove(index);
            this.nextNewStackIndex--;
        }else if (this.stacksizes[index] == this.cap) {
            // can be used for push
            this.availableStacks.add(index);
        }
        
        // pop operation
        this.stacksizes[index]--;
        return this.stackdata[this.cap * index + this.stacksizes[index]];
    }
    
    public int popAtStack(int index) {
        // invalid input
        if (this.nextNewStackIndex - 1 < index || 0 == this.stacksizes[index]) {
            return -1;
        }
        if (this.stacksizes[index] == this.cap) {
            // can be used for push
            this.availableStacks.add(index);
        }
        // pop
        this.stacksizes[index]--;
        // get eaxct element;
        return this.stackdata[this.cap * index + this.stacksizes[index]];
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */
___________________________________________________________________My Solution____________________________________________________________
class DinnerPlates {
    // endless plates
    List<Stack<Integer>> plates;
    // store first plate to push(left)
    PriorityQueue<Integer>pushQ;
    // store first plate to pop(right)
    PriorityQueue<Integer>popQ;
    
    //count is idx for new stack
    int count, cap;
    public DinnerPlates(int capacity) {
        plates = new ArrayList();
        pushQ = new PriorityQueue();
        popQ = new PriorityQueue<>((x, y) -> y - x);
        this.cap = capacity;
        this.count = 0;
    }
    
    //push new or push exisiing? reduce pushQ and update popQ
    public void push(int val) {
        int pushIdx = -1;
        while(!pushQ.isEmpty()){
            pushIdx = pushQ.poll();
            if(plates.get(pushIdx).size() < cap){
                break;
            }
        }
        // add new stack
        Stack<Integer> pushStack;
        if(pushIdx == -1){
            pushStack = new Stack<Integer>();
            pushStack.push(val);
            plates.add(pushStack);
            
            // update Q
            if(pushStack.size() < cap){
                pushQ.offer(count);
            }
            popQ.offer(count++);
        }else{
        //set a stack to push;
            pushStack = plates.get(pushIdx);
            pushStack.push(val);
            plates.set(pushIdx, pushStack);
            
            if(pushStack.size() < cap){
                pushQ.offer(pushIdx);
            }
            if(pushStack.size() == 1){
                popQ.offer(pushIdx);
            }
        }
    }
    
    // pop may need update pushQ when stack.size == cap;
    public int pop() {
        int popIdx = -1;
        
        // find first valid stack to pop; could be size() == 0 by popAtStack
        while(!popQ.isEmpty()){
            popIdx = popQ.poll();
            if(plates.get(popIdx).size() > 0){
                break;
            }
        }
        
        if(popIdx == -1) return -1;
        
        Stack<Integer> popStack = plates.get(popIdx);
        if(popStack.size() > 1){
            popQ.offer(popIdx);
        }
        if(popStack.size() == cap){
            pushQ.offer(popIdx);
        }
        return popStack.pop();
    }
    
    // only pop, pop() will find valid one itself
    public int popAtStack(int index) {
        if(index >= plates.size()) return -1;
        Stack<Integer> popStack = plates.get(index);
        
        if(popStack.size() == 0) return -1;
        
        if(popStack.size() == cap){
            pushQ.offer(index);
        }
        return popStack.pop();
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */

 
