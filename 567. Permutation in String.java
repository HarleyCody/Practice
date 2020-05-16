___________________________________________________________Best Solution_________________________________________________________________
class Solution {
    // every time move right to find char until freq[right] is matched
    // if left - right == n => found one permutation, 
    // as prev chars are valid char in target(ie, count  0), ans occurs when left - right == n;
    // not match then move left to until release a right char to continue find
    public boolean checkInclusion(String s1, String s2) {
        int[] counts = new int[128];
        for(int i=0;i<s1.length();i++){
            counts[s1.charAt(i)]++;
        }
        int n = s1.length();
        
        int left=0;
        int right=0;
        char[] arr = s2.toCharArray();
        while(right<arr.length){
            while(right<arr.length && counts[arr[right]]>0){
                counts[arr[right]]--;
                right++;
            }
            
            if(right-left==n)return true;
         
            while(right<arr.length && counts[arr[right]]<=0){
                counts[arr[left++]]++;
            }
        }        
        return false;
    }
}
____________________________________________________________My Solution__________________________________________________________________
class Solution {
    // two pointers, three status
    // -2, cur char is not in target String, prev is wasted;
    // -1, cur char is in target string, but freq is higher than it in target
    //      narrow down the range by moving head until cur char freq is exactly right(ie remain 0)
    // 0, get exactly number of cur char in target, remain n - 1 char need to be fit with exactly frequence 
    public boolean checkInclusion(String s1, String s2) {
        int[] freq = new int[26];
        Arrays.fill(freq, -1);
        int n = 0;
        for(int i = 0; i < s1.length(); ++i){
            int idx = s1.charAt(i) - 'a';
            if(freq[idx] == -1){
                freq[idx] = 1;
            }else{
                ++freq[idx];
            }
            if(freq[idx] == 1){
                ++n;
            }
        }
        
        char[] chs = s2.toCharArray();
        int s = 0, e = 0, len = chs.length;
        while(e < len){
            int idx = chs[e] - 'a';
            --freq[idx];
            
            if(freq[idx] == -2){
                while(s <= e){
                    int i = chs[s++] - 'a';
                    if(freq[i] == 0){
                        ++n;
                    }
                    ++freq[i];
                }
            }else if(freq[idx] == -1){
                while(-1 == freq[idx]){
                    int i = chs[s++] - 'a';
                    ++freq[i];
                    if(freq[i] == 1){
                        ++n;
                    }
                }
            }else if(freq[idx] == 0){
                --n;
            }
            if(n == 0){
                return true;
            }
            ++e;
        }
        return false;
    }
}
