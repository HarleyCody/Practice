____________________________________________________________________________Best Solution_________________________________________________________________
class FirstUnique {
    // maintain doublelinkedlist to remove O(1)
    // q only store valid unqiue num
    private class Node {
        int value;
        Node prev, next;
        
        public Node(int value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private HashMap<Integer, Node> map;
    private Node head = new Node(-1, null, null);
    private Node tail = new Node(-1, head, null);
    
    public FirstUnique(int[] nums) {
        head.next = tail;
        map = new HashMap<>((int) Math.ceil(nums.length / 0.75f));
        for (int n : nums) {
            Node node = map.get(n);
            if (node != null) {
                removeVal(node);
            } else addVal(n);
        }
    }
    
    public int showFirstUnique() {
        if (head.next == null) return -1;        
        else return head.next.value;
    }
    
    public void add(int n) {
        Node node = map.get(n);
        if (node != null) {
            removeVal(node);
        } else addVal(n);
    }
    
    private void removeVal(Node node) {
        if (node.prev == null) return;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }
    
    private void addVal(int val) {
        Node node = new Node(val, tail.prev, tail);
        tail.prev.next = node; // OVO SAM ZABORAVIO
        tail.prev = node;
        map.put(val, node);
    }
}
______________________________________________________________________________My Solution_________________________________________________________________
// check freq before poll and return, only return if its unique
class FirstUnique {
    HashMap<Integer, Integer> recorder = new HashMap<Integer, Integer>();
    Queue<Integer> data = new LinkedList();
    public FirstUnique(int[] nums) {
        for(int num : nums){
            add(num);
        }
    }
    
    public int showFirstUnique() {
        while(!data.isEmpty()){
            int cur = data.peek();
            if(recorder.get(cur) == 1){
                return cur;
            }
            data.poll();
        }
        return -1;
    }
    
    public void add(int value) {
        int f = recorder.getOrDefault(value, 0);
        recorder.put(value, f + 1);
        data.offer(value);
    }
}

