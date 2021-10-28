
//Best Solution: Build and tree based on the start and end time find by OlogN
// if(start > root.end) check right node
// else if(start < root.end) check left node;
class MyCalendarTwo {
    class Node{
        Node left;
        Node right;
        int start;
        int end;
        boolean isOverlap;
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    Node root = null;
    public MyCalendarTwo() {
        
    }
    
    public boolean book(int start, int end) {
        if(canBook(root, start, end)){
            root = book(root, start, end);
            return true;
        }
        return false;
    }
    
    private boolean canBook(Node root, int start, int end){
        if(start >= end || root == null) return true;
        if(start >= root.end){
            return canBook(root.right, start, end);
        }else if(end <= root.start){
            return canBook(root.left, start, end);
        }else{
            if(root.isOverlap) return false;
            if(start >= root.start && end <= root.end) return true;
            return canBook(root.left, start, root.start) && canBook(root.right, root.end, end);
        }
    }
        
    private Node book(Node root, int start, int end){
        if(start >= end) return root;
        if(root == null) return new Node(start, end);
        if(start >= root.end){
            root.right = book(root.right, start, end);
        }else if(end <= root.start){
            root.left = book(root.left, start, end);
        }else{
            int maxStart = Math.max(root.start, start);
            int minStart = Math.min(root.start, start);
            int maxEnd = Math.max(root.end, end);
            int minEnd = Math.min(root.end, end);
            root.left = book(root.left, minStart, maxStart);
            root.right = book(root.right, minEnd, maxEnd);
            
            root.start = maxStart;
            root.end = minEnd;
            root.isOverlap = true;
        }
        
        return root;
    }
}
//Best Solution: Build and tree based on the start and end time find by OlogN
// if(start > root.end) check right node
// else if(start < root.end) check left node;
class MyCalendarTwo {
    class Node{
        Node left;
        Node right;
        int start;
        int end;
        boolean isOverlap;
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    Node root = null;
    public MyCalendarTwo() {
        
    }
    
    public boolean book(int start, int end) {
        if(canBook(root, start, end)){
            root = book(root, start, end);
            return true;
        }
        return false;
    }
    //Check if the new time can be inserted
    private boolean canBook(Node root, int start, int end){
        if(start >= end || root == null) return true;
        if(start >= root.end){
            return canBook(root.right, start, end);
        }else if(end <= root.start){
            return canBook(root.left, start, end);
        }else{
            if(root.isOverlap) return false;
            if(start >= root.start && end <= root.end) return true;
            return canBook(root.left, start, root.start) && canBook(root.right, root.end, end);
        }
    }
    // Build Tree
    private Node book(Node root, int start, int end){
        if(start >= end) return root;
        if(root == null) return new Node(start, end);
        if(start >= root.end){
            root.right = book(root.right, start, end);
        }else if(end <= root.start){
            root.left = book(root.left, start, end);
        }else{
            int maxStart = Math.max(root.start, start);
            int minStart = Math.min(root.start, start);
            int maxEnd = Math.max(root.end, end);
            int minEnd = Math.min(root.end, end);
            root.left = book(root.left, minStart, maxStart);
            root.right = book(root.right, minEnd, maxEnd);
            
            root.start = maxStart;
            root.end = minEnd;
            root.isOverlap = true;
        }
        
        return root;
    }
}
//Better Solution: Use TreeMap to do the bucket sort and traverse every timestamp to get the max;
class MyCalendarTwo {

    private TreeMap<Integer, Integer> map;    
    
    public MyCalendarTwo() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int count = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count += entry.getValue();
            if(count > 2) {
                map.put(start, map.get(start) - 1);
                if(map.get(start) == 0) {
                    map.remove(start);
                }
                map.put(end, map.get(end) + 1);
                if(map.get(end) == 0) {
                    map.remove(end);
                }
                return false;
            }
        }
        return true;
    }
}

//My Solution: Store the event int {start, end, num} compare and insert
class MyCalendarTwo {
    PriorityQueue<int[]> timeline;
    public MyCalendarTwo() {
         timeline = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
    }
    
    public boolean book(int start, int end) {
        PriorityQueue<int[]> traverse = new PriorityQueue<int[]>(timeline);
        PriorityQueue<int[]> nTimeline = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        while(!traverse.isEmpty()){
            int[] e = traverse.poll();
            if(e[1] <= start || e[0] >= end) {
                nTimeline.offer(e);
                continue;
            }
            if(e[2] == 2) return false;
            if(e[0] != start) nTimeline.offer(new int[]{Math.min(e[0], start), Math.max(e[0], start), 1});
            nTimeline.offer(new int[]{Math.max(e[0], start), Math.min(e[1], end), 2});
            start = Math.min(e[1], end);
            end = Math.max(e[1], end);
        }
        if(start != end) nTimeline.offer(new int[]{start, end, 1});
        timeline = nTimeline;
        
        return true;
    }
}
