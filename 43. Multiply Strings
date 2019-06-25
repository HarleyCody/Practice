___________________________________________________________Better Solution_______________________________________________________________
class Solution { 
    public String multiply(String num1, String num2){
        int m = num1.length(), n = num2.length(), zero = 0;// zero is padding.
        int[] a = new int[m], c = new int[m + n];// c record carry and digits
        for (int i = 0, k = m; i < m; i++)
            a[--k] = num1.charAt(i) - '0';// a is reverse order of digits in num1
        for (int i = n - 1; i >= 0; i--)
            add(c,a,num2.charAt(i) - '0', zero++);// caculate digit in num2 with num1, zero ++ -> padding: 123 * 456  6*321, “0” + 5*321
        carry(c);// maintain the arry c, change number > 10 to digit( %10 ) then add carry(/10) to next number(as it is in revers order, next is actually previous)
        int i = m + n;// head
        while (i > 0 && c[--i] == 0);
        i++;// first digit of answer
        StringBuilder ret = new StringBuilder(i);
        while (i > 0)
            ret.append((char)(c[--i] + '0'));
        return ret.toString();
    }
    void carry(int[] a){// maintain c to be single digit in every index
        int i;
        for (int k = 0, d = 0; k < a.length; k++){
            i = a[k] + d;
            a[k] = i % 10;
            d = i / 10;
        }
    }
    void add(int[] c, int[] a, int b, int zero){
        for (int i = zero, j = 0; j < a.length; j++,i++)
            c[i] += a[j]*b;// += accumulate result of every digit multiplying with num1. it could be >= 10
    }
}
___________________________________________________________My Solution___________________________________________________________________
class Solution {
    public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        if(num1.charAt(0) == '0' || num2.charAt(0) == '0') return "0";
        int carry = 0, n1 = 0, n2 = 0, a = 0;
        StringBuilder [] answers = new StringBuilder[len2];
        // reduce duplicate calculation
        HashMap<Integer, StringBuilder> recorder = new HashMap();
        
        // multiply single digital of num2 with num1
        for(int i = len2 -1; i >= 0; --i){
            n2 = num2.charAt(i) - '0';
            if(recorder.containsKey(n2)){
                answers[len2 - 1 - i] = recorder.get(n2);
                continue;
            }
            carry = 0;
            StringBuilder answer = new StringBuilder();
            
            for(int j = len1 - 1; j >= 0 ; --j){
                n1 = num1.charAt(j) - '0';
                a = n1 * n2 + carry;
                carry = a / 10;
                answer.insert(0, Integer.toString(a % 10));
            }
            if(carry != 0){
                answer.insert(0, Integer.toString(carry));
            }
            answers[len2 - 1 - i] = answer;
            recorder.put(n2, answer);
        }
        // add answers
        StringBuilder ans = new StringBuilder(answers[0]);
        StringBuilder padding = new StringBuilder("0");
        for(int i = 1 ; i < len2; ++i){
            StringBuilder addend = new StringBuilder(answers[i] + padding.toString());
            padding.append("0");
            carry = 0;
            int l1 = ans.length() - 1, l2 = addend.length() - 1;
            for(; 0 <= l1 && 0 <= l2 ; --l1, --l2){
                n1 = ans.charAt(l1) - '0';
                n2 = addend.charAt(l2) - '0';
                a = n1 + n2 + carry;
                carry = a / 10;
                ans.setCharAt(l1, (char)(a % 10 + '0'));
            }
            while(carry == 1){
                if(l2 >= 0){
                    n2 = addend.charAt(l2--) - '0' + carry;
                    carry = n2 / 10;
                    ans.insert(0, Integer.toString(n2 % 10));
                }
                else{
                    ans.insert(0, "1");
                    carry = 0;
                }
            }
            if(l2 >= 0){
                ans.insert(0, addend.substring(0, l2 + 1));
            }
        }
        return ans.toString();
    }
}
