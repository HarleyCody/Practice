______________________________________________________________________________Best Solution______________________________________________________________________________
// Interval Scheduling Maximization
// similar to meeting room, extend range and sort interval by end, make sure there is not overlap when add string to list
class Solution {
    private static class Interval {
        public int b;
        public int e;

        public Interval(int b, int e) {
            this.b = b;
            this.e = e;
        }
    }
    
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = s.charAt(i) - 'a';
        }
        
        int[] fst = new int[26];
        int[] lst = new int[26];
        for (int i = 0; i < n; i++) {
            lst[vals[i]] = i;
        }
        for (int i = n - 1; i >= 0; i--) {
            fst[vals[i]] = i;
        }
        
        List<Interval> t = new ArrayList<Interval>();
        for (int i = 0; i < 26; i++) {
            if (fst[i] < n) {
                int b = fst[i];
                int e = lst[i];
                for (int j = b; j <= e; j++) {
                    b = Math.min(b, fst[vals[j]]);
                    e = Math.max(e, lst[vals[j]]);
                }
                if (b == fst[i]) {
                    t.add(new Interval(b, e));
                }
            }
        }
        
        Collections.sort(t, Comparator.comparing(i -> i.e));
        List<String> ans = new ArrayList<String>();
        int prev = -1;
        for (Interval i : t) {
            if (i.b > prev) {
                ans.add(s.substring(i.b, i.e + 1));
                prev = i.e;
            }
        }
        
        return ans;
    }
}
