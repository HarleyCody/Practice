class Solution {
    public String licenseKeyFormatting(String S, int K) {
        // recorder acutal symbols
        Deque<Character> recorder = new LinkedList<Character>();
        int len = 0;
        for(char c : S.toCharArray()){
            if('0' <= c && c <= '9' || 'A' <= c && c <='Z'){
                recorder.offer(c);
                ++len;
            }
            // change to uppercase
            if('a' <= c && c <= 'z'){
                recorder.offer((char)(c - 32));
                ++len;
            }
        }
        StringBuilder ans = new StringBuilder();
        // append ansStringBuilder
        while(!recorder.isEmpty()){
            // len == recorder.size() add beginning of string, else it = K
            // len % K == 0; even format, every groups has K symbols, else add (len % k) symbols as beginning
            int it = len == recorder.size() ? len % K == 0 ? K : len % K : K;
            while(it-- > 0){
                ans.append(recorder.poll());
            }
            if(!recorder.isEmpty()) ans.append('-');
            
        }
        return ans.toString();
    }
}
