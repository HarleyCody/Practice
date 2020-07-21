class Solution {
    // compute smallest dis below it
    // search from top down, make sure prev is min
    // searched node, compare with its below and prevMin
    int K;
    HashMap<Integer, int[]> recorder = new HashMap();
    int ans = Integer.MAX_VALUE;
    public int findClosestLeaf(TreeNode root, int k) {
        findBelow(root);
        findTar(root, k, 1, recorder.get(root.val));
        return ans;
    }
    
    private int[] findBelow(TreeNode root){
        if(root.left == null && root.right == null){
            recorder.put(root.val, new int[]{root.val, 0});
            return new int[]{root.val, 0};
        }
        int[] rlt = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};
        if(root.left != null){
            int[] left = findBelow(root.left);
            if(rlt[1]> left[1] + 1){
                rlt[0] = left[0];
                rlt[1] = left[1] + 1;
            }
        }
        if(root.right != null){
            int[] right = findBelow(root.right);
            if(rlt[1] > right[1] + 1){
                rlt[0] = right[0];
                rlt[1] = right[1] + 1;
            }
        }
        recorder.put(root.val, rlt);
        return rlt;
    }
    
    private void findTar(TreeNode root, int tar, int dis, int[] prev){
        if(ans != Integer.MAX_VALUE){
            return;
        }
        if(root.val == tar){
            int[] below = recorder.get(root.val);
            ans = below[1] < prev[1] + dis ? below[0] : prev[0];
            return;
        }
        // compare below and prev to generate new prev
        int[] below = recorder.get(root.val);
        if(below[1] > prev[1] + dis){
            if(root.left != null){
                findTar(root.left, tar, dis + 1, prev);
            }
            if(root.right != null){
                findTar(root.right, tar, dis + 1, prev);
            }
        }else{
            if(root.left != null){
                findTar(root.left, tar, 1, below);
            }
            if(root.right != null){
                findTar(root.right, tar, 1, below);
            }
        }
    }
}
