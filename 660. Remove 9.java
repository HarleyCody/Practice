//Best Solution: Removing 9 from all interger means converting all numbers to base 9 system.
//Thus we can get the nth number in base 9 by converting n to a number in base 9.
class Solution {
    public int newInteger(int n) {
        StringBuilder sb = new StringBuilder();
        while(n >= 9){
            sb.insert(0,n%9);
            n /= 9;
        }
        sb.insert(0,n);
        return Integer.parseInt(sb.toString());
    }
}
