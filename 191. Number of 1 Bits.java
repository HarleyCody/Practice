___________________________________________________________________My Solution__________________________________________________________
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0){
            res++;
            // decrease to next one bit
            n &= (n-1);
        }
        return res;
    }
}
___________________________________________________________________My Solution__________________________________________________________
// convert to string;
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        String binaryStr = Integer.toBinaryString(n);
        int ans = 0, i = 0;
        while(i < binaryStr.length()){
            if(binaryStr.charAt(i) == '1'){
                ++ans;
            }
            ++i;
        }
        return ans;
    }
}
