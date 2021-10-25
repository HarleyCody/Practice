//Best Solution: Binary Seach, do not need to sort, only guess the word in the middle
//Clean the candidates by comparing match, minimize the candidate size by two pointers
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        int count = 0;
        int length = wordlist.length;
        while(count<10){
            int idx = length/2;
            String selected = wordlist[idx];
            int matches = master.guess(selected);
            for(int i = 0; i < length; i++){
                if(helper(wordlist[i],selected)!=matches){
                    length--;
                    wordlist[i] = wordlist[length];
                }
            }
            count++;
        }
    }
    public int helper(String s, String t){
        int res = 0;
        for(int i = 0; i < 6; i++){
            if(s.charAt(i)==t.charAt(i))
                res++;
        }
        return res;
    }
}

//Naive Solution: Guess Word by the calculating score of each word, find best guess by hightest score
// Score == sum of frequence;
// Clean word candidates after guess with match
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
