_____________________________________________________________Qualified and Best Solution_________________________________________________
class AllOne {
    // maintain a ascending LinkedList consisting of nodes connected with each other. (bidirection).
    // start with MIN_VALUE(head) end by MAX_VALUE(tail);
    class Node {
        int val;
        Node prev;// use for dec, decrese the value, put node to correct pos at previous part
        Node next;// use for inc, increase the value, put node to correct pos at later part
        String key;
        
        public Node(int val, String key) {
            this.val = val;
            this.key = key;
            this.prev = null;
            this.next = null;
        }
    }
    
    Map<String, Node> map;
    Node head;
    Node tail;

    /** Initialize your data structure here. */
    public AllOne() {
        this.map = new HashMap<>();
        this.head = new Node(Integer.MIN_VALUE, "");
        this.tail = new Node(Integer.MAX_VALUE, "");
        // connect head to tail, next relation and pre relation;
        head.next = tail;
        tail.prev = head;
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Node node = map.get(key);
        if(node == null) {
            Node cur = new Node(1, key);
            map.put(key, cur);
            addNode(head, cur);
        } else {
            int val = node.val;
            node.val = val + 1;
            moveNext(node);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        Node node = map.get(key);
        if(node == null) {
            return;
        } else {
            if(node.val == 1) {
                // delete node directly
                deleteNode(node);
                map.remove(key);
            } else {
                //node.val > 1, update node, update LinkedList
                int val = node.val;
                node.val = val - 1;
                moveAhead(node);
            }
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(map.isEmpty()) {
            return "";
        }
        //tail is Integer.MAX_VALUE move forward to get correct key
        return tail.prev.key;
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(map.isEmpty()) {
            return "";
        }
        //head is Integer.MIN_VALUE move backward to get correct key
        return head.next.key;
    }
    // insert node to list
    private void addNode(Node before, Node node) {
        // copy next of before to node
        node.next = before.next;
        // node connect with before
        node.prev = before;
        // before.next is orginal node that follows before, not node, connect the node to current new node;
        before.next.prev = node;
        // update before.next to new node.
        before.next = node;
    }
    
    private void deleteNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    private void moveNext(Node node) {
        Node next = node.next;
        // find correct position to add node
        while(node.val > next.val) {
            next = next.next;
        }
        // delete current node from LinkedList;
        deleteNode(node);
        //add node after next.prev;
        addNode(next.prev, node);
    }
    
    private void moveAhead(Node node) {
        Node before = node.prev;
        // find node.val where before.val < node.val 
        while(node.val < before.val) {
            before = before.prev;
        }
        // delete Node in orginal position
        deleteNode(node);
        // add node after before node.
        addNode(before, node);
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
__________________________________________________________________My Solution____________________________________________________________
class AllOne {
    // use HashMap to store and retrieve value. fast but O(N) 
    HashMap<String, Integer> recorder;
    /** Initialize your data structure here. */
    public AllOne() {
        recorder = new HashMap();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        recorder.put(key, recorder.getOrDefault(key,0) + 1);
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!recorder.containsKey(key)) return;
        int value = recorder.get(key);
        if(value == 1){
            recorder.remove(key);
        }else{
            recorder.put(key, recorder.get(key) - 1);
        }
        
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        int max = 0; 
        String ans = "";
        for(String key: recorder.keySet()){
            ans = recorder.get(key) > max ? key : ans;
            max = recorder.get(key) > max ? recorder.get(key) : max;
        }
        return ans;
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        int min = Integer.MAX_VALUE; 
        String ans = "";
        for(String key: recorder.keySet()){
            ans = recorder.get(key) < min ? key : ans;
            min = recorder.get(key) < min ? recorder.get(key) : min;
        }
        return ans;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
