_____________________________________________________________________________Best Solution_____________________________________________________________________________
class Solution {
    // memoization
    // record how many stickers needed for string;
    // status comes from prev freq to current freq
    public int minStickers(String[] stickers, String target) {
        int m = stickers.length;
        int[][] mp = new int[m][26];
        Map<String, Integer> dp = new HashMap<>();
        for (int i = 0; i < m; i++) 
            for (char c:stickers[i].toCharArray()) mp[i][c-'a']++;
        dp.put("", 0);
        return helper(dp, mp, target);
    }
    private int helper(Map<String, Integer> dp, int[][] mp, String target) {
        if (dp.containsKey(target)) return dp.get(target);
        int ans = Integer.MAX_VALUE, n = mp.length;
        int[] tar = new int[26];
        for (char c:target.toCharArray()) tar[c-'a']++;
        // try every sticker
        for (int i = 0; i < n; i++) {
            // optimization
            if (mp[i][target.charAt(0)-'a'] == 0) continue;
            StringBuilder sb = new StringBuilder();
            // apply a sticker on every character a-z
            for (int j = 0; j < 26; j++) {
                if (tar[j] > 0 ) 
                    for (int k = 0; k < Math.max(0, tar[j]-mp[i][j]); k++)
                        sb.append((char)('a'+j));
            }
            String s = sb.toString();
            int tmp = helper(dp, mp, s);
            if (tmp != -1) ans = Math.min(ans, 1+tmp);
        }
        dp.put(target, ans == Integer.MAX_VALUE? -1:ans);
        return dp.get(target);
    }
}
____________________________________________________________________________My Solution_____________________________________________________________________________
class Solution {
    // bfs, record status in q
    // status include curTar, curRemain, lastPosition
    // return if curRemain == 0;
    // if last bfs has same min remain with current bfs return -1 (impossible)
    class Node{
        int[] tar;
        int lastPos;
        int rest;
        public Node(int[] freq, int idx, int re){
            tar = freq;
            lastPos = idx;
            rest = re;
        }
    }
    public int minStickers(String[] stickers, String target) {
        int len = stickers.length;
        
        int[][] nei = new int[len][26];
        for(int i = 0; i < len; ++i){
            for(char c : stickers[i].toCharArray()){
                ++nei[i][c - 'a'];
            }
        }
        
        int[] tar = new int[26];
        int num = target.length();
        for(char c : target.toCharArray()){
            ++tar[c - 'a'];
        }
        
        Queue<Node> q = new LinkedList();
        q.offer( new Node(tar, 0, num));
        int step = 0, min = num, curMin = num;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                Node cur = q.poll();
                int start = cur.lastPos;
                while(start < len){
                    int[] nTar = cur.tar.clone();
                    num = cur.rest;
                    int reduced = 0;
                    for(int i = 0; i < 26; ++i){
                        if(nei[start][i] == 0 || nTar[i] == 0){
                            continue;
                        }
                        int cReduced = Math.min(nei[start][i], nTar[i]);
                        reduced += cReduced;
                        nTar[i] -= cReduced;
                    }
                    num -= reduced;
                    if(num == 0){
                        return step + 1;
                    }
                    if(reduced != 0){
                        q.offer(new Node(nTar, start, num));
                    }
                    ++start;
                    curMin = Math.min(num, curMin);
                }
            }
            if(curMin == min){
                break;
            }
            min = curMin;
            ++step;
        }
        
        return -1;
    }
}
