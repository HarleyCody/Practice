______________________________________________________Best Solution____________________________________________________________
class Solution {
    private class BSTNode {
        int low, high;
        BSTNode left, right;

        BSTNode(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    public String longestDupSubstring(String S) {
        char[] s = S.toCharArray();
        int len = s.length;
        s = Arrays.copyOf(s, len + 1);
        s[len] = '*';
        // dp : adjascent index having same start.(by swapping in parition)
        // record next postion(j) of char that need to be checked, if next char is not equal, then sawp adjscence with next matched in char j.
        int[] dp = new int[len], chs = new int[26];
        for (int i = 0; i < len; i++) {
            dp[i] = i;
            chs[s[i] - 'a'] += 1;
        }

        for (int i = 0; i < 26; i++) {
            if (chs[i] == len) {
                return S.substring(1);
            }
        }

        BSTNode[] trees = new BSTNode[len];
        int[] r = helper(s, dp, 0, len, trees);
        for(BSTNode bst : trees){
            if(bst != null){
                System.out.println("low is " + bst.low + " high is " + bst.high);
            }
            else {
                System.out.println("bst is null");
            }
        }
        return String.valueOf(s, r[0], r[1]);
    }

    private int[] helper(char[] s, int[] dp, int l, int r, BSTNode[] trees) {
        int pos = 0, max = -1;
        while (l < r) {
            // l can only be updated by nl, so its static, 
            // in the partition, it will check from tail to l to find char that eaquals to l.
            int nl = partitionAndMoveForward(s, dp, l, r);
            if (nl - l == 1) {
                if (0 > max) {
                    pos = dp[l] - 1;
                    max = 0;
                }
            } else if (nl - l == 2) {
                // count = 1 cuse tail is same
                int count = 1, a = dp[l], b = dp[l + 1];
                if (s[a] == s[b]) {
                    // l which is potential start, and l + 1 which is another potential duplicate start
                    int low = Math.min(a, b), high = Math.max(a, b);
                    // build tree by gap between two starts.(high - low)
                    // low - 1 cuz to get middle point.(expand left and right to get length);
                    // build tree
                    count = searchAndUpdate(s, low - 1, high - low, trees);
                }
                // longer, update.
                if (count > max) {
                    pos = dp[l] - 1;
                    max = count;
                }
            } else {
                int[] m = helper(s, dp, l, nl, trees);
                if (m[1] + 1 > max) {
                    // -1, +1 cuz only start compare from sec char, first one should be added 
                    pos = m[0] - 1;
                    max = m[1] + 1;
                }
            }
            l = nl;
        }
        return new int[]{pos, max};
    }
    
    // search for the longest gap between two same start
    // return the length
    private int searchAndUpdate(char[] s, int v, int gap, BSTNode[] trees) {
        BSTNode p = trees[gap];
        while (p != null) {
            //same gap same start, checked before
            if (v < p.high && v >= p.low) {
                return p.high - v;
            }
            // same gap, different start, build tree with start index.
            if (v >= p.high && p.right != null) {
                p = p.right;
                continue;
            } else if (v < p.low && p.left != null) {
                p = p.left;
                continue;
            }
            break;
        }
        
        // broaden the length.
        int len = s.length, a = v, b = v + gap;
        while (b < len && s[a] == s[b]) {
            ++a;
            ++b;
        }
        int high = a;
        a = v;
        b = v + gap;
        while (a >= 0 && s[a] == s[b]) {
            --a;
            --b;
        }
        int low = a + 1;

        if (p == null) {
            trees[gap] = new BSTNode(low, high);
        } else if (v >= p.high) {
            p.right = new BSTNode(low, high);
        } else {
            p.left = new BSTNode(low, high);
        }
        // length of duplicate strings;
        return high - v;
    }
    // dp will match the pattern of S. eg b a n a n a, it will be 1 3 5 7 5 3
    private int partitionAndMoveForward(char[] s, int[] dp, int l, int r) {
        char target = s[dp[l]];
        while (l < r) {
            // search first not equal to tar
            if (s[dp[l]] == target) {
                ++dp[l];
                ++l;
                continue;
            }
            // search point eaqual to tar from tail to head;
            if (s[dp[r - 1]] != target) {
                --r;
                continue;
            }
            
            // dp[r - 1] first equal to tar
            int tmp = dp[l];
            dp[l] = dp[r - 1] + 1;
            dp[r - 1] = tmp;
            ++l;
            --r;
        }
        return l;
    }
}
_____________________________________________________Rabin-Karp Solution_______________________________________________________
/* longestDupSubstring: set new length to search based on the result of func2*/ 
/* search: calculate the hashvalue of string with length L
find if there is valid start(>=0) with finding the duplicate L length string
*/
class Solution {
  /*
  Rabin-Karp with polynomial rolling hash.
      Search a substring of given length
      that occurs at least 2 times.
      Return start position if the substring exits and -1 otherwise.
      */
    // serach mainly check if there is duplicate string by hashCode
    // hashcode = (hashCode * a + nums[i]) % module PS: a = 26
    // return start of string
  public int search(int L, int a, long modulus, int n, int[] nums) {
    // compute the hash of string S[:L]
    long h = 0;
      // + nums[i] is in case of hashcode is equal but string is not same
      // so when hashcode is same, it could be say that string is same.
    for(int i = 0; i < L; ++i) h = (h * a + nums[i]) % modulus;

    // already seen hashes of strings of length L
    HashSet<Long> seen = new HashSet();
    seen.add(h);
    // const value to be used often : a**L % modulus
    long aL = 1;
    for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;
      
      // check every substring with length L. 
    for(int start = 1; start < n - L + 1; ++start) {
      // compute rolling hash in O(1) time
      h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
      h = (h + nums[start + L - 1]) % modulus;
      if (seen.contains(h)) return start;
      seen.add(h);
    }
    return -1;
  }
    
// narrow down the value of length of duplicate string
  public String longestDupSubstring(String S) {
    int n = S.length();
    // convert string to array of integers
    // to implement constant time slice
    int[] nums = new int[n];
    for(int i = 0; i < n; ++i) nums[i] = (int)S.charAt(i) - (int)'a';
    // base value for the rolling hash function
    int a = 26;
    // modulus value for the rolling hash function to avoid overflow
    long modulus = (long)Math.pow(2, 32);

    // binary search, L = repeating string length
    int left = 1, right = n;
    int L;
    while (left <= right) {
      L = left + (right - left) / 2;
      if (search(L, a, modulus, n, nums) != -1) left = L + 1;
      else right = L - 1;
    }

    int start = search(left - 1, a, modulus, n, nums);
    return S.substring(start, start + left - 1);
  }
}
