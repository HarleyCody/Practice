______________________________________________________________Best Solution____________________________________________________
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        //101
        //111
        //110
        //100 = 4
        int count =0;
        while(m!=n){
            // move to right 1 time. divided by 2
            m>>=1;
            n>>=1;
            count++;
        }
        System.out.println(m);
        System.out.println(count);
        // move to left, void fills by 0. times 2 for count times.
        return m<<count;
    }
}
____________________________________________________________My Solution________________________________________________________
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        if(n > (long)m*m) return 0;
        if(m == Integer.MAX_VALUE) return m;
        long ans = m;
        for(long i = m + 1; i <= n; i++){
            ans = ans & i;
            if(ans == 0) return 0;
        }
        return (int)ans;
    }
}
