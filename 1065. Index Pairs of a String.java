//My Solution: record one by one, brute force. For each is faster than for iteration
class Solution {
    public int[][] indexPairs(String text, String[] words) {
        int len = words.length;
        List<int[]> ansList = new ArrayList();
        int idxStart = 0;
        int wordLength;
        
        for(String word : words){
            idxStart = text.indexOf(word);
            wordLength = word.length();
            while(idxStart >= 0){
                ansList.add(new int[]{idxStart, idxStart + wordLength - 1});
                idxStart = text.indexOf(word, idxStart + 1);
            }
        }
        
        Collections.sort(ansList, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        return ansList.toArray(new int[0][]);
    }
}
