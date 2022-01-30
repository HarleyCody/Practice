//Best Solution: remove one character by bitmask, use hash set to record start
class Solution {    
    public int wordCount(String[] startWords, String[] targetWords) {
        HashSet<Integer> st = new HashSet<>();
        boolean hasStr25 = false;
        int str26Num = 0;
        // store $startWords as masks in $st
        for (String s : startWords) {
            // 26 long start word is useless
            if (s.length() == 26)
                continue;
            // 25 long start word could only become 26 length target word
            if (s.length() == 25){
                hasStr25 = true;
                continue;
            }
            int mask = 0;
            for (char c : s.toCharArray())
                mask |= 1 << (c - '0');
            st.add(mask);
        }
        int ret = 0;
        for (String s : targetWords) {
            // can't be create
            if (s.length() == 1)
                continue;
            // can be create if $hasStr25
            if (s.length() == 26){
                str26Num++;
                continue;
            }
            int mask = 0;
            for (char c : s.toCharArray())
                mask |= 1 << (c - '0');
            boolean ok = false;
            for (char c : s.toCharArray())
                ok |= st.contains(mask ^ (1 << (c - '0')));
            if (ok)
                ret++;
        }
        return ret + (hasStr25 ? str26Num : 0);
    }
}

//My Solution: Find by trie and skip once
class Solution{
	class TrieNode{
        char c;
		boolean isStart = false;
		TrieNode[] kids = new TrieNode[26];
    }
    TrieNode root = new TrieNode();
	public int wordCount(String[] startWords, String[] targetWords){
		for(String word : startWords){
		    build(word.toCharArray());
        }

        int ans = 0;
        for(String word : targetWords){
            ans += find(word.toCharArray()) ? 1 : 0;
            //System.out.println(word + " " + find(word.toCharArray()));
        }
        return ans;
    }

    private void build(char[] chs){
        Arrays.sort(chs);
        TrieNode curNode = root;
        for(char c : chs){
            int idx = c - 'a';
            if(curNode.kids[idx] == null){
                curNode.kids[idx] = new TrieNode();
            }
            curNode = curNode.kids[idx];
            curNode.c = c;
        }
        curNode.isStart = true;
    }

    private boolean find(char[] chs){
        Arrays.sort(chs);
        TrieNode curNode = root;
        //System.out.println("find " + new String(chs));
        for(int i = 0; i < chs.length - 1; ++i){
            int idx = chs[i] - 'a';
            if(curNode == null){
                return false;
            }
            if(findWithMiss(chs, i + 1, curNode)){
                return true;
            }
            
            curNode = curNode.kids[idx];
        }
        
        return curNode != null && curNode.isStart;
    }
    
    private boolean findWithMiss(char[] chs, int startIdx, TrieNode curNode){
        for(int i = startIdx; i < chs.length; ++i){
            int idx = chs[i] - 'a';
            if(curNode.kids[idx] == null) return false;
            curNode = curNode.kids[idx];
        }
        return curNode.isStart;
    }
}
