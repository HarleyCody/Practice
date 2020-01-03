______________________________________________________Best Solution____________________________________________________________
// Traveling salesman problem NP hard problem( Hamilton cycle with minimal weight)
// can be solved by dfs(backtacking) or just dfs with recording minimal value;
class Solution {
    public String shortestSuperstring(String[] A) {
        int N = A.length;

        // Populate overlaps
        // record end index of overlaps in second string; when concate ans, k can be directly used;
        int[][] overlaps = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j) if (i != j) {
                int m = Math.min(A[i].length(), A[j].length());
                for (int k = m; k >= 0; --k)
                    if (A[i].endsWith(A[j].substring(0, k))) {
                        overlaps[i][j] = k;
                        break;
                    }
            }
        
        // 1<<N cause there are N * 2 kinds of status for cycle, every path can be build by choosing or not choosing i(eg, if the N is 2, two points) there are 00, 01, 10, 11 status
        // bitmask(ie, masK) is set of arrived node(arrvied 0: unvisited:1) 
        // dp[mask][i] = most overlap with mask, ending with ith element
        int[][] dp = new int[1<<N][N];
        // paretnt records the path of minimal weight of Hamilton Cycle
        
        int[][] parent = new int[1<<N][N];
        
        // use cur mask as previous mask for dp in next level
        for (int mask = 0; mask < (1<<N); ++mask) {
            Arrays.fill(parent[mask], -1);

            for (int bit = 0; bit < N; ++bit) if (((mask >> bit) & 1) > 0) {
                // update status for every possible tail node in mask; dp[mask][bit];
                // Let's try to find dp[mask][bit].  Previously, we had
                // a collection of items represented by pmask.
                int pmask = mask ^ (1 << bit);
                // previous is not calculated;
                if (pmask == 0) continue;
                // update dp[mask][bit] by checking its middle point;
                for (int i = 0; i < N; ++i) if (((pmask >> i) & 1) > 0) {
                    // i is middle point which is possibe in cur mask
                    // For each bit i in pmask, calculate the value
                    // if we ended with word i, then added word 'bit'.
                    // transition
                    int val = dp[pmask][i] + overlaps[i][bit];
                    if (val > dp[mask][bit]) {
                        dp[mask][bit] = val;
                        parent[mask][bit] = i;
                    }
                }
            }
        }

        // # Answer will have length sum(len(A[i]) for i) - max(dp[-1])
        // Reconstruct answer, first as a sequence 'perm' representing
        // the indices of each word from left to right.

        int[] perm = new int[N];
        boolean[] seen = new boolean[N];
        int t = 0;
        int mask = (1 << N) - 1;

        // p: the last element of perm (last word written left to right)
        int p = 0;
        for (int j = 0; j < N; ++j)
            if (dp[(1<<N) - 1][j] > dp[(1<<N) - 1][p])
                p = j;

        // Follow parents down backwards path that retains maximum overlap
        // get trace from end to start;
        // perm record idx of string with concate order
        while (p != -1) {
            perm[t++] = p;
            seen[p] = true;
            int p2 = parent[mask][p];
            //mask in next is calculated by pmask ^ (1<<p)
            //get pmask from mask, mask ^ (1<<p) as pmask ^ d ^ d == pmask)
            mask ^= 1 << p;
            p = p2;
        }

        // Reverse perm
        for (int i = 0; i < t/2; ++i) {
            int v = perm[i];
            perm[i] = perm[t-1-i];
            perm[t-1-i] = v;
        }

        // Fill in remaining words not yet added
        // has no connection with previous words.
        for (int i = 0; i < N; ++i) if (!seen[i])
            perm[t++] = i;

        // Reconstruct final answer given perm
        StringBuilder ans = new StringBuilder(A[perm[0]]);
        for (int i = 1; i < N; ++i) {
            int overlap = overlaps[perm[i-1]][perm[i]];
            ans.append(A[perm[i]].substring(overlap));
        }
        return ans.toString();
    }
}


______________________________________________________Flaw fastest Solution_______________________________________________________
// the new String combined by two strings might not have larger overlaps with the rest of strings
class Solution {
    // every time go through all the list and find two string with max overlap area, combine them to gether 
    // pair record content of string and length
    class Pair{
        String str;
        int len;
        
        public Pair(String str, int len){
            this.str = str;
            this.len = len;
        }
    }
    
    public String shortestSuperstring(String[] A) {
        
        if (A == null || A.length == 0){
            return "";
        }
        
        List<String> list = new ArrayList<>(Arrays.asList(A));
        // combine two strings with max overlapp untill there is only one string left;
        while (list.size() > 1){
            int maxOverLap = 0;
            int first = 0;
            int second = 1;
            // initial: didnot calculate overlap
            String minString = list.get(first) + list.get(second);
            // start from 0 as initial did not calcualte overlap;
            for (int i = 0; i < list.size() - 1; i++){
                for (int j = i + 1; j < list.size(); j++){
                    Pair pair = findMaxOverlap(list.get(i), list.get(j));
                    if (pair.len > maxOverLap){
                        maxOverLap = pair.len;
                        minString = pair.str;
                        first = i;
                        second = j;
                    }
                }
            }
            list.remove(second);
            list.remove(first);
            list.add(minString);
        }
        return list.get(0);
    }
    
    // find max overlap of s1 + s2 or s2 + s1; return pair
    private Pair findMaxOverlap(String s1, String s2){
        int max = 0;
        String str = "";
        
        for (int i = 0; i < s1.length(); i++){
            if (s2.startsWith(s1.substring(i))){
                max = s1.length() - i;
                str = s1.substring(0, i) + s2;
                break;
            }
        }
        
        for (int i = 0; i < s2.length(); i++){
            String sub = s2.substring(i);
            if (s1.startsWith(sub) && sub.length() > max){
                max = sub.length();
                str = s2.substring(0, i) + s1;
                break;
            }
        }
        
        return new Pair(str, max);
        
    }
    
}
