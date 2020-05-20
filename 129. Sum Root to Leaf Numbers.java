_______________________________________________________My Solution(Recursion)____________________________________________________________
class Solution {
    int ans = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null){
            return ans;
        }
        sumAt(root, 0);
        return ans;
    }
    
    private void sumAt(TreeNode root, int prev){
        if(root.left == null && root.right == null){
            ans += prev * 10 + root.val;
            return;
        }
        int cur = prev * 10 + root.val;
        if(root.left != null){
            sumAt(root.left, cur);
        }
        
        if(root.right != null){
            sumAt(root.right, cur);
        }
    }
}
_________________________________________________________My Solution(One Function backtrack)_____________________________________________

class Solution {
    int ans = 0, sum = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null){
            return ans;
        }
        if(root.left == null && root.right == null){
            sum += root.val;
            ans += sum;
            sum -= root.val;
            return ans;
        }
        sum += root.val;
        sum *= 10;
        if(root.left != null){
            sumNumbers(root.left);
        }
        
        if(root.right != null){
            sumNumbers(root.right);
        }
        sum /= 10;
        sum -= root.val;
        
        return ans;
    }
}
