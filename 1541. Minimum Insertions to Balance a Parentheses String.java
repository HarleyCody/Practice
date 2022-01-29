//Best Solution: Count how many left and right are needed
class Solution {
    public int minInsertions(String s) {
        int count = 0;
        int right = 0;
        for(char c:s.toCharArray()){
            if(c=='('){
                if((right&1)!=0){
                    right--;
                    count++;
                }
                right+=2;
            }else{
                if(right==0){
                    count++;
                    right+=2;
                }
                right--;
            }
        }
        return count+right;
    }
}
//My Solution: Same idea, clearer code
class Solution {
    public int minInsertions(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int idx = 0;
        int balance = 0;
        int ans = 0;
        while(idx < len){
            int rightCount = 0;
            while(idx < len && chs[idx] == ')'){
                ++rightCount;
                ++idx;
            }
            if(rightCount != 0){
                balance -= (rightCount + 1) / 2;
                if(balance < 0){
                    ans -= balance;
                    balance = 0;
                }
                ans += rightCount % 2;
            }
            if(idx < len){
                ++balance;
            }
            ++idx;
        }
        if(balance > 0){
            ans += balance << 1;
        }
        return ans;
    }
}
//My Solution: use right parenthsis as many as it can. if left is used up add left instead
class Solution{
	public int minInsertions(String s){
        char[] chs = s.toCharArray();
        int len = chs.length;
        int balance = 0;
        int ans = 0;
        for(int i = 0; i < len; ++i){
            if(chs[i] == ')'){
                if(i < len - 1 && chs[i + 1] == ')'){
                    ++i;
                    if(balance == 0){
                        ++ans;
                    }else{
                        --balance;
                    }
                }else{
                    if(balance > 0){
                        ++ans;
                        --balance;
                    }else if(balance == 0){
                        ans += 2;
                    }
                }
            }else{
                ++balance;
            }
        }
        if(balance > 0){
            ans += (balance << 1);
        }else{
            ans -= balance;
        }

        return ans;
    }
} 
