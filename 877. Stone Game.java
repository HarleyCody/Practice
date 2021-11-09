//My Solution: Memic while process, count and compare
class Solution {
    public boolean stoneGame(int[] piles) {
        int head = 0;
        int tail = piles.length - 1;
        int alice = 0;
        int bob = 0;
        boolean isAlice = true;
        while(head <= tail){
            if(isAlice){
                alice += piles[head] >= piles[tail] ? piles[head++] : piles[tail--];
            }else{
                bob += piles[head] >= piles[tail] ? piles[head++] : piles[tail--];
            }
        }
        return alice > bob;
    }
}
