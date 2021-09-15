//first pass: Record number of unique character at index i in an array of input length
//Second pass: Count and compare unique number of character from the oppsite direction of first pass
______________________________________________________________________________My Solution______________________________________________________________________________
class Solution {
    public int numSplits(String s) {
        boolean[] visited = new boolean[26];
        int[] diff = new int[s.length()];
        char[] chs = s.toCharArray();
        
        int count = 0;
        for(int i = 0; i < chs.length; ++i){
            if(!visited[chs[i] - 'a']) ++count;
            diff[i] = count;
            visited[chs[i] - 'a'] = true;
        }
        
        count = 0;
        visited = new boolean[26];
        int ans = 0;
        for(int i = chs.length - 1; i > 0; --i){
            if(!visited[chs[i] - 'a']) ++count;
            if(count == diff[i - 1]) ++ans;
            visited[chs[i] - 'a'] = true;
        }
        
        return ans;
    }
}

//Refined if block: Only set visited to true if it is not visited
____________________________________________________________________________Best Solution______________________________________________________________________________
class Solution {
    public int numSplits(String s) {
        boolean[] visited = new boolean[26];
        int[] diff = new int[s.length()];
        char[] chs = s.toCharArray();
        
        int count = 0;
        for(int i = 0; i < chs.length; ++i){
            if(!visited[chs[i] - 'a']){
                ++count;
                visited[chs[i] - 'a'] = true;
            }
            diff[i] = count;
        }
        
        count = 0;
        int ans = 0;
        visited = new boolean[26];
        for(int i = chs.length - 1; i > 0; --i){
            if(!visited[chs[i] - 'a']){
                ++count;
                visited[chs[i] - 'a'] = true;
            }
            if(count == diff[i - 1]) ++ans;
        }
        
        return ans;
    }
}
