// Critical Point
// Theory:https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
______________________________________________Critical Point O(V+E)___________________________________________________________
class Solution {
    HashMap<Integer, HashSet<Integer>> cons = new HashMap();
    HashSet<Integer> ans;
    int time = 0;
    // low record earliest visited vertex that can be reached from subtree rooted with cur.
    // ids record discovery time and isVisited. -1 : unvisited
    int[] low, parent, ids;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ans = new HashSet();
        for(int i = 0 ; i < n; i++){
            cons.put(i, new HashSet<Integer>());
        }
        for(List<Integer> list : connections){
            int first = list.get(0);
            int second = list.get(1);
            cons.get(first).add(second);
            cons.get(second).add(first);
        }
        low = new int[n];
	    ids = new int[n];
	    parent = new int[n]; 
	    Arrays.fill(ids, -1);
	    Arrays.fill(parent, -1);
        for(int i = 0; i < n; i++){
            if(ids[i] == -1)
                dfs(cons, i);
        }
        System.out.print(low[3]);
        System.out.print(ans);
        return new ArrayList();
    }
    private void dfs(HashMap<Integer, HashSet<Integer>> cons, int cur){
        int child = 0;
        ids[cur] = low[cur] = time++;
        for(int nei : cons.get(cur)){
            if(ids[nei] == -1){
                // if child == 2 means cur connect two independant graph. cur is an junction
                //so 0 is excluded from ansList
                child++;
                parent[nei] = cur;
                dfs(cons, nei);
                low[cur] = Math.min(low[nei],low[cur]);
                // in second scenario, low[nei] >= ids[cur] means nei is discovered after cur
                if(parent[cur] == -1 && child > 1 || parent[cur] != -1 && low[nei] >= ids[cur]){
                    ans.add(cur);
                }
            }else if(nei != parent[cur]){
                // update time as its undirect so nei can go to cur as well.
                low[cur] = Math.min(low[cur], ids[nei]);
            }
        }
    }
}
// critical connections
__________________________________________________Critical Connections________________________________________________________
class Solution {
    int[] low;
    int[] disc;
    boolean[] visited;
    ArrayList<Integer>[] adjs;
    int time = 0;
    List<List<Integer>> ans = new ArrayList();
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        low = new int[n];
        disc = new int[n];
        visited = new boolean[n];
        adjs = new ArrayList[n];
        for(int i = 0; i < n; i++){
            adjs[i] = new ArrayList();
        }
        for(List<Integer> con : connections){
            int first = con.get(0);
            int second = con.get(1);
            adjs[first].add(second);
            adjs[second].add(first);
        }
        dfs(0, -1);
        return ans;
    }
    private void dfs(int cur, int parent){
        visited[cur] = true;
        disc[cur] = low[cur] = time++;
        for(int nei : adjs[cur]){
            if(nei == parent) continue;
            if(!visited[nei]){
                dfs(nei,cur);
                low[cur] = Math.min(low[nei], low[cur]);
                if(disc[cur] < low[nei]){
                    ans.add(Arrays.asList(cur, nei));
                }
            }else{
                low[cur] = Math.min(low[cur], disc[nei]);
            }
        }
    }
}
// Product Suggestion
______________________________________________________Product Suggestion____________________________________________________________
import java.util.*;
class ProSug{
    private class Trie{
        HashMap<Character, Trie> child = new HashMap();
        PriorityQueue<String> products;
        public Trie(){
            this.products = new PriorityQueue<>(Collections.reverseOrder());
        }
    }
    Trie root = new Trie();
    public List<List<String>> proSug(int numPro, List<String> repo, String query){
        for(String str : repo){
            add(str.toLowerCase());
        }
        List<List<String>> ans = new ArrayList();
        for(int i = 2; i <= query.length(); i++){
            ans.add(search(query.substring(0, i)));
        }
        return ans;
    }
    private void add(String s){
        Trie cur = root;
        for(char c : s.toCharArray()){
            HashMap<Character, Trie> child = cur.child;
            cur = child.getOrDefault(c, new Trie());
            cur.products.add(s);
            if(cur.products.size() > 3){
                cur.products.poll();
            }
            child.put(c, cur);
        }
    }
    private List<String> search(String s){
        Trie cur = root;
        for(char c : s.toCharArray()){
            cur = cur.child.get(c);
            if(cur == null) return new ArrayList();
        }
        List<String> ans = new ArrayList();
        while(!cur.products.isEmpty()){
            ans.add(0, cur.products.poll());
        }
        return ans;
    }
}
_____________________________________________________Play ground______________________________________________________________
import java.util.*;
public class ProductSuggestions {
  public static class Trie{
    Trie[] nextLetters;
    PriorityQueue<String> pq;
    boolean wordEnd;
    public Trie() {
      nextLetters = new Trie[26];
      pq = new PriorityQueue<>(Collections.reverseOrder());
      wordEnd = false;
    }
  }

  public static List<List<String>> suggestions(int numProducts, List<String> repository, String customerQuery) {
    Trie root = new Trie();
    for (String product : repository) {
      product = product.toLowerCase();
      Trie walk = root;
      for (int i = 0; i < product.length(); i++) {
        int c = product.charAt(i) - 'a';
        if (walk.nextLetters[c] == null) {
          walk.nextLetters[c] = new Trie();
        }
        walk = walk.nextLetters[c];
        walk.pq.add(product);
        if (walk.pq.size() > 3) {
          walk.pq.poll();
        }
      }
      walk.wordEnd = true;
    }
    List<List<String>> productSuggestions = new ArrayList<>();
    Trie walk = root;
    customerQuery = customerQuery.toLowerCase();
    for (int i = 0; i < customerQuery.length(); i++) {
      int c = customerQuery.charAt(i) - 'a';
      if (walk.nextLetters[c] == null) {
        break;
      }
      walk = walk.nextLetters[c];
      if (i > 0 && walk.pq.size() != 0) {
        productSuggestions.add(new ArrayList<>(walk.pq));
      }
    }
    return productSuggestions;
  }
}
// Favorite Genre
___________________________________________________Favorite Genere____________________________________________________________
class Solution {
   public Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
   	Map<String, List<String>> res = new HashMap<>();
   	Map<String, String> songstogenre = new HashMap<>();

