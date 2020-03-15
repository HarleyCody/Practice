class Solution {
    // as two small stones may collide with one large stone to get answer
    // s1 + s2 - s3
    // so stones can be divided into two groups smaller group, large group
    // sum of stones are fixed, so larger the smaller group is, smaller the result is.
    // dp find largest group of stone
    public int lastStoneWeightII(int[] A) {
        // dp records the weight of a smaller group.
        // sumA will be 3000 at most, so the half is 1500 enough for recording max of smaller group
        boolean[] dp = new boolean[1501];
        dp[0] = true;
        int sumA = 0;
        
        // find max weight for smaller group;
        int max = 0;
        for (int a : A) {
            sumA += a;
            for (int i = Math.min(1500, sumA); i >= a; --i)
                dp[i] |= dp[i - a];
        }
        // find the largest group under sumA/2(max of smaller group)
        for (int i = sumA / 2; i > 0; --i)
            if (dp[i]) return sumA - i - i;
        return 0;
    }
}
