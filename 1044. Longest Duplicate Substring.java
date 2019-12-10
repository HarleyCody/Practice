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
