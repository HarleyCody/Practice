/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        
        // get total layer in advance as nestedList in lower layer
        int layers = layer(nestedList, 1, 1);
        // sum list recursivly;
        return recSum(nestedList, layers, 0);
    }
    
    public int recSum(List<NestedInteger> nestedList, int layer, int ans) {
        for( NestedInteger i : nestedList){
            // Integer element, sum to ans;
            if(i.isInteger()) ans += i.getInteger() * layer;
            // List element, ans += recSum(new List), cannot use --layer as it's reference transfer;
            else
                ans += recSum(i.getList(), layer-1, 0);
        }
        return ans;
    }
    
    public int layer(List<NestedInteger> nestedList, int layer, int max){
        for(NestedInteger i : nestedList){
            // renew number of layer;
            max = layer > max? layer: max;
            // find a list, recursivly find if there is a nestedList( new layer) in current list;
            if(!i.isInteger()){
                max = layer(i.getList(), layer + 1, max);
            }
        }
        return max;
    }
}
