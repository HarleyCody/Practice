__________________________________________________________________My Solution__________________________________________________________________
class Solution {
    // mem + dfs
    List<TreeNode>[] recorder = new ArrayList[21];
    public List<TreeNode> allPossibleFBT(int N) {
        if(N == 0 || N % 2 == 0){
            return new ArrayList();
        }
        if(recorder[N] != null){
            return recorder[N];
        }
        TreeNode cur = new TreeNode(0, null, null);
        if(N == 1){
            recorder[1] = new ArrayList();
            recorder[1].add(cur);
            return recorder[1];
        }
        recorder[N] = new ArrayList();
        for(int i = 1; i < N; i += 2){
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - i - 1);
            for(TreeNode l : left){
                for(TreeNode r : right){
                    // must initialize tree here every time;
                    recorder[N].add(new TreeNode(0, l, r));
                }
            }
        }
        return recorder[N];
    }
}
