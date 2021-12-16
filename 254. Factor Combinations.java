//Best Solution: use linkedlist and backtrack
//Improve from tacktrack below by using linkedlist instead of ArrayList
class Solution {
    List<List<Integer>> ans = new ArrayList();
    public List<List<Integer>> getFactors(int n) {
        getFactors(n, 2, new LinkedList<Integer>());
        return ans;
    }
    
    private void getFactors(int n, int start, LinkedList<Integer> cur){
        if(n == 1){
            if(cur.size() < 2) return;
            ans.add(new ArrayList(cur));
            return;
        }
        int end = (int)Math.sqrt(n);
        for(int i = start; i <= end; ++i){
            if(n % i == 0){
                cur.offer(i);
                getFactors(n / i, i, cur);
                cur.removeLast();
            }
        }
        
        cur.offer(n);
        getFactors(1, n, cur);
        cur.removeLast();
    }
} 
//General Solution: Use linkedlist and add two number at a time
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new LinkedList<>();
        factors(ans, new LinkedList<Integer>(),2,n);
        return ans;
    }
    
    
    public void factors(List<List<Integer>> res, LinkedList<Integer> holder, int start, int n) {
        int upper = (int)Math.sqrt(n); //upper limit for the smaller factor
        for(int i = start; i <= upper; i++) {
            //checks case 
            if(n % i == 0){
                int biggerFactor = n / i;
                if( biggerFactor >= i) {
                    holder.add(i);
                    holder.add(biggerFactor);
                    res.add(new ArrayList<Integer>(holder)); 
                    //need to remove the bigger factor since bigger factor can be factored into smaller factors
                    holder.removeLast();
                    factors(res, holder, i, biggerFactor);
                    holder.removeLast();
                }
            }
        }
        
    }
    }
}
//Backtrack : start from 2 to divide n and divide the factor continueously untill n == 1
//When the cur size > 2 then there is a solution add it to ans
//Return and backtrack
//Backtrack from the previous max to sqrt(current n) and n;
class Solution {
    List<List<Integer>> ans = new ArrayList();
    public List<List<Integer>> getFactors(int n) {
        getFactors(n, 2, new ArrayList<Integer>());
        return ans;
    }
    
    private void getFactors(int n, int start, List<Integer> cur){
        if(n == 1){
            if(cur.size() < 2) return;
            ans.add(new ArrayList(cur));
        }
        int end = (int)Math.sqrt(n);
        for(int i = start; i <= n; ++i){
            if(n % i == 0){
                cur.add(i);
                getFactors(n / i, i, cur);
                cur.remove(cur.size() - 1);
            }
            
            if(i < n && i > end) i = n-1; // speed up
        }
    }
} 
