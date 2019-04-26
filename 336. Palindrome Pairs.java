_______________________________________________________Better Solution___________________________________________________________________
class Solution {
    private static class TrieNode {
        TrieNode[] next;
        int index;
        List<Integer> list;
    	
        TrieNode() {
    	    next = new TrieNode[26];
    	    index = -1;
            
/*integer list to each TrieNode; the list will record the indices of all words satisfying the following two conditions: 
1. each word has a suffix represented by the current TrieNode; 
2. the rest of the word forms a palindrome.
    1st case : prefix of String a + latter part of a(palindrome) + whole String b = new palindrome.
    2nd case : whole String a + prefix of String b(palindrome) + suffix of String b = new palindrome.
    
List usage: in case of a.append(a) == 'aa' also is palindrome but invalid result as 'a' can only be used for once.*/
    	    list = new ArrayList<>();
        }
    }
    
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        TrieNode root = new TrieNode();// set start of prefix tree
		
        for (int i = 0; i < words.length; ++i) {
            addWord(root, words[i], i);// add words to the prefix tree
        }
		
        for (int i = 0; i < words.length; ++i) {
            search(words[i], i, root, res);// 
        }
    
        return res;
    }
    
    
    //build tree by one word by one word build path of first one then build path of second one.
    private void addWord(TrieNode root, String word, int index) {// index is the postion of word in the words[]
        // as palindrome string, so comparing from the tail to head(reverse order)    common suffix.
        for (int i = word.length() - 1; i >= 0; --i) {
            int j = word.charAt(i) - 'a';
				
            if (root.next[j] == null) {// if j does not have any children Node
                root.next[j] = new TrieNode(); // set j as root, give it a children Node index = charAt(j) - 'a'
            }
				
            if (isPalindrome(word, 0, i)) {//  rest part and has common suffix
                root.list.add(index); // index of word which is valid words and has palindromic rest part and common suffix.
            }
				
            root = root.next[j];// change root to node [j] continue record word in reverse order.
        }
    	
        root.list.add(index);//root has been changed here, list is new empty list, set index to the new list.
        root.index = index;// updata index for the new root node.
    }
    
/*1. each word has a suffix represented by the current TrieNode; 
  2. the rest of the word forms a palindrome.*/
    private void search(String word, int i, TrieNode root, List<List<Integer>> res) {
        
        //1st case : prefix of String a + latter part of a(palindrome) + whole String b = new palindrome.
        for (int j = 0; j < word.length(); ++j) {
            
            // root.index >=0 valid word, current node is not the word being compared right now.(i != j)
    	    if (root.index >= 0 && root.index != i && isPalindrome(word, j, word.length() - 1)) {
    	        res.add(Arrays.asList(i, root.index));// one pair of answer;
    	    }
    		
    	    root = root.next[word.charAt(j) - 'a']; //root change to next node;
      	    if (root == null) return;// reach to the end of word
        }
    	
        //2nd case : whole String a + prefix of String b(palindrome) + suffix of String b = new palindrome.
        for (int j : root.list) {
    	    if (i == j) continue;
    	    res.add(Arrays.asList(i, j));
        }
        
        //res will not repeated beacuse the word is unique, so the searching will be in the different level, which implicates different index
    }
    
    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
    	    if (word.charAt(i++) != word.charAt(j--)) return false;
        }
    	
        return true;
    }
}




















_________________________________________________________My Solution_________________________________________________________________________
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < words.length; ++ i){
            StringBuilder pre = new StringBuilder(words[i]);
            for( int j = 0; j < words.length; ++j){
                if(i == j || 
                   (words[i].length() != 0 && words[j].length() != 0 && 
                    words[i].charAt(0) != words[j].charAt(words[j].length()-1)))continue;// filter repeated and words[i] words[j] that can not be paired
                StringBuilder lat = new StringBuilder(words[j]);
                StringBuilder target = new StringBuilder(pre);
                target.append(lat);//get string = word[i]+word[j] determine it is palindromic or not.
                if(isPalindromic(target)){
                    ans.add(Arrays.asList(i,j));
                }
                target.setLength(0);
            }
        }
        return ans;
    }
    public boolean isPalindromic(StringBuilder s){
        int n = s.length();
        if(n%2 == 0)// if length == even that the center is n/2 - 1 and n/2
            return judger(s, n, n/2-1, n/2);
        return judger(s, n, n/2, n/2);// if length == odd, center is n/2;
    }
    public boolean judger(StringBuilder s, int len, int lmid, int rmid){// check is palindromic by expending from center to margin.
        while(0<= lmid && rmid < len){
            if(s.charAt(lmid--) == s.charAt(rmid++))continue;
            return false;
        }
        return true;
    }
}
