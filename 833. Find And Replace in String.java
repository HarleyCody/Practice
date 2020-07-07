______________________________________________________________________________My Solution2______________________________________________________________________________
class Solution {
    // sort by insertion, not by pq, as range is known;
    // check one by one use equals and substring to compare;
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int len = S.length();
        if(len == 0){
            return S;
        }
        
        char[] chs = S.toCharArray();
        String[] src = new String[len];
        String[] tar = new String[len];
        for(int i = 0; i < indexes.length; ++i){
            int idx = indexes[i];
            src[idx] = sources[i];
            tar[idx] = targets[i];
        }

        int prev = 0;
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < len; ++i){
            if(src[i] == null){
                ans.append(chs[i]);
                continue;
            }
            int l = src[i].length();
            if(i + l <= len && S.substring(i, i + l).equals(src[i])){
                ans.append(tar[i]);
                i += l - 1;
            }else{
                ans.append(chs[i]);
            }
        }
        return ans.toString();
    }
}
______________________________________________________________________________My Solution1______________________________________________________________________________
class Solution {
    //encapsulate class and sort by idx
    //read, compare, replace one by one
    class Pair{
        int idx;
        String src;
        String tar;
        public Pair(int i, String s, String t){
            idx = i;
            src = s;
            tar = t;
        }
    }
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        char[] chs = S.toCharArray();
        int len = indexes.length;
        if(len == 0){
            return S;
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((p1, p2) -> p1.idx - p2.idx);
        for(int i = 0; i < len; ++i){
            pq.offer(new Pair(indexes[i], sources[i], targets[i]));
        }
        
        StringBuilder ans = new StringBuilder(S.substring(0, pq.peek().idx));
        while(!pq.isEmpty()){
            Pair cur = pq.poll();
            int next = pq.isEmpty() ? S.length() : pq.peek().idx;
            if(compare(chs, cur.idx, cur.src.toCharArray())){
                ans.append(cur.tar);
                ans.append(S.substring(cur.idx + cur.src.length(), next));
            }else{
                ans.append(S.substring(cur.idx, next));
            }
        }
        return ans.toString();
    }
    
    private boolean compare(char[] og, int s, char[] can){
        int i = 0;
        while(s + i < og.length && i < can.length && og[s + i] == can[i]){
            ++i;
        }
        return i == can.length;
    }
}
