_______________________________________________________Best Solution___________________________________________________________
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    	final int len = searchWord.length();
        final List<List<String>> result = new ArrayList<>(len);
        Arrays.sort(products);
        int start = 0, end = products.length-1;
        for (int i = 0; i < len; ++i) {
        	final char ch = searchWord.charAt(i);
        	final List<String> list = new ArrayList<>();
        	
        	int prevStart = start-1, prevEnd = end+1;
        	while ((start <= end) && (start < products.length) && (end >= 0)) {
                // find start
        		if (prevStart != start) {
        			prevStart = start;
            		if ((products[start].length() <= i) || (products[start].charAt(i) != ch)) {
            			++start;
            		}
        		}
                // find end
        		if (prevEnd != end) {
        			prevEnd = end;
            		if ((products[end].length() <= i) || (products[end].charAt(i) != ch)) {
            			--end;
            		}
        		}
                // prev = cur, means cur is stable, will not change.
        		if ((prevEnd == end) && (prevStart == start))
        			break;
        	}
        	// add list
        	final int tmp = (end-start) > 2 ? start+2 : end;
    		for (int j = start; j <= tmp; ++j) {
    			if (j < products.length)
    				list.add(products[j]);
    		}
        	
        	result.add(list);
        }
        return result;
    }
}
_______________________________________________________Improved Solution___________________________________________________________
// treeMap
//floor the greatest string not bigger(<=) than target;
// ceiling the smallest string not smaller(>=) than target;
// choose three from range ceiling and floor(floor should be min(ceiling + 2, floor); three or not enough for three.
// sublist of ceiling ~ floor
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        Arrays.sort(products);
        List<String> productsList = Arrays.asList(products);

        for (int i = 0; i < products.length; i++) {
            map.put(products[i], i);
        }
        String key = "";
        for (char c : searchWord.toCharArray()) {
            key += c;
            String ceiling = map.ceilingKey(key);
            // ~ the max in the ascii
            String floor = map.floorKey(key + "~");
            if (ceiling == null || floor == null) break;
            // floor : Returns the greatest key less than or equal to the given key
            // ceiling: Returns the least key greater than or equal to the given key
            res.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
        }

        while (res.size() < searchWord.length()) res.add(new ArrayList<>());
        return res;
    }
}
_________________________________________________________My Solution___________________________________________________________
// Trie store products
// search through dfs;
class Solution {
    class Trie{
        Trie[] next = new Trie[26];
        boolean isWord;
        String word;
    }
    private List<String> med = new LinkedList();
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie();
        for(String str : products){
            add(str, root);
        }
        List<List<String>> ans = new LinkedList();
        for(int i = 0; i < searchWord.length(); ++i){
            med = new LinkedList();
            if(root != null && root.next[searchWord.charAt(i) - 'a'] != null){
                root = root.next[searchWord.charAt(i) - 'a'];
                search(root);
            }else{
                // if product ad root will search abcd for d if root is not null
                // root = null stops search anymore.
                root = null;
            }
            ans.add(med);
        }
        return ans;
    }
    private void add(String str, Trie root){
        char[] c = str.toCharArray();
        int cur = 0;
        while(cur < c.length){
            if(root.next[c[cur] - 'a'] == null){
                root.next[c[cur] - 'a'] = new Trie();
            }
            root = root.next[c[cur] - 'a'];
            ++cur;
        }
        root.isWord = true;
        root.word = str; 
    }
    
    private void search(Trie root){
        if(med.size() == 3) return;
        if(root.isWord) med.add(root.word);
        for(int i = 0; i < 26; ++i){
            if(root.next[i] != null) {
                search(root.next[i]);
            }
        }
    }
}
