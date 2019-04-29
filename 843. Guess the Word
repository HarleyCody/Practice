/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        int[] fre = new int[26];
        HashSet<String> words = new HashSet<>();
        for(String s : wordlist){
            for(char c : s.toCharArray()){
                ++fre[c - 'a'];
            }
            words.add(s);
        }
        int i = 0;
        while(++i<=10){
            String best = bestGuess(words, fre);
            int match = master.guess(best);
            for(Iterator<String> it = words.iterator(); it.hasNext();){
                if(match(it.next(), best) != match)
                    it.remove();
            }
        }
    }
    
    public String bestGuess(Set<String> words, int[] fre){
        int max = 0;
        String ans = "";
        for(String s : words){
            int count = 0;
            for(char c : s.toCharArray()){
                count += fre[c - 'a'];
            }
            if(count > max){
                max = count;
                ans = s;
            }
        }
        return ans;
    }
    
    public int match(String a, String b){
        int ans = 0, count = 0;
        for(int i = 0; i < 6; ++i){
            if(a.charAt(i) == b.charAt(i))++count;
        }
        return count;
    }
}
