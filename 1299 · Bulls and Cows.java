//My Solution: Find cows by comparing one by one
//             Find bulls by accumulate the minFreq of secret char and guess char - cows
public class Solution {
    public String getHint(String secret, String guess) {
        char[] secretChars = secret.toCharArray();
        char[] guessChars = guess.toCharArray();

        int[] freqSecret = new int[10];
        int[] freqGuess = new int[10];
        int cows = 0;
        for(int i = 0; i < secretChars.length; ++i){
            if(secretChars[i] == guessChars[i]) ++cows;
            ++freqSecret[secretChars[i] - '0'];
            ++freqGuess[guessChars[i] - '0'];
        }
        int bulls = 0;
        for(int i = 0; i < 10; ++i){
            bulls += Math.min(freqSecret[i], freqGuess[i]);
        }
        bulls -= cows;
        return cows + "A" + bulls + "B";
    }
}
