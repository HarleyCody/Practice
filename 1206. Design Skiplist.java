class Skiplist {
    class Node{
        int val;
        Node next;
        Node prev;
        Node below;
        Node up;
        
        public Node(int v){
            val = v;
        }
    }
    
    Node hHead;
    int height;
    Random rand;
    public Skiplist() {
        hHead = new Node(-1);
        height = (int)Math.log(20000) + 1;
        rand = new Random();
        
        int curLayer = height - 1;
        Node hh = hHead;
        while(curLayer-- > 0){
            hh.below = new Node(-1);
            hh.below.up = hh;
            hh = hh.below;
        }
    }
    
    public boolean search(int target) {
        Node ans = find(target);
        
        return ans != null;
    }
    // find from left to right on a layer, if next is null or next.val > tar, find on next layer
    // duplicate node will return the first node that find in list
    private Node find(int tar){
        Node fNode = hHead;
        Node downNode = hHead;
        while(fNode != null && fNode.val <= tar){
            if(fNode.below != null){
                downNode = fNode;
            }
            if(fNode.val == tar){
                return fNode;
            }
            if(fNode.next == null || fNode.next.val > tar){
                fNode = downNode.below;
                downNode = fNode;
            }else{
                fNode = fNode.next;
            }
        }
        
        return null;
    }
    
    // Get a random layer number, start add below at the layer number.
    // insert num into lists with ascending order, insert from layer number until layer0
    // duplicate will be added to the last node with same value in a list
    public void add(int num) {
        int upper = rand.nextInt(height);
        int curH = height - 1;
        Node cNode = hHead;
        Node downNode = hHead;
        Node tarNode = new Node(num);
        while(cNode != null && cNode.val <= num){
            if(cNode.below != null){
                downNode = cNode;
            }
            if(cNode.next == null || cNode.next.val > num){
                if(curH <= upper){
                    //System.out.println("Add " + num);
                    tarNode.next = cNode.next;
                    cNode.next = tarNode;
                    tarNode.prev = cNode;
                    if(tarNode.next != null){
                        tarNode.next.prev = tarNode;
                    }
                    
                    if(curH != 0){
                        //System.out.println
                        tarNode.below = new Node(num);
                        tarNode.below.up = tarNode;
                        tarNode = tarNode.below;
                    }
                }
                
                cNode = downNode.below;
                downNode = cNode;
                --curH;
            }else{
                cNode = cNode.next;
            }
        }
    }
    
    // get the first one and delete
    public boolean erase(int num) {
        Node eNode = find(num);
        if(eNode == null){
            return false;
        }
        
        while(eNode != null){
            eNode.prev.next = eNode.next;
            if(eNode.next != null){
                eNode.next.prev = eNode.prev;
            }
            eNode = eNode.below;
        }
        
        return true;
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
