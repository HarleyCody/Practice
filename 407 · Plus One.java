//My Solution: caculate with carry, prepare ans to be digits.length + 1, set carry after calculating all digits
public class Solution {
    public int[] plusOne(int[] digits) {
        int[] ans = new int[digits.length + 1];
        int carry = 1;
        for(int i = digits.length - 1; 0 <= i; --i){
            ans[i + 1] = (digits[i] + carry) % 10;
            carry = (digits[i] + carry) / 10;
        }
        if(carry == 0){
            return Arrays.copyOfRange(ans, 1, ans.length);
        }
        ans[0] = carry;
        return ans;
    }
}
