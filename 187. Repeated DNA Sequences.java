_______________________________________________________________Best Solution_____________________________________________________________
class Solution {
    // rolling hash, compress to array with 4;
    // rolling hash have to compress the base to zer0, ie start first with zero.
    // map can be used to compress;
    Map<Character, Integer> toInt = new
            HashMap() {{put('A', 0); put('C', 1); put('G', 2); put('T', 3); }};
    public List<String> findRepeatedDnaSequences(String s) {        
        int len = s.length();
        
        if(len < 10) return new ArrayList();
        
        HashSet<Integer> seen = new HashSet();
        HashSet<String> ans = new HashSet();
        int[] nums = new int[len];
        for(int i = 0; i < len; ++i) nums[i] = toInt.get(s.charAt(i));
        
        
        int coefficient = 4, i = 0;
        int rolling = 0, headCoefficient = 1, head;
        while(i < 10){
            rolling = rolling * coefficient + nums[i++];
            headCoefficient = (headCoefficient * coefficient); 
        }
        seen.add(rolling);
        
        while(i < len){
            rolling = rolling * coefficient - headCoefficient * nums[i - 10] + nums[i++];
            
            if(!seen.add(rolling)){
                ans.add(s.substring(i - 10, i));
            }
        }
        return new ArrayList<String>(ans);
    }
}
_____________________________________________My Solution(didnot compress hashbase) Wrong__________________________________________________
class Solution {
    // rolling hash
    int mod = 100000007;
    public List<String> findRepeatedDnaSequences(String s) {        
        char[] chs = s.toCharArray();
        int len = chs.length;
        
        if(len < 10) return new ArrayList();
        
        HashSet<Long> seen = new HashSet();
        HashSet<String> ans = new HashSet();
        
        int coefficient = 26, i = 0;
        long rolling = 0, headCoefficient = 1, head;
        while(i < 10){
            rolling = (rolling * coefficient + chs[i++]) % mod;
            headCoefficient = (headCoefficient * 26) % mod; 
        }
        seen.add(rolling);
        
        while(i < len){
            head = headCoefficient * chs[i - 10] % mod;
            rolling = (rolling * coefficient - head + mod) % mod;
            rolling = (rolling + chs[i++]) % mod;
            if(!seen.add(rolling)){
                ans.add(s.substring(i - 10, i));
            }
        }
        return new ArrayList<String>(ans);
    }
}
