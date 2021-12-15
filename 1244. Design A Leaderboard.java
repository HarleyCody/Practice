//Best Solution: Create map with customized node, insert node by order. Insert is o(n) time;
class Leaderboard {
    class Node{
        int playerId; 
        int score; 
        Node next; 
        Node prev; 

        Node(int pId,int score){
            this.playerId = pId;
            this.score = score; 
        }
    }
    Node head, tail; 
    Map<Integer,Node> map; 
    
    public Leaderboard() {
        map = new HashMap();   
        head = new Node(0,0); 
        tail = new Node(0,Integer.MAX_VALUE); 
        
        head.next= tail; 
        tail.prev = head; 
    }
    
    public void addScore(int playerId, int score) {
        if(map.containsKey(playerId)){ 
            Node node = map.get(playerId); 
            reset(playerId); 
            node.score += score;
            addNode(node); 
            map.put(playerId,node);

        }else{
            Node node = new Node(playerId,score); 
            addNode(node);        
            map.put(playerId,node);
        }
    }

    public int top(int K) {
        int sum = 0; 
        Node temp = tail.prev; 
        while(K>0){
            sum += temp.score; 
            temp = temp.prev; 
            K--;
        }
        
        return sum; 
    }
    
    public void reset(int playerId) {
        if(!map.containsKey(playerId)) return; 
        
        Node node = map.get(playerId);
        Node nodePrev = node.prev; 
        Node nodeNext = node.next; 
        nodePrev.next = nodeNext; 
        nodeNext.prev = nodePrev; 
        
        map.remove(playerId); 
    }
    
    private void addNode(Node node){
        Node temp = head; 
        while(temp.score<=node.score){
            temp = temp.next; 
        }
        Node tempPrev = temp.prev; 
        tempPrev.next = node; 
        node.prev = tempPrev;
        node.next = temp; 
        temp.prev = node;   
    }
}

//My Solution: Quick Select to get the idx of kth largest score and sum them up. Time O(10001)
class Leaderboard {
    int[] board;
    public Leaderboard() {
        board = new int[10001];
    }
    
    public void addScore(int playerId, int score) {
        board[playerId] += score;
    }
    
    public int top(int K) {
        int[] findArr = board.clone();
        findTopK(findArr, 0, 10000, K - 1);
        int ans = 0;
        for(int i = 0; i < K; ++i){
            ans += findArr[i];
        }
        
        return ans;
    }
    
    public void reset(int playerId) {
        board[playerId] = 0;
    }
    
    private void findTopK(int[] arr, int left, int right, int k){
        while(left < right){
            int mid = quickSelect(arr, left, right);
            if(mid == k)return;
            if(mid < k){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
    }
    
    private int quickSelect(int[] arr, int l, int r){
        int pivot = arr[l];
        while(l < r){
            while(l < r && arr[r] <= pivot){
                --r;
            }
            arr[l] = arr[r];
            while(l < r && arr[l] >= pivot){
                ++l;
            }
            arr[r] = arr[l];
        }
        arr[l] = pivot;
        return l;
    }
}
