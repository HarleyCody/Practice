//Best Solution: Sort dimension so the largest side will be height
//              sort cuboids from large to small by bottom area
//              stack up the cubiods by brute force with dp
class Solution {
    public int maxHeight(int[][] cuboids) {
        int len = cuboids.length;
        int[] dp = new int[len];
        
        for(int[] cuboid : cuboids){
            Arrays.sort(cuboid);
        }
        Arrays.sort(cuboids, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] != b[0]) return b[0] - a[0];
                if(a[1] != b[1]) return b[1] - a[1];
                
                return b[2] - a[2];
            }
        });
        int ans = 0;
        for(int i = 0; i < len; ++i){
            dp[i] = cuboids[i][2];
            for(int j = 0; j < i; ++j){
                if(cuboids[j][0] >= cuboids[i][0] && cuboids[j][1] >= cuboids[i][1] && cuboids[j][2] >= cuboids[i][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
                }
            }
             
            ans = Math.max(dp[i], ans);
        }
        
        return ans;
    }
}
