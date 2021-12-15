//Best Solution: Record and calculate the top 5
class Solution {
    public int[][] highFive(int[][] items) {
        Arrays.sort(items, (a, b) -> b[1] - a[1]);
        int[] score = new int[1001];
        int[] count = new int[1001];
        
        int num = 0;
        for(int[] item : items){
            int id = item[0];
            int point = item[1];
            if(count[id]++ < 5){
                score[id] += point;
            }
            if(count[id] == 5){
                ++num;
            }
        }
        int[][] ans = new int[num][2];
        int idx = 0;
        
        for(int i = 0; i < count.lengtha; ++i){
            if(count[i] < 5) continue;
            ans[idx][0] = i;
            ans[idx++][1] = score[i] / 5;
        }
        return ans;
    }
}
