_______________________________________________________________________________________Best Solution______________________________________________________________________
class Solution {
    // All number construct from 01689 - # of mirror number(after rotate they are still same)
    // construct string and count #.
    static char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    public int confusingNumberII(int N) {
        String num = Integer.toString(N);
        int res = findTotal(num);
        for (int len = 1; len <= num.length(); len++) {
            char[] curr = new char[len];
            res -= dfs(curr, num, 0, len - 1);
        }
        return res;
    }
    // count the # of numbers from "01689" that is less than N
    private int findTotal(String s) {
        if (s.length() == 0) return 1;
        char first = s.charAt(0);
        int res = count(first) * (int) (Math.pow(5, s.length() - 1));
        if (first == '0' || first == '1' || first == '6' || first == '8' || first == '9') {
            res += findTotal(s.substring(1));
        }
        return res;
    }
    // count the # of Strobogrammatic numbers
    private int dfs(char[] curr, String num, int left, int right) {
        int res = 0;
        if (left > right) {
            String s = new String(curr);
            if (s.length() < num.length() || s.compareTo(num) <= 0) {
                ++res;
            }
        } else {
            for (char[] p : pairs) {
                curr[left] = p[0];
                curr[right] = p[1];
                if ((curr[0] == '0' && curr.length > 1) || (left == right && p[0] != p[1])) continue;
                res += dfs(curr, num, left + 1, right - 1);
            }
        }
        return res;
    }
    // a helper function that counts the # of chars in "01689" less than given 'c'
    private int count(Character c) {
        int res = 0;
        for (char[] p : pairs) {
            if (p[0] < c) res += 1;
        }
        return res;
    }
}
__________________________________________________________________________________Naive Solution______________________________________________________________________
class Solution {
    //construct from map one by one backtracking, only construct with candidate digits
    HashMap<Integer, Integer> map = new HashMap();
    int ans = 0, end;
    int[]can = new int[]{0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
    public int confusingNumberII(int N) {
        end = N;
        helper(0);
        return ans;
    }
    
    private void helper(long n){
        if(isValid(n)){
            ++ans;
        }
        
        for(int i : can){
            if(i == -1){
                continue;
            }
            long next = n * 10 + i;
            if(next > end || next == 0){
                continue;
            }
            helper(next);
        }
    }
    
    private boolean isValid(long n){
        long rlt = 0;
        long og = n;
        while(n > 0){
            int d = (int)n % 10;
            rlt = rlt * 10 + can[d];
            n /= 10;
        }
        
        return rlt != og;
    }
}
