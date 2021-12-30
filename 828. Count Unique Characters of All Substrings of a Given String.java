//Best Solution: Dp, record the number of unique chars ends at chs[i]
//                  every time add i - last number of unique chars and delete previous unique chars.
class Solution {
     public int uniqueLetterString(String s) {
        int[] lastPosition = new int[26];
        int[] previousPosition = new int[26];

        int result = 0;
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int last = lastPosition[c-'A'];
            result += (i - last+1);
            int previous = previousPosition[c-'A'];
            result -= (last-previous);
            previousPosition[c-'A'] = lastPosition[c-'A'];
            lastPosition[c-'A'] = i+1;
            count += result;
        }
        
        return count;
    }
}
