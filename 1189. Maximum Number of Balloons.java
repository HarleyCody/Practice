//Count freq and calcualte the number of words based on the min of character frequence of "balloon"
________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] freqMap = new int[26];
        char[] chs = text.toCharArray();
        int freq = 0;
        for(char c : chs){
            ++freqMap[c - 'a'];
        }

        int ans = chs.length;
        for(char c : "balon".toCharArray()){
            freq = freqMap[c - 'a'];
            if(c == 'l' || c == 'o') freq /= 2;
            ans = Math.min(ans, freq);
        }
        return ans;
    }
}
// find min frequence of character by scanning "balloon" in the frequence array, elinate one pass by one pass
________________________________________________________________________Another Solution________________________________________________________________________
class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] letters = new int[26];
        String source = "balloon";
        for (char ch : text.toCharArray()) letters[ch - 'a']++;
        
        int count = 0;
        
        while (true) {
            for (char ch : source.toCharArray()) {
                letters[ch - 'a']--;
                if (letters[ch - 'a'] < 0) {
                    return count;
                }
            }
            ++count;
        }
    }
}
