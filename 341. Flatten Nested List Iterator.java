public class NestedIterator implements Iterator<Integer> {
    
    private Queue<Integer> queue = new LinkedList<>();// maintain a queue in NesterIterator class
    
    public NestedIterator(List<NestedInteger> nestedList) {
        addToQueue(nestedList);
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
    
    private void addToQueue(List<NestedInteger> nestedList){
        if(nestedList.isEmpty()){
            return;
        }
        // NestedInteger defined by question, do need to worry about
        for(NestedInteger i : nestedList){
            if(i.isInteger()){
                queue.offer(i.getInteger());// add to tail
            }else{
                
                // recursivly add integer in nestedlist to queue;
                addToQueue(i.getList());
            }
        }
    }
}
