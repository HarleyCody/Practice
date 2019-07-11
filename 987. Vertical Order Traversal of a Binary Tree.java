_______________________________________________________________Best Solution_____________________________________________________________
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // use map instead of sortedMap
    Map<Integer, PriorityQueue<Point>> map = new HashMap<>();
    // record order of priorityQueue
    int lowest = 0;
    int highest = 0;
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        dfs(root,0,0);
        // get all columns, transfer them to List
        List<List<Integer>> res = new ArrayList<>();
        // add to list from smallest col to largest col
        while(lowest <= highest){
            PriorityQueue<Point> heap = map.get(lowest);
            
            List<Integer> list =  new ArrayList<>();
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            /*
            this while cannot be replaced by for(int i : heap){}
            as heap only poll with order of comparator, but int i will get stored order not polling order, so order is unchanged by using for(int i : heap)
            */
            while(!heap.isEmpty()){
                list.add(heap.poll().val);
            }
            res.add(list);
            lowest++;
        }
        return res;
    }
    
    // traversal nodes and lable nodes with row and coln=
    private void dfs(TreeNode root, int x, int y) {
        if(root == null) {
            return;
        }
        if(map.get(y) == null){
            map.put(y,new PriorityQueue<Point>(new MyComparator()));
        }
        
        PriorityQueue<Point> heap = map.get(y);
        Point p = new Point(x,y,root.val);
        heap.offer(p);
        // update col range
        lowest = Math.min(lowest,y);
        highest = Math.max(highest,y);
        //travel left
        dfs(root.left,x - 1,y - 1);
        //travel right
        dfs(root.right,x - 1,y + 1);
    }
    // create a new node
    public static class Point {
        int x;
        int y;
        int val;
        
        public Point(int x,int y, int val) {
            this.x = x;
            this.y=  y;
            this.val =val;
        }
    }
    // rewrite comparator for priorityqueue to POLL value;
    public static class MyComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            if(p2.x == p1.x){
                return Integer.compare(p1.val,p2.val);
            }
            return Integer.compare(p2.x,p1.x);
        }
    }
}
________________________________________________________________My Solution______________________________________________________________
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.SortedMap; 
class Solution {
    
    class newNode{
        TreeNode node;
        int row;
        int col;
        newNode(TreeNode root, int r, int c){
            node = root;
            row = r;
            col = c;
        }
    }
    // sorted map record column with ascending order
    private SortedMap<Integer, List<newNode>> recorder = new TreeMap();
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        if(root == null) return ans;
        // rewrite comparator so order is valid
        Comparator<newNode> cmp = new Comparator<newNode>(){
            public int compare(newNode n1, newNode n2){
                if(n1.row == n2.row && n1.col == n2.col) return n1.node.val - n2.node.val;
                return n1.row - n2.row;
            }
        };
        verticalTraversal(root, 0, 0);
        for(int i : recorder.keySet()){
            Collections.sort(recorder.get(i), cmp);
            List<Integer> med = new ArrayList();
            for(newNode n : recorder.get(i)){
                med.add(n.node.val);
            }
            ans.add(med);
        }
        return ans;
    }
    //add node to recorder by newNode(extra row and col element)
    private void verticalTraversal(TreeNode root, int row, int col){
        if(root == null) return;
        newNode r = new newNode(root, row, col);
        // update list of current column. add current node to its list
        List<newNode> cur = recorder.getOrDefault(col, new ArrayList());
        cur.add(r);
        recorder.put(col, cur);
        verticalTraversal(root.left, row + 1, col - 1);
        verticalTraversal(root.right, row + 1, col + 1);
    }
}
