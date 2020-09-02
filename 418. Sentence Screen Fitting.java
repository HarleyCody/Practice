______________________________________________________________________________Best Solution______________________________________________________________________________
public class Solution {
    // Sliding window to calcualte length of sentence start at word i;
    // calcualte word by word and sentence by sentce to get total words can get
    // totalword / num of words in sentece = sentence;
    // dp[i] in a row start at i end at dp[i]; num of words = dp[i] - i;
    
    // record end of starting from every word; to see how far a word can go;
    // only calculate row rows, num of word of each line is dp[start] - start;
    // update start to dp[start] / n; and calcualte next line;
    public int wordsTyping(String[] sentence, int rows, int cols) {
        // dp[i] records start at i, how far the sentence can go.
        int[] dp = new int[sentence.length];
        int n = sentence.length;
        for(int i = 0, prev = 0, len = 0; i < n; ++i) {
            // remove the length of previous word and space
            if(len > 0){
                len -= sentence[i - 1].length() + 1;
            }
            // calculate the start of next line.
            // it's OK the index is beyond the length of array so that 
            // we can use it to count how many words one row has.
            while(len + sentence[prev % n].length() <= cols) len += sentence[prev++ % n].length() + 1;
            dp[i] = prev;
            //System.out.println("end at word " + sentence[prev % n]);
        }
        int count = 0;
        // add line by line
        for(int i = 0, k = 0; i < rows; ++i) {
            // count how many words one row has and move to start of next row.
            // It's better to check if d[k] == k but I find there is no test case on it. 
            // if(dp[k] == k) return 0;
            count += dp[k] - k;
            k = dp[k] % n;
        }
        // divide by the number of words in sentence
        return count / n;
    }
}
