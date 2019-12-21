______________________________________________________Best Solution___________________________________________________________
class Solution {
// preporecess, exchange pair that both can be placed in correct place;
// dfs(swap until mactch;
    public int kSimilarity(String A, String B) {
        if (A.equals(B)) {
            return 0;
        }
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int cnt = preProcess(a, b);
        cnt += dfs(a, b, 0);
        return cnt;
    }
    
    public int dfs(char[] a, char[] b, int start) {
        if (start == a.length) {
            return 0;
        }
        if (a[start] == b[start]) {
            return dfs(a, b, start + 1);
        }
        int min = Integer.MAX_VALUE;
        for (int i = start + 1; i < a.length; i++) {
            if (a[i] == b[start] && a[i] != b[i]) {
                swap(a, start, i);
                min = Math.min(min, 1 + dfs(a, b, start + 1));
                swap(a, start, i);
            }
        }
        return min;
    }
    
    public int preProcess(char[] a, char[] b) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) {
                continue;
            }
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] == b[j] && a[j] == b[i]) {
                    swap(a, i, j);
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }
    
    public void swap(char[] chars, int p1, int p2) {
        char temp = chars[p1];
        chars[p1] = chars[p2];
        chars[p2] = temp;
    }
}
___________________________________________________My Solution(BFS)____________________________________________________________
class Solution {
// skip same and only change where cur possibly can go;
    public int kSimilarity(String A, String B) {
        if(A.equals(B)) return 0;
        HashSet<String> visited = new HashSet();
        Queue<String> q = new LinkedList();
        q.add(A);
        int ans = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            ++ans;
            for(int i = 0; i < size; ++i){
                String cur = q.poll();
                int j = 0;
                while(cur.charAt(j) == B.charAt(j)){
                    ++j;
                }
                for(int k = j + 1; k < A.length(); ++k){
                    // skip same and only change where cur possibly can go;
                    if(cur.charAt(k) == B.charAt(k) || cur.charAt(j) != B.charAt(k)) continue;
                    String temp = swap(cur, j, k);
                    if(temp.equals(B))return ans;
                    if(visited.add(temp)) q.add(temp);
                }
            }
        }
        return ans;
    }
    private String swap(String s, int i, int j){
        char[] c = s.toCharArray();
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
        return new String(c);
    }
}
