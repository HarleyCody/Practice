//My Solution: Backtack every time we can pick words[i] or donot. compare result at end
class Solution{
	int maxScore = 0;
    public int maxScoreWords(String[] words, char[] letters, int[] scores){
	    int[] freq = new int[26];
	    for(char c : letters){
	        ++freq[c - 'a'];
        }
        getMaxScore(words, freq, scores, -1, 0);

        return maxScore;
    }

    private void getMaxScore(String[] words, int[] freq, int[] scores, int idx, int curScore){

        ++idx;
        if(idx == words.length){
            maxScore = Math.max(curScore, maxScore);
            return;
        }
        getMaxScore(words, freq, scores, idx, curScore);

        int result[][] = useWord(freq, words[idx], scores);
        if(result[0][0] < 0) return;
        getMaxScore(words, result[1], scores, idx, curScore + result[0][0]); 
    }

    private int[][] useWord(int[] freq, String word, int[] score){
        int[][] result = new int[2][];
        int resultScore = 0;
        int[] nFreq = freq.clone();
        for(char c : word.toCharArray()){
            --nFreq[c - 'a'];
            if(nFreq[c - 'a'] < 0){
                resultScore = -1;
                break;
            }
            resultScore += score[c - 'a'];
        }
        result[0] = new int[]{resultScore};
        result[1] = nFreq;
        return result;
    }
}
