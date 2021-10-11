//My Solution: Behaviore break down: Set: Delete and add new node. Get delete and add old node
public class LRUCache {
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        Node head;
        Node tail;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    int cap;
    int bucketSize;
    Node[] bucket;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        cap = capacity;
        bucketSize = 100;
        bucket = new Node[bucketSize];
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // printLink(head);
        // for(Node a : bucket){
        //     printArr(a);
        // }
        Node find = findNode(key);
        if(find != null){
            removeNode(find);
            set(find.key, find.val);
        }
        return find == null ? -1 : find.val;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        Node find = findNode(key);
        if(find == null){
            find = new Node(key, value);
        }else{
            removeNode(find);
            find.val = value;
        }
        addNode(find);
    }
    private Node findNode(int key){
        int idx = getIdx(key);
        Node find = bucket[idx];
        while(find != null){
            if(find.key == key){
                break;
            }
            find = find.next;
        }
        return find;
    }
    private void removeNode(Node node){
        ++cap;
        if(node.prev != null){
            node.prev.next = node.next;
        }else{
            int idx = getIdx(node.key);
            bucket[idx] = node.next;
        }
        if(node.next != null){
            node.next.prev = node.prev;
        }
        if(node.tail != null){
            node.tail.head = node.head;
        }
        if(node.head != null){
            node.head.tail = node.tail;
        }
        if(node == head){
            head = node.tail;
        }
        if(node == tail){
            tail = node.head;
        }
        node.next = null;
        node.prev = null;
        node.head = null;
        node.tail = null;
    }
    private void addNode(Node node){
        if(head != null){
            node.tail = head;
            head.head = node;
        }
        head = node;
        if(tail == null){
            tail = node;
        }
        if(cap == 0){
            tail = tail.head;
            removeNode(tail.tail);
        }
        int idx = getIdx(node.key);
        node.next = bucket[idx];
        if(bucket[idx] != null){
            bucket[idx].prev = node;
        }
        bucket[idx] = node;
        --cap;
    }
    private int getIdx(int key){
        return key % bucketSize;
    }

    private void printLink(Node head){
        if(head == null) return;
        Node n = head;
        while(n != null){
            System.out.print(n.key + " ");
            n = n.tail;
        }
        System.out.println();
    }
    private void printArr(Node head){
        Node n = head;
        while(n != null){
            System.out.print(n.key + " ");
            n = n.next;
        }
        System.out.println();
    }
}
________________________________________________________Implement LinkedHashMap__________________________________________________________
// best and fastest solution
// in the node its bi-direction
// in map nodes are still bi-direction. different from hashmap
class LRUCache {
    Node[] cache;
    int capacity;
    
    class Node{
        int key;
        int value;
        // next prev double linkedlist
        Node next;
        Node prev;
        // up down to locate this node is need to be delete or not
        Node up;
        Node down;
        
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    Node head;
    Node tail;
    
    int numNodes = 0;
    
    public LRUCache(int capacity){
        this.capacity = capacity;
        cache = new Node[capacity];
    }
    
    private void evictTail(){
        Node tailPrev = tail.prev;
        Node tailNext = tail.next;
        // delete tail from map
        if (tailPrev != null) tailPrev.next = null;
        if (tailNext != null) tailNext.prev = null;
        int key = tail.key % capacity;
        if (tail == cache[key]) cache[key] = tailNext;
        
        // remove from double linkedlist
        Node tailUp = tail.up;
        Node tailDown = tail.down;
        if (tailUp != null) tailUp.down = null;
        if (tailDown != null) tailDown.up = null;
        if (head == tail) head = null;
        tail = tailUp;
        numNodes--;
    }
    
    private void moveToHead(Node node){
        if (node == head) return;
        Node nodeUp = node.up;
        Node nodeDown = node.down;
        // if put the exisiting entry, then pick out current node 
        if (nodeUp != null) nodeUp.down = nodeDown;
        if (nodeDown != null) nodeDown.up = nodeUp;
        // if current node is tail, then set tail to previous node
        if (tail == node) tail = nodeUp;
        
        // set up node to head;
        node.down = head;
        head.up = node;
        head = node;
    }
    
    public void put(int key, int value){
        int pos = key % capacity;
        Node listPtr = cache[pos];
        while (listPtr != null && listPtr.key != key){
            listPtr = listPtr.next;
        }
        
        if (listPtr != null){
            listPtr.value = value;
            moveToHead(listPtr);
        }
        else{
            if (numNodes == capacity) evictTail();
            Node node = new Node(key, value);
            node.next = cache[pos];
            if (cache[pos] != null) cache[pos].prev = node;
            cache[pos] = node;
            node.down = head;
            if (head != null) head.up = node;
            head = node;
            if (tail == null) tail = head;
            numNodes++;
        }
    }
    
    public int get(int key)
    {
        int pos = key%capacity;
        Node listPtr = cache[pos];
        while (listPtr != null && listPtr.key != key)
        {
            listPtr = listPtr.next;
        }
        
        if (listPtr != null)
        {
            moveToHead(listPtr);
            return listPtr.value;
        }
        return -1;
    }
}
______________________________________________________________Override LinkedHashMap_____________________________________________________
class LRUCache {//LRU 最近访问的被保留
    private int capacity;
    LinkedHashMap<Integer,Integer> hm = null;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        hm = new LinkedHashMap<Integer,Integer>(this.capacity,.75F,true){
            protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {//重写LinkedList中的对应方法，也可以写在@Override 标签下面
                return size() > capacity;//重写的意义在于原removeEldestEntry的capacity 为MAX_ENTRIES,在LRU中需要更新为capacity，保证了capacity的大小
            }
        };
    }
    
    /*@Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }*/
    
    public int get(int key) {
        if(!hm.containsKey(key))return -1;//不存在返回1
        else return hm.get(key);//参在返回结果
    }
    
    public void put(int key, int value) {
        hm.put(key,value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

——————————————————————————————————————————————————————————————————简洁版——————————————————————————————————————————————————————————————
class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);//super调用父类，相当于父类的对象
        this.capacity = capacity;//this为当前类对象
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);//重用父类的方法
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {//重载父类的remove方法，因为需要改变MAX_ENTRIES 为capacity.
        return size() > capacity; 
    }
}
