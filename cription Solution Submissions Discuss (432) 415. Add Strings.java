________________________________________________________Best Solution__________________________________________________________
class Solution {
    public String addStrings(String num1, String num2) {
        int finalLength = Math.max(num1.length(), num2.length());
        char[] res = new char[finalLength];
        int p1 = num1.length()-1;
        int p2 = num2.length()-1;
        int p3 = res.length-1;
        int carry = 0;
        //add two nums if shorter num has been added then set its digit to 0 continue to add two nums
        // put rlt to array but not add them to answer directly.
        while(p3 >= 0){
            int digit1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int digit2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            int digit = sum % 10;
            res[p3] = (char)(digit + '0');
            p1--;
            p2--;
            p3--;
        }
        // char array to string and add 1;
        return carry == 1 ? "1" + String.valueOf(res) : String.valueOf(res);
    }
}
________________________________________________________My Solution_____________________________________________________________
class Solution {
    public String addStrings(String nums1, String nums2) {
        // set num1 be the longest num
        if(nums1.length() < nums2.length()){
            String tem = nums1;
            nums1 = nums2;
            nums2 = tem;
        }
        StringBuilder ans = new StringBuilder();
        int n1 = 0 , n2 = 0, rlt = 0, carry = 0;
        int i = nums1.length() - 1, j = nums2.length() - 1;
        //add two nums
        for(; 0 <= j; --i, --j){
            n1 = nums1.charAt(i) - '0';
            n2 = nums2.charAt(j) - '0';
            rlt = (n1 + n2 + carry) % 10;
            carry = (n1 + n2 + carry) / 10;
            ans.insert(0, (char)(rlt + 48));
        }
        // move rest of longer num to ans
        while(0 <= i){
            n1 = nums1.charAt(i--) - '0';
            rlt = (n1 + carry) % 10;
            carry = (n1 + carry) / 10;
            ans.insert(0, (char)(rlt + 48));
        }
        if(carry > 0) ans.insert(0, '1');
        return ans.toString();
    }
}
