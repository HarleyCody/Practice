//Best Solution: Use string builder to record path and compare char instead of string, use StringBuilder.setLength to do the backtrack
class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {

        StringBuilder dirStart = new StringBuilder();
        StringBuilder dirEnd = new StringBuilder();
        
        dfs(root, dirStart, startValue);
        dfs(root, dirEnd, destValue);
        
        int k = 0;
        
        while (k < dirStart.length() && k < dirEnd.length() && dirStart.charAt(k) == dirEnd.charAt(k)) {
            ++k;
        }
        
        
        StringBuilder sb = new StringBuilder();
        for (int i = k; i < dirStart.length(); ++i) {
            sb.append('U');
        }
        sb.append(dirEnd.substring(k));
        return sb.toString();
    }
    
    private boolean dfs(TreeNode root, StringBuilder dir, int target) {
        if (root == null) {
            return false;
        }
        if (root.val == target) {
            return true;
        }
        if (root.left != null) {
            dir.append('L');
            if (dfs(root.left, dir, target)) {
                return true;
            }
            dir.setLength(dir.length() - 1);
            
        }
        if (root.right != null) {
            dir.append('R');
            if (dfs(root.right, dir, target)) {
                return true;
            }
            dir.setLength(dir.length() - 1);
            
        }
        return false;
    }  
}
//My Solution: Find path to two nodes and delete the command heading directions, if start has more then append U with start.size() and append direction to dest
class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        LinkedList<String> startDirections = getDirections(root, startValue, new LinkedList());
        LinkedList<String> destDirections = getDirections(root, destValue, new LinkedList());
        
        while(!startDirections.isEmpty() && !destDirections.isEmpty() && 
              startDirections.peek().equals(destDirections.peek())){
            startDirections.poll();
            destDirections.poll();
        }
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < startDirections.size(); ++i){
            ans.append("U");
        }
        
        while(!destDirections.isEmpty()){
            ans.append(destDirections.poll());
        }
        
        return ans.toString();
    }
    
    private LinkedList<String> getDirections(TreeNode root, int targetValue, LinkedList<String> directions){
        if(root == null) return null;
        if(root.val == targetValue){
            return directions;
        }
        directions.offer("L");
        if(getDirections(root.left, targetValue, directions) != null){
            return directions;
        }
        directions.pollLast();
        
        directions.offer("R");
        if(getDirections(root.right, targetValue, directions) != null){
            return directions;
        }
        directions.pollLast();
        return null;
    }
}
