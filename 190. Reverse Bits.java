__________________________________________________________________________________Best Solution_____________________________________________________________________________
public class Solution {
// reverse last digit to the other side by moving it times digits
    public static int reverseBits(int i) {
        // HD, Figure 7-1
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        i = (i << 24) | ((i & 0xff00) << 8) |
            ((i >>> 8) & 0xff00) | (i >>> 24);
        return i;
    }
}

__________________________________________________________________________________Best Solution_____________________________________________________________________________
public class Solution {
// reverse last digit to the other side by moving it times digits
    public int reverseBits(int n) {
        int times = 31;
        int ret = 0;
        
        while (times >= 0){
            int lastDigit = n & 1;
            if (lastDigit == 1){
                ret |= (lastDigit << times);
                
            }
            times--;
            n = n >> 1;
        }
        return ret;
    }
}
____________________________________________________________________________________My Solution_____________________________________________________________________________
public class Solution {
    // reverse string use long for overflow
    public int reverseBits(int n) {
        String bits = Integer.toBinaryString(n);
        int len = bits.length();
        char[] chs = bits.toCharArray();
 
        char[] ans = new char[32];
        int a = 32 - len;
        int idx = 0;
        while(idx < a){
            ans[idx++] = '0';
        }
        for(int i = 0; i < len; ++i){
            ans[idx + i] = chs[i]; 
        }
        String ansStr = new StringBuilder(new String(ans)).reverse().toString();
        long l = Long.parseLong(ansStr, 2);
        
        return (int)l;
    }
}
