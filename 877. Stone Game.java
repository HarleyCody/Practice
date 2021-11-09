//Best Solution: If I am the first player, before I make the choice, I can calculate the sum of all elements the index of which is even as well as the sum of all elements the index of which is odd, if sum(odd) > sum(even), everytime I just pick up odd elements, vice versa.
//By this strategy must win. This strategy works because the length of piles array is even.
class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}

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
