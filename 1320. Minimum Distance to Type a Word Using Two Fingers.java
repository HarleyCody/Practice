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
