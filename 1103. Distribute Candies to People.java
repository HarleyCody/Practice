________________________________________________________________________My Solution___________________________________________________________________________________
class Solution {
    // find round num, find stop peoson index
    // calcualt one by one
    // round to round is equal difference array;
    // people to people is equal difference array;
    public int[] distributeCandies(int candies, int num_people) {
        int rDiff = num_people * num_people;
        int rSum = (1 + num_people) * num_people / 2;
        int rNum = 0;
        while(candies > 0 && candies >= rSum){
            candies -= rSum;
            ++rNum;
            rSum += rDiff;
        }
        int cSum = 1 + rNum * num_people;
        int cNum = 0;
        while(candies > 0 && candies >= cSum){
            candies -= cSum;
            ++cNum;
            cSum += 1;
        }
        --rNum;
        int[] ans = new int[num_people];
        for(int i = 0; i < num_people; ++i){
            ans[i] = i + 1;
            if(i < cNum){
                ans[i] += (rNum + 1) * num_people;
                ans[i] = (i + 1 + ans[i]) * (rNum + 2) / 2;
            }else if(i > cNum){
                ans[i] += rNum * num_people;
                ans[i] = (i + 1 + ans[i]) * (rNum + 1) / 2;
            }else{
                ans[i] += rNum * num_people;
                ans[i] = (i + 1 + ans[i]) * (rNum + 1) / 2;
                ans[i] += candies;
            }
        }
        return ans;
    }
}
________________________________________________________________________Concise Solution___________________________________________________________________________________
// reduce candies one by one
class Solution {
    public int[] distributeCandies(int candies, int n) {
        int[] res = new int[n];
        for (int i = 0; candies > 0; ++i) {
            res[i % n] += Math.min(candies, i + 1);
            candies -= i + 1;
        }
        return res;
    }
}
