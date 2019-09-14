class Solution {
    // distance = abs(words2 - words1);
    // two pointers one pass;
    
    public int shortestDistance(String[] words, String word1, String word2) {
        int pos1 = -1, pos2 = -1;
        // two pointers on the mark, pos1 records position of word1 pos2 records position of word2;
        while(++pos1 < words.length && !words[pos1].equals(word1));
        while(++pos2 < words.length && !words[pos2].equals(word2));
        // get initial distance between two pointers
        int ans = Math.abs(pos2 - pos1);
        while(pos1 < words.length && pos2 < words.length){
            // if words1 is slower than pos2, pos1 chase pos2 until it passes pos2
            if(pos1 < pos2){
                // run to next mark(next word1);
                while(++pos1 < words.length && !words[pos1].equals(word1));
                // if pos1 >= words.length : cannot find next word1
                if(pos1 < words.length){
                    ans = Math.min(Math.abs(pos2 - pos1), ans);
                }
            }else{
                // run to next word2
                while(++pos2 < words.length && !words[pos2].equals(word2));
                // if pos2 >= words.length : cannot find next word2
                if(pos2 < words.length){
                    ans = Math.min(Math.abs(pos2 - pos1), ans);
                }
            }
        }
        return ans;
    }
}
