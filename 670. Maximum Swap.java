class Solution {
    // use dp[i] to determine the maxChar after idx i;
    // swap when cur < dp[i];
    public int maximumSwap(int num) {
        String numStr = String.valueOf(num);
        char[] chs = numStr.toCharArray();
        int len = chs.length;
        
        char[] max = new char[len];
        max[len - 1] = chs[len - 1];
        for(int i = len - 2; 0 <= i; --i){
            max[i] = (char)Math.max(chs[i], max[i + 1]);
        }
        char maxDigit = '$';
        int i = 0;
        while(i < len){
            if(chs[i] < max[i]){
                maxDigit = chs[i];
                chs[i] = max[i];
                break;
            }
            ++i;
        }
        int j = len - 1;
        while(i < j){
            if(chs[i] == chs[j]){
                chs[j] = maxDigit;
                break;
            }
            --j;
        }
        return Integer.parseInt(new String(chs));
    }
}
