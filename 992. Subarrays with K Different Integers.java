_____________________________________________________Best Solution___________________________________________________________
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        int result = 0;
        if (A == null || A.length == 0) return result;

        int[] counts = new int[A.length+1];
        int left = 0, countDist = 0, combNum = 1;
        
        for (int right = 0; right < A.length; right++) {
            // num of distinct num
            if (counts[A[right]]++ == 0) countDist++;
            while (countDist > K) {
                if (--counts[A[left]] == 0) countDist--;
                left++;
                // calculate comb from 1 after while breaks
                combNum = 1;
            }
            if (countDist == K) {
                // find left boarder for cur i; > 1 means has contain at least one left;
                while (counts[A[left]] > 1) {
                    ++combNum;
                    --counts[A[left]];
                    left++;
                }
                result += combNum;
            }
        }
        return result;
    }
}
_____________________________________________________Basic Solution___________________________________________________________
class Solution {
    //(AtMost(K) - AtMost(K-1) == Excat K
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostK(A, K) - atMostK(A, K - 1);
    }
    private int atMostK(int[] A, int K) {
        int i = 0, res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int j = 0; j < A.length; ++j) {
            int num = count.getOrDefault(A[j], 0);
            if (num == 0) --K;
            count.put(A[j], num + 1);
            while (K < 0) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0) ++K;
                ++i;
            }
            res += j - i + 1;
        }
        return res;
    }
}
