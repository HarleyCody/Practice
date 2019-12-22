___________________________________________HashMap Solution(record timeline s: +1 e: -1)___________________________________________________
class MyCalendarThree {
    class Node{
        int start, end, count;
        Node left, right;
        Node(int s, int e, int c){
            start = s;
            end = e;
            count = c;
        }
    }
    
    Node root;
    public MyCalendarThree() {
        root = null;
    }
    
    int max = 1;
    public int book(int start, int end) {
        root = insert(root, start, end, 1);
        return max;
    }
    
    private Node insert(Node node, int start, int end, int count){
        if(start >= end) return node; ////
        if(node == null) return new Node(start, end, count);
        if(end <= node.start){
            node.left = insert(node.left, start, end, count);
        }else if(node.end <= start){
            node.right = insert(node.right, start, end, count);
        }else{
            // overlaps end after(end>node start) or start before(start < node end)
            // position found new node should be inserted here
            int s = Math.min(start, node.start);
            // star of overlap (max)
            int overlapS = Math.max(start, node.start);
            int e = Math.max(end, node.end);
            // end of overlap (min)
            int overlapE = Math.min(end, node.end);
            // create new point for new overlaps
            // insert node for timeline out of cur node creat with count otherwide create with curNode count
            node.left = insert(node.left, s, overlapS, 
                               start < node.start ? count:node.count);
            node.right = insert(node.right, overlapE, e,
                               end > node.end ? count:node.count);
            // renew count;
            node.start = overlapS;
            node.end = overlapE;
            node.count += count;
            max = Math.max(max, node.count);
        }
        return node;
    }
}
___________________________________________HashMap Solution(record timeline s: +1 e: -1)___________________________________________________
class MyCalendarThree {
    // use sorted map( treeMap) to record how many events at time i timeline
    // start means there is one more event at time i
    // end means there is one less event at time i;
    private TreeMap<Integer, Integer> timeline = new TreeMap<>();
    public int book(int s, int e) {
        timeline.put(s, timeline.getOrDefault(s, 0) + 1); // 1 new event will be starting at [s]
        timeline.put(e, timeline.getOrDefault(e, 0) - 1); // 1 new event will be ending at [e];
        int ongoing = 0, k = 0;
        for (int v : timeline.values())
            // choose peek value;
            k = Math.max(k, ongoing += v);
        return k;
    }
}
