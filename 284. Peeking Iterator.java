_______________________________________________________Best Solution(Queue)_________________________________________________________________
// set semaphore for peek() and next();
// semaphore next control is curnext is valid(up to date);
// if is valid, next() return curnext and set next to false（not valid) peek() return curnext
// else next() return it.next(); peek() set curnext to latest and next set to true;               
class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> it;
    boolean n = false;
    Integer next;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    it = iterator;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if(n) return next;
        if(it.hasNext()){
            next = it.next();
            n = true;
            return next;
        }
        return -1;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        if(n){
            n = false;
            return next;
        }
	    return it.next();
	}

	@Override
	public boolean hasNext() {
        if(n) return true;
	    return it.hasNext();
	}
}
________________________________________________________My Solution(Queue)_________________________________________________________________
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
// store element in queue;
  Queue<Integer> q = new LinkedList();
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    while(iterator.hasNext()){
            q.add(iterator.next());
        }
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return q.peek();
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    return q.poll();
	}

	@Override
	public boolean hasNext() {
	    return !q.isEmpty();
	}
}
