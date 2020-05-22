_____________________________________________________________Best Solution______________________________________________________________
class Solution {
    // Reversely think
    // replcae target from every possible i; record index, from target to start
    public int[] movesToStamp(String stamp, String target) {
        char[] sChar = stamp.toCharArray();
        char[] tChar = target.toCharArray();
        
        List<Integer> ansList = new ArrayList();
        
        int sLen = sChar.length, tLen = tChar.length;
        int numStars = 0;
        boolean[] visited = new boolean[tLen];
        boolean isReplaced = true;
        while(numStars < tLen && isReplaced){
            isReplaced = false;
            for(int i = 0; i <= tLen - sLen; ++i){
                if(visited[i]){
                    continue;
                }
                if(canReplace(sChar, sLen, tChar, i)){
                    visited[i] = true;
                    ansList.add(i);
                    isReplaced = true;
                    numStars += replace(sLen, tChar, i); 
                    if(numStars == tLen){
                        break;
                    }
                }
                
            }
        }
        if(!isReplaced){
            return new int[0];
        }
        int size = ansList.size();
        int[] ans = new int[size];
        for(int i = size - 1; 0 <= i; --i){
            ans[size - i - 1] = ansList.get(i);
        }
        return ans;
    }
    
    private boolean canReplace(char[] sChar, int len, char[] tChar, int p){
        for(int i = 0; i < len; ++i){
            if(tChar[i + p] != '*' && tChar[i + p] != sChar[i]){
                return false;
            }
        }
        return true;
    }
    
    private int replace(int len, char[] tChar, int p){
        int num = 0;
        for(int i = 0; i < len; ++i){
            if(tChar[i + p] == '*'){
                continue;
            }
            tChar[i + p] = '*';
            ++num;
        }
        return num;
    }
}
_______________________________________________________________TLE Badly________________________________________________________________
class Solution {
    // bfs + dfs
    class Node{
        int idx;
        String str;
        
        public Node(int i, String s){
            idx = i;
            str = s;
        }
    }
    HashMap<String, Node> graph;
    public int[] movesToStamp(String stamp, String target) {
        int[] ans = new int[0];
        graph = new HashMap();
        int len = target.length(), steps = 0;
        StringBuilder startSb = new StringBuilder();
        for(int i = 0; i < len; ++i){
            startSb.append('?');
        }
        Queue<Node> q = new LinkedList();
        HashSet<String> visited = new HashSet();
        
        char[] ogChars = stamp.toCharArray();
        int ogLen = ogChars.length;
        
        q.offer(new Node(-1, startSb.toString()));
        boolean found = false;
        while(!q.isEmpty() && steps < 10 * len && !found){
            int size = q.size();
            while(size-- > 0){
                Node curNode = q.poll();
                String curStr = curNode.str;
                char[] chs = curStr.toCharArray();
                int cLen = 0;
                for(int l = 0; l <= len - ogLen; ++l){
                    String newStr = copy(chs, l, ogChars);
                    if(!visited.add(newStr)){
                        continue;
                    }
                    q.offer(new Node(l, newStr));
                    graph.put(newStr, curNode);
                    if(newStr.equals(target)){
                        ans = new int[steps + 1];
                        ans[steps] = l;
                        found = true;
                        break;
                    }
                }
                if(found){
                    break;
                }
            }
            ++steps;
        }
        if(!found){
            return ans;
        }
        return findPath(target, ans, steps - 2);
    }
    private int[] findPath(String start, int[] ans, int idx){
        if(idx < 0){
            return ans;
        }
        Node prevNode = graph.get(start);
        ans[idx] = prevNode.idx;
        findPath(prevNode.str, ans, idx - 1);
        return ans;
    }
    
    private String copy(char[] dst, int start, char[] src){
        int len = src.length;
        char[] ans = dst.clone();
        for(int i = 0; i < len; ++i){
            ans[start + i] = src[i];
        }
        return new String(ans);
    }
}
