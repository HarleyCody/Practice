________________________________________________________________________________dp Solution_______________________________________________________________________________
/*Imagine that we tap all letters with only one finger.
The res distance we get is the maximum distance we will need.

In our dynamic programming, dp[a] means that,
if our left finger ends at character a,
the maximum we can save is dp[a].

Now our right finger tapped all letters, and left finger did nothing.
We iterate through the whole string one by one
and select some letter to tap with the left finger.
By doing this, we want to find out the maximum distance that we can save from the tapping with one finger.

Assume that our left finger is at a now,
our right finger is at b,
and we the right finger will tap c next.

Instead of moving right finger from b to c with distance d(b, c),
we try moving left finger from a to c with distance d(a, c).
Hopely this will save d(b, c) - d(a, c).

And finaly, we have one fingers at b and the other at c now.
The finger at b will be new left finger, and the other will be the rihgt.*/

class Solution {
    public int minimumDistance(String word) {
        int dp[] = new int[26], res = 0, save = 0, n = word.length();
        for (int i = 0; i < n - 1; ++i) {
            int b = word.charAt(i) - 'A', c = word.charAt(i + 1) - 'A';
            for (int a = 0; a < 26; ++a)
                dp[b] = Math.max(dp[b], dp[a] + d(b, c) - d(a, c));
            save = Math.max(save, dp[b]);
            res += d(b, c);
        }
        return res - save;

    }

    private int d(int a, int b) {
        return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
    }
}
    
________________________________________________________________________________My Best Solution________________________________________________________________________
class Solution {
    // improve use array instead of hashMap
    // dfs + memoization
    // recorder[i][hash] record the minDis when charAt(i)[fin1 * 27 + fin0]
    int ans = Integer.MAX_VALUE;
    int[][] recorder;
    public int minimumDistance(String word) {
        recorder = new int[word.length()][730];
        return type(word.toCharArray(), 0, 26, 26);
    }
    
    private int type(char[] chs, int idx, int fin0, int fin1){
        if(idx == chs.length){
            return 0;
        }
        
        Integer key = fin0 * 27 + fin1;
        if(recorder[idx][key] != 0){
            return recorder[idx][key];
        }
        
        int ans = Integer.MAX_VALUE;
        
        int[] tar = getPos(chs[idx] - 'A');
        int curDis = fin0 == 26 ? 0 : getDis(tar, getPos(fin0));
        
        ans = Math.min(ans, curDis + type(chs, idx + 1, chs[idx] - 'A', fin1));
        curDis = fin1 == 26 ? 0 : getDis(tar, getPos(fin1));
        ans = Math.min(ans, curDis + type(chs, idx + 1, fin0, chs[idx] - 'A'));
        
        recorder[idx][key] = ans;
        return ans;
    }
    
    private int[] getPos(int hash){
        return new int[]{hash / 6, hash % 6};
    }
    
    private int getDis(int[] a, int[] b){
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
________________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    // dfs + memoization
    // recorder[i]<Integer, Integer> record the minDis when charAt(i).get(fin1 * 27 + fin0)
    int ans = Integer.MAX_VALUE;
    HashMap<Integer, Integer>[] recorder;
    public int minimumDistance(String word) {
        recorder = new HashMap[word.length()];
        return type(word.toCharArray(), 0, 26, 26);
    }
    
    private int type(char[] chs, int idx, int fin0, int fin1){
        if(idx == chs.length){
            return 0;
        }
        
        Integer key = fin0 * 27 + fin1;
        if(recorder[idx] != null && recorder[idx].containsKey(key)){
            return recorder[idx].get(key);
        }
        
        int ans = Integer.MAX_VALUE;
        
        int[] tar = getPos(chs[idx] - 'A');
        int curDis = fin0 == 26 ? 0 : getDis(tar, getPos(fin0));
        
        ans = Math.min(ans, curDis + type(chs, idx + 1, chs[idx] - 'A', fin1));
        curDis = fin1 == 26 ? 0 : getDis(tar, getPos(fin1));
        ans = Math.min(ans, curDis + type(chs, idx + 1, fin0, chs[idx] - 'A'));
        
        if(recorder[idx] == null){
            recorder[idx] = new HashMap();
        }
        
        recorder[idx].put(key, ans);
        return ans;
    }
    
    private int[] getPos(int hash){
        return new int[]{hash / 6, hash % 6};
    }
    
    private int getDis(int[] a, int[] b){
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
