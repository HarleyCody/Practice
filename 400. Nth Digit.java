___________________________________________________________My(Best) Solution_____________________________________________________________
class Solution{
    // find target length, find target num, find digit; 
    public int findNthDigit(int n) {
        if(n < 10){
            return n;
        }
        
        // find proper length;
        int lb = 1, rb = 9;
        while(lb < rb){
            int m = (lb + rb) / 2;
            if(numOfDigit(m) < n){
                lb = m + 1;
            }else{
                rb = m;
            }
        }
        int len = rb;
        
        // find exact num;
        int base = (int)Math.pow(10, len - 1);
        int extra = (int)(n - numOfDigit(len - 1) - 1);
        int num = base + extra / len;

        // find digit;
        int idx = len - extra % len;
        int ans = 0;
        while(idx-- > 0){
            ans = num % 10;
            num /= 10;
        } 
        return ans;
    }

    // len == 3 ans = 900 * 3 + 90 + 2 + 9 => 3 * 10^3 - 100 - 10 - 1
    // len == 2 ans => 90 * 2 + 9 => 2 * 10^2 - 10 - 1;
    private long numOfDigit(int numLen){
        long base = numLen * (long)Math.pow(10, numLen);
        long acc = 1 + (long)(Math.pow(10, numLen) - 10)/ 9;
        return base - acc;
    }
}
__________________________________________________________________Concise Solution________________________________________________________
class Solution { 
	public int findNthDigit(int n) {
		int len = 1;
		long count = 9;
		int start = 1;
        
        //get base
		while (n > len * count) {
			n -= len * count;
			len += 1;
			count *= 10;
			start *= 10;
		}
        
        // get num
		start += (n - 1) / len;
		String s = Integer.toString(start);
        
        // get digit
		return Character.getNumericValue(s.charAt((n - 1) % len));
	}
}
