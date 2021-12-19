//My Solution: BFS, ans is linkedlist as it is only append element.
               cur and prev is arraylist as it is need to retrieve element
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<Integer> prev = new ArrayList();
        prev.add(1);
        LinkedList<List<Integer>> ans = new LinkedList();
        ans.offer(prev);
        while(ans.size() < numRows){
            List<Integer> cur = new ArrayList();
            cur.add(1);
            for(int i = 0; i < prev.size() - 1; ++i){
                cur.add(prev.get(i) + prev.get(i + 1));
            }
            cur.add(1);
            ans.offer(cur);
            prev = cur;
        }
        
        return ans;
    }
}
