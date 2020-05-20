_______________________________________________________________My Solution______________________________________________________________
class Solution {
    // recorder(i) elements in ith diagonal;
    // every node collect by its node, order is reversed if traverse from top left.
    // idx of recorder => if root in leftmost, x is idx
    //                    if root in bottommost row + rootY - 1 is idx(ie, i * len + j);
    // convert list to 1d array
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int len = 0;
        int R = nums.size();
        for(List<Integer> n : nums){
            len += n.size();
        }
        
        List<List<Integer>> recorder = new ArrayList();
        for(int i = 0; i < R; ++i){
            List<Integer> cur = nums.get(i);
            int size = cur.size();
            for(int j = 0; j < cur.size(); ++j){
                int dis = Math.min(R - 1 - i, j);
                int rootX = i + dis;
                int rootY = j - dis;
                int idx = rootX == R - 1 ? R - 1 + rootY : rootX;
                
                if(recorder.size() == idx){
                    recorder.add(new ArrayList());
                }
                recorder.get(idx).add(0, nums.get(i).get(j));
            }
        }
        
        int[] ans = new int[len];
        int idx = 0;
        for(List<Integer> list : recorder){
            for(int n : list){
                ans[idx++] = n;
            }
        }
        return ans;
    }
}
