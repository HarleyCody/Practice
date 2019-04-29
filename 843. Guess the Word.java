/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {// guess word with sum of character frequence of word character
    public void findSecretWord(String[] wordlist, Master master) {
        Set<String> words = new HashSet<String>();
        int[] fre = new int[26];
        for(String s : wordlist){
            for(char c : s.toCharArray()){
                ++fre[c - 'a'];
            }
            words.add(s);
        }
        for(int i = 0; i < 10; ++i){
            String guess = bestGuess(words, fre);
            int match = master.guess(guess);
            for(Iterator<String> it = words.iterator(); it.hasNext();){
                if(match(it.next(), guess) != match)
                    it.remove();
            }
        }
    }
    public String bestGuess(Set<String> words, int[] fre){
        int count = 0, max = 0;
        String ans ="";
        for(String s : words){
            count = 0;
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
        int count = 0;
        for(int i = 0; i < a.length(); ++i){
            if(a.charAt(i) == b.charAt(i)) ++count;
        }
        return count;
    }
}
