___________________________________________________________Best Solution___________________________________________________________________
class Solution {
    // use consective array(presum) to record the number of people no older than age i;
    public int numFriendRequests(int[] ages) {
        int[] count = new int[121];
        for(int age : ages) {
            count[age]++;
        }
        int[] preSum = new int[121];
        for(int i = 1; i < 121; i++) {
            preSum[i] = count[i] + preSum[i-1];
        }
        int result = 0;
        for(int i = 15; i < 121; i++) {
            // get all number of all young people
            result += count[i] * (preSum[i] - preSum[( i/2 + 7)] - 1);
        }
        return result;
    }
}
___________________________________________________________My Solutoion___________________________________________________________________
class Solution {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int len = ages.length;
        int ans = 0;
        for(int a = 0; a < len; ++a){
            int b = a - 1;
            while(0 <= b && ages[b--] > ages[a] / 2 + 7 )ans++;
            b = a + 1;
            while(b < len && ages[a] > ages[a] / 2 + 7 && ages[a] == ages[b++]) ans++;
        }
        return ans;
    }
}
