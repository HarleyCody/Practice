__________________________________________________Best Solution__________________________________________________________________
class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        int head = 0;
        int longest = 0;
        // only need to record the head of the string,
        for (int i = 0; i < arr.length; i++) {
            for (int j = head; j < i; j++) {
                // there are two duplicate characters in the string, then move head to next char
                // shorten the length.
                if (arr[i] == arr[j]) {
                    head = j + 1;
                    // break to find next tail.
                    break;
                }
            }
            if (i + 1 - head > longest) longest = i + 1 - head;
        }
        return longest;
    }
}
______________________________________________________My Solution(improved)___________________________________________________
public class Solution {
    // Array records last position of current duplicate character
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
        // i always keep start at char that is not duplicated. 
        // eg, abba i wll be 1 when met j = abb, i will be 1(max(0, 1) when met abba
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
______________________________________________________My Solution_____________________________________________________________
class Solution {
// use hashset
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) return 0;
        char[] c = s.toCharArray();
        int i = 0, j = 0;
        int length = 0;
        HashSet<Character> recorder = new HashSet();
        while(i < s.length() && j < s.length()){
            if(!recorder.contains(c[j])){
                recorder.add(c[j]);
                j++;
                length = Math.max(length, j - i);
            }
            else{
                recorder.remove(c[i]);
                i++;
            }
        }
        return length;
    }
}