   	for(String genre : genreMap.keySet()) {
   		List<String> songs = genreMap.get(genre);
   		for(String song : songs) {
   			songstogenre.put(song, genre);
   		}
   	}
       Map<String, Integer> count = new HashMap();
   	int max = 0;
   	for(String user : userMap.keySet()) {
           count = new HashMap();
           max = 0;
           res.put(user, new ArrayList());
   		List<String> songs = userMap.get(user);
   		for(String song : songs) {
   			String genre = songstogenre.get(song);
   			int c = count.getOrDefault(genre, 0) + 1;
   			count.put(genre, c);
               max = Math.max(c, max);
   		}
           for (String key : count.keySet()) {
               if (count.get(key) == max) {
                   res.get(user).add(key);
               }
           }
   	}
   	return res;
   }
}
// Copy linkedlist with random
____________________________________________________Copy linkedlist with random_______________________________________________
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node h = new Node();
        Node dummy = h;
        Node tra = head;
        HashMap<Node, Node> map = new HashMap();
        while(tra != null){
            if(map.containsKey(tra))break;
            h.val = tra.val;
            if(tra.next != null){
                h.next = new Node();
                h.next.val = tra.next.val;
            }
            map.put(tra, h);
            h = h.next;
            tra = tra.next;
        }
        HashSet<Node> visited = new HashSet();
        tra = head;
        h = dummy;
        while(tra != null){
            if(visited.contains(tra)) break;
            visited.add(tra);
            if(!(tra.random == null)){
                h.random = map.get(tra.random);
            }
            h = h.next;
            tra = tra.next;
        }
        return dummy;
    }
}
_____________________________________________________Best Solution_____________________________________________________________
class Solution {
    // O（1）space解法
    // 1. iterate and copy each node and insert copy Node after original node
    // 2. connect random node
    // 3. split original node and cloned node, return cloned node
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        cloneList(head);

        Node n = head;
        // connect random node
        while (n != null) {
            Node cloned = n.next;
            if (n.random == null) {
                cloned.random = null;
            } else {
                cloned.random = n.random.next;
            }
            n = cloned.next;
        }


        Node cloned = head.next;
        n = head;
        while (n.next.next!=null) {
            Node temp = n.next.next;
            n.next.next = n.next.next.next;
            n.next = temp;

            n = temp;
        }
        n.next = null;

        return cloned;
    }

    private void cloneList(Node head) {
        while(head!=null) {
            head.next = new Node(head.val, head.next, null);
            head = head.next.next;
        }
    }
}
// merge two sorted Lists
____________________________________________________Merge Two Sorted Lists_____________________________________________________
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while(l1 != null && l2 != null){
            if(l1.val > l2.val){
                head.next = l2;
                l2 = l2.next;
            }else{
                head.next = l1;
                l1 = l1.next;
            }
            head = head.next;
        }
        head.next = l2 != null ? l2 : l1;
        return dummy.next;
    }
}
// Subtree of Another Tree
_____________________________________________________Subtree of Another Tree__________________________________________________
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null && t != null) return false;
        if(isSub(s,t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    private boolean isSub(TreeNode s, TreeNode t){
        if(s == null && t == null) return true;
        if(s == null || t == null) return false;
        if(s.val != t.val) return false;
        return isSub(s.left, t.left) && isSub(s.right, t.right);
    }
}
// search a 2D matrix 

___________________________________________________Search a 2D matrix_________________________________________________________
class Solution {
    //find a middle point(one scenario corresponding to one direction)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // use bottom left as middle point, so if tar > cur, only can go left, if tar < cur, only can go up.
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }

        }
        return false;
    }
}
// twoSum unique pair
_________________________________________________TwoSum Unique Pair__________________________________________________________________
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashSet<Integer> recorder = new HashSet();
        HashSet<Integer> rlt = new HashSet();
        for(int i = 0; i < nums.length; i++){
            if(recorder.contains(target - nums[i])){
                if(rlt.contains(target - nums[i])) continue;
                rlt.add(nums[i]);
            }
            recorder.add(nums[i]);
        }
        System.out.print(rlt.size());
        return new int[0];
    }
}

// spiral matrix
________________________________________________________Spiral Matrix___________________________________________________________
class Solution {
    int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        fill(matrix, n);
        return matrix;
    }
    public void fill(int[][] matrix, int n){
        int x = 0, y = 0, val = 1;
        int nX = 0, nY = 0;
        // max turns of n matrix is (n - 1) * 4 - 1
        for(int dir = 0; dir < n * 4; ++dir){
            // max times of filling value to matrix.
            for(int i = 0; i < n; ++i){

                matrix[x][y] = val;

                // get next postion
                nX = x + dirs[dir % 4][0];
                nY = y + dirs[dir % 4][1];

                // if next postion is not valid(out of range of filled), break( do not update x y);
                if(nX < 0 || nX == n || nY < 0 || nY == n || matrix[nX][nY] != 0)break;

                // update x y and val
                x = nX;
                y = nY;
                val += 1;
            }
        }
    }
}
