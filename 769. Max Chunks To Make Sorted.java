//My Solution: Make sure a >= b. Then not swap them. Added two a and 1 b if number of a > number of b + 2; else add the character with less counts
class Solution {
    public String strWithout3a3b(int a, int b) {
        char c1 = 'a';
        char c2 = 'b';
        int n1 = a;
        int n2 = b;
        if(a < b){
            c1 = 'b';
            c2 = 'a';
            n1 = b;
            n2 = a;
        }
        
        char[] ans = new char[a + b];
        int idx = 0;
        while(n1 > 0 && n2 > 0){
            if(n1 > n2 + 2){
                ans[idx++] = c1;
                ans[idx++] = c1;
                ans[idx++] = c2;
                n1 -= 2;
                n2 -= 1;
            }else if(n1 > n2){
                ans[idx++] = c1;
                --n1;
            }else{
                ans[idx++] = c2;
                --n2;
            }
        }
        
        while(n1-- > 0){
            ans[idx++] = c1;
        }
        
        while(n2-- > 0){
            ans[idx++] = c2;
        }
        
        return new String(ans);
    }
}
