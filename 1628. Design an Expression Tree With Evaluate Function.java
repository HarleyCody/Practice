//My Solution: Build Tree from tail to head to make sure the operate order is left operates right
//             build with decided string is num or operate
/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

abstract class Node {
    Node left;
    Node right;
    String input;
    boolean isVal;
    public Node(String input){
        this.input = input;
    }
    
    public abstract int evaluate();
    // define your fields here
    
};


class TreeNode extends Node{
    String input;
    boolean isVal;
    public TreeNode(String input){
        super(input);
        this.input = input;
    }
    
    @Override
    public int evaluate(){
        if(isVal) return Integer.parseInt(input);
        int leftVal = left.evaluate();
        int rightVal = right.evaluate();
        int ans = compute(leftVal, rightVal, input);
        return ans;
    }
    
    private int compute(int num1, int num2, String operator){
        switch(operator){
            case "+": return num1 + num2;
            case "*": return num1 * num2;
            case "/": return num1 / num2;
            default: return num1 - num2;
        }
    }
}
/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input 
 * and returns the expression tree represnting it as a Node.
 */
// Build Tree from tail to head to make sure the operate order is left operates right
class TreeBuilder {
    int idx;
    Node buildTree(String[] postfix) {
        idx = postfix.length - 1;
        return build(postfix);
    }
    HashSet<String> operators = new HashSet(Arrays.asList(new String[]{"+", "-", "*", "/"}));
    private TreeNode build(String[] postfix){
        TreeNode curNode = new TreeNode(postfix[idx--]);
        curNode.isVal = !operators.contains(curNode.input);
        if(curNode.isVal){
            return curNode;
        }
        
        curNode.right = build(postfix);
        curNode.left = build(postfix);
        return curNode;
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */
