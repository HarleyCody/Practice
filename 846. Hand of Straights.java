__________________________________________________________________________________Best Solution________________________________________________________________________________
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        
        boolean[] visited = new boolean[hand.length];
        Arrays.sort(hand);
        
        if(hand.length%W != 0)
            return false;
        
        for(int i=0; i<hand.length;i++){
            if(visited[i])
                continue;
            
            visited[i] = true;
            int prev = hand[i];
            int count = 1;
            
            for(int j=i+1;j<hand.length && count<W;j++){
                if(!visited[j]){
                    // hand[j] must be the first valid candidata to be extend as hands is sorted
                    if(hand[j]-prev>1)
                        return false;
                    if(hand[j]-prev==1){
                        visited[j]=true;
                        count++;
                        prev=hand[j];
                    }
                }
            }
            
            if(count != W)
                return false;
        }
        
        return true;
    }
}
__________________________________________________________________________________My Solution____________________________________________________________________________________
class Solution {
    // form hand by recording lastNum and number of cars in one hand
    int[] lastNum, num;
    int s = 0, e = 0, w;
    public boolean isNStraightHand(int[] hand, int W) {
        int len = hand.length;

        if(len % W != 0){
            return false;
        }
        Arrays.sort(hand);
        w = W;
        lastNum = new int[len / W];
        num = new int[len / W];
        
        int idx = 0;
        while(idx < len){
            int n = hand[idx];
            int freq = 0;
            while(idx < len && n == hand[idx]){
                ++freq;
                ++idx;
            }
            
            if(!update(n, freq)){
                return false;
            }
        }
        return true;
    }
    
    private boolean update(int n, int f){
        for(int idx = e - 1; s <= idx; --idx){
            if(f != 0 && lastNum[idx] == n - 1 && num[idx] < w){
                lastNum[idx] = n;
                ++num[idx];
                --f;
            }else if(f == 0){
                if(num[idx] < w){
                    return false;
                }else{
                    s = idx;
                }
            }
        }
        while(f != 0){
            if(e >= lastNum.length){
                return false;
            }
            lastNum[e] = n;
            ++num[e++];
            --f;
        }
        return true;
    }
}
