class AutocompleteSystem {
  private static class Node {
    public Node[] children = new Node[27];
    public List<String> hotlist = new ArrayList<>(3);// every char has a hotlist
  }  
  private Node root = new Node();
  private Map<String, Integer> dict = new HashMap<>(); // word -> count
  private Comparator<String> comp = new Comparator<String>() {
    public int compare(String a, String b) {
      int cnt1 = dict.get(a);
      int cnt2 = dict.get(b);
      if (cnt1 == cnt2) return a.compareTo(b);// if the frequence is same, order lexicographically.
      return cnt2 - cnt1;// descedent order.
    }
  };
  
  private Node nextNode(Node curr, char ch) {// return the postition of node for current char. create if null return if exist
    if (ch == ' ') ch = '{'; // when user input a space store the space as '{' so it is fastr to check the hotlist of space.
    int idx = ch - 'a';
    if (curr.children[idx] == null) curr.children[idx] = new Node();
    return curr.children[idx];
  }
  private void addWord(String s, boolean newWord) { // build a tree for every word
    Node curr = root;
    for (char ch : s.toCharArray()) {expend curr to the end of tree(last char of word)
      curr = nextNode(curr, ch);
      // update hotlist.
      if (newWord || !curr.hotlist.contains(s)) curr.hotlist.add(s);//add a new word to the hotlist of every char in word
      Collections.sort(curr.hotlist, comp);//sort by frequence
      if (curr.hotlist.size() > 3) curr.hotlist.remove(curr.hotlist.size() - 1);// only store the 3 most frenquent words in the list.
    }
  }
  public AutocompleteSystem(String[] sentences, int[] times) {
    for (int i=0; i<sentences.length; ++i) {
      dict.put(sentences[i], times[i]); // record the frequence of word
    }
    for (String s : sentences) addWord(s, true); // label word
  }
  
  private StringBuilder sb = new StringBuilder();
  private Node currSearch = root;
  public List<String> input(char c) {
    if (c == '#') {// end of searching
      String s = sb.toString();
      int cnt = dict.getOrDefault(s, 0) + 1;
      dict.put(s, cnt);// Update dict:save the sentence to the dictionary
      addWord(s, cnt == 1);// build a tree for new input if it is not in the dictionary before
      currSearch = root;
      sb.setLength(0);//prepare for next new input
      return new ArrayList<>();// or currSearch.hotlist: return the hotlist of new word ( should be a empty cause it is first appear in the serach)
    }    
    sb.append(c);
    currSearch = nextNode(currSearch, c);
    return currSearch.hotlist;
  }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
