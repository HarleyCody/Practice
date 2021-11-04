//My(Best) Solution: dp record odd and eve sub-arrays that end with arr[i];
//Check odd by num & 1 == 1;
//As cur odd and eve only related to previous odd and eve, so two pointers are enough(ie odd, eve)
//Fomula : if(curNum is odd) curOdd = prevEven + 1; curEven = prevOdd;
//         if(curNum is even) curOdd = prevOdd; curEven = prevEven + 1;
//Mod 1e9 + 7 at last
class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = (int) 1e9 + 7;
        int len = arr.length;
        if(len == 0) return 0;
        
        int odd = arr[0] & 1;
        int eve = 1 - odd;
                
        long ans = odd;
        int tmp;
        for(int i = 1; i < len; ++i){
            if((arr[i] & 1) == 1){
                tmp = odd;
                odd = eve + 1;
                eve = tmp;
            }else{
                ++eve;
            }
            ans += odd;
        }

        return (int)(ans % mod);
    }
}
