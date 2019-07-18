class Solution {
    public String licenseKeyFormatting(String S, int K) {
        Deque<Character> recorder = new LinkedList<Character>();
        int len = 0;
        for(char c : S.toCharArray()){
            if('0' <= c && c <= '9' || 'A' <= c && c <='Z'){
                recorder.offer(c);
                ++len;
            }
            if('a' <= c && c <= 'z'){
                recorder.offer((char)(c - 32));
                ++len;
            }
        }
        StringBuilder ans = new StringBuilder();
        while(!recorder.isEmpty()){
            int it = len == recorder.size() ? len % K == 0 ? K : len % K : K;
            while(it-- > 0){
                ans.append(recorder.poll());
            }
            if(!recorder.isEmpty()) ans.append('-');
            
        }
        return ans.toString();
    }
}
