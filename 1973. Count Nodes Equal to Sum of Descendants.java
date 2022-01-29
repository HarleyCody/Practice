//My Solution: count the sum in left and right, find when root.val == left + right. return sum of all nodes
class Solution{
	int ans = 0;
	public int equalToDescendants(TreeNode root){
	count(root);
	return ans;
}

private int count(TreeNode root){
	if(root == null) return 0;
	int leftSum = count(root.left);
	int rightSum = count(root.right);
	if(root.val == leftSum + rightSum){
	++ans;
}
return leftSum + rightSum + root.val;
}
}
