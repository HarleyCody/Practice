______________________________________________________________Best Solution______________________________________________________________
class Solution {
    // insecond tree add element by order
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        LinkedList<Integer> list1 = new LinkedList<>();
        build(root1, list1);
        LinkedList<Integer> list = new LinkedList<>();
        build(root2, list, list1);
        // list.addAll(list2);
        // Collections.sort(list);
        // while(!list1.isEmpty()) {
        list.addAll(list1);
        // }
        return list;
    }
    // insert in order
    private void build(TreeNode n1, LinkedList<Integer> list, LinkedList<Integer> list1) {
        if(n1 == null) { return; }
        build(n1.left, list, list1);
        while (!list1.isEmpty() && list1.peek() < n1.val) {   
            list.add(list1.poll());
        }
        list.add(n1.val);
        build(n1.right, list, list1);   
    }
    
    private void build(TreeNode n1, LinkedList<Integer> list) {
        if(n1 == null) { return; }
       build(n1.left, list);
        list.add(n1.val);
        build(n1.right, list);   
    }
}
________________________________________________________________My Solution______________________________________________________________
class Solution {
    // transfer tree to linkedlist by inorder traverse
    // concatenate two lists;
    // linkedlist is faster that stack
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        LinkedList<Integer> sta1 = getList(root1, new LinkedList());
        LinkedList<Integer> sta2 = getList(root2, new LinkedList());
        
        List<Integer> ans = new ArrayList();
        while(!sta1.isEmpty() && !sta2.isEmpty()){
            int p1 = sta1.peekFirst(), p2 = sta2.peekFirst();
            if(p1 > p2){
                //ans.add(sta2.pop());
                ans.add(sta2.remove());
            }else if(p1 < p2){
                ans.add(sta1.remove());
            }else{
                ans.add(sta1.remove());
                ans.add(sta2.remove());
            }
        }
        
        while(!sta1.isEmpty()){
            ans.add(sta1.remove());
        }
        
        while(!sta2.isEmpty()){
            ans.add(sta2.remove());
        }
        return ans;
    }
    
    private LinkedList<Integer>getList(TreeNode root, LinkedList<Integer> ans){
        if(root == null){
            return ans;
        }
        if(root.left != null){
            getList(root.left, ans);
        }
        
        ans.offer(root.val);
        
        if(root.right != null){
            getList(root.right, ans);
        }
        
        return ans;
    }
}
