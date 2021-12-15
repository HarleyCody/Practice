//Best Solution: Find differently when word1 is not same as word2 and break the lopp and return when the shortest is 1 directly
class Solution {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        //和1处理一致，唯一不同是1和2可能会相同，这种情况下，交替把找到的值赋值给两者，即可保证之间距离最小
        if(wordsDict.length == 2) {
            return 1;
        }
        int r = Integer.MAX_VALUE;
        int index = -1;
        if(word1.equals(word2)) {
            for(int i = 0; i < wordsDict.length; i ++) {
                if(wordsDict[i].equals(word1)) {
                    if(index != -1) {
                        r = Integer.min(r, i - index);
                        if(r == 1) {
                            break;
                        }
                    }
                    index = i;
                }
            }
        }else {
            int index1 = -1, index2 = -1;
            for(int i = 0; i < wordsDict.length; i ++) {
                if(wordsDict[i].equals(word1)) {
                    if(index2 != -1) {
                        r = Integer.min(r, i - index2);
                    }
                    index1 = i;
                    
                }else if(wordsDict[i].equals(word2)) {
                    if(index1 != -1) {
                        r = Integer.min(r, i - index1);
                    }
                    index2 = i;
                }else {
                    continue;   
                }
                if(r == 1) {
                    break;
                }
            }
        }
        return r;
    }
}
//My Solution: Two pointers find word1 and word2 and calcualte the distance between them and find min 
class Solution {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int ans = Integer.MAX_VALUE;
        int len = wordsDict.length;
        int wIdx1 = 0;
        int wIdx2 = 0;
        while(wIdx1 < len){
            while(wIdx1 < len && !wordsDict[wIdx1].equals(word1)){
                ++wIdx1;
            }
            while(wIdx2 == wIdx1 || wIdx2 < len && !wordsDict[wIdx2].equals(word2)){
                ++wIdx2;
            }
            
            if(wIdx2 != len && wIdx1 != len){
                ans = Math.min(ans, Math.abs(wIdx1 - wIdx2));
            }
            if(wIdx1 < wIdx2){
                ++wIdx1;
            }else{
                ++wIdx2;
            }
        }
        
        return ans;
    }
}
