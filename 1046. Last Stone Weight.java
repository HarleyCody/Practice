_________________________________________________________Best Solution________________________________________________________
// calculate smash from right to left after sort, only need to sort when smash > 0, update stone[i] = smash, then sort
class Solution {
    public int lastStoneWeight(int[] stones) {
        Arrays.sort(stones);
        int i = stones.length - 2;
        int smash = 0;
        while(0 <= i){
            smash = stones[i + 1] - stones[i];
            // smash, only need to move to left one in next round
            if( smash > 0){
                stones[i] = smash;
                Arrays.sort(stones);
            }else{// no smash, move to two previous in order to smash i and i + 1;
                i--;
            }
            i--;
        }
        //i == -1 means i = 0 was smashed with residue, -2 means 0 was smashed totally.
        return i == -1 ? stones[0] : 0; 
    }
}
_________________________________________________________Priority Queue_______________________________________________________
class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> recorder = new PriorityQueue<>((x,y) -> y - x);
        for(int i : stones){
            recorder.offer(i);
        }
        int first = 0, second = 0, smash = 0;
        while(recorder.size() > 1){
            first = recorder.poll();
            if (recorder.size() != 0){
                second = recorder.poll();
            }
            smash = first - second; 
            if(smash != 0){
                recorder.offer(smash);
            }
            else if(recorder.size() == 0){
                recorder.offer(0);
            }
        }
        return recorder.size() == 0 ? 0:recorder.poll();
    }
}
