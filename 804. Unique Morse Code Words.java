__________________________________________________________________My Solution____________________________________________________________________________________
// translate and record by hashSet
class Solution {
    String[] morses = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    HashSet<String> wtm = new HashSet();
    public int uniqueMorseRepresentations(String[] words) {
        for(String word : words){
            wtm.add(translate(word));
        }
        return wtm.size();
    }
    
    private String translate(String w){
        char[] chs = w.toCharArray();
        
        StringBuilder sb = new StringBuilder();
        for(char c : chs){
            sb.append(morses[c - 'a']);
        }
        
        return sb.toString();
    }
}
