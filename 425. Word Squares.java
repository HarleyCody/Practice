____________________________________________________________________________________My Solution__________________________________________________________________________________
// use trie to recod prefix of words, each node contains list of words for composing
// trie + dfs
// dfs with trienodes to get prefix of next word
class Solution {
    class Node{
        Node[] kids = new Node[26];
        List<String> candidates = new ArrayList();
    }
    List<List<String>> ans = new ArrayList();
    int wLen;
    Node root = new Node();
    public List<List<String>> wordSquares(String[] words) {
        wLen = words[0].length();
        
        for(String str : words){
            build(str);
        }
        
        Node[] fNodes = new Node[wLen];
        for(int i = 0; i < wLen; ++i){
            fNodes[i] = root;
        }
        compose(fNodes, 0, new LinkedList());
        return ans;
    }
    private void build(String w){
        char[] chs = w.toCharArray();
        Node cur = root;
        for(char c : chs){
            cur.candidates.add(w);
            int idx = c - 'a';
            if(cur.kids[idx] == null){
                cur.kids[idx] = new Node();
            }
            cur = cur.kids[idx];
        }
        cur.candidates.add(w);
    }
    private void compose(Node fNodes[], int cur, LinkedList<String> can){
        if(cur == wLen){
            ans.add(new LinkedList(can));
            return;
        }
        
        List<String> words = fNodes[cur].candidates;
        for(String w : words){
            Node[] nFNodes = fNodes.clone();
            boolean canSearch = true;
            for(int nf = cur + 1; nf < wLen; ++nf){
                int idx = w.charAt(nf) - 'a';
                if(fNodes[nf].kids[idx] == null){
                    canSearch = false;
                    break;
                }
                nFNodes[nf] = fNodes[nf].kids[idx];
            }
            if(!canSearch){
                continue;
            }
            can.offer(w);
            compose(nFNodes, cur + 1, can);
            can.pollLast();
        }
    }
}
