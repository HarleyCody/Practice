class Solution {
    // recursion + mem;
    // eg, 222 caculate countDigit(99) for start 0, 1, however 1 need to add extra num Math.pow(10, len - 1), as it start with '1';
    // ans = 0(count99) + 1 (count 99) + 2 count(22);
    // eg 13, ans = 0 count(9) + 1 count(3) + 3 + 1; as 10 is also the answer
    // intialize hashmap out of function to be faster
    static HashMap<Integer, Integer> dp = new HashMap();
    static{
        dp.put(9, 1); dp.put(99, 20);
    }
    public int countDigitOne(int n) {
        if(n < 10){
            return n < 1 ? 0 : 1;
        }
        
        String s = String.valueOf(n);
        char[] chs = s.toCharArray();
        
        int len = chs.length;
        int bottom = (int)Math.pow(10, len - 1);
        int remain = count(bottom - 1);
        int ans = 0;
        
        for(char c = '0' ; c < chs[0]; ++c){
            if(c == '1'){
                ans += bottom;
            }
            ans += remain;
        }
        int last = n - (chs[0] - '0') * bottom;
        ans += (chs[0] == '1' ?  last + 1 : 0)  + countDigitOne(last);
        return ans;
    }
    
    private int count(int n){
        if(dp.containsKey(n)){
            return dp.get(n);
        }
        
        int num = countDigitOne(n);
        dp.put(n, num);
        return num;
    }
}
