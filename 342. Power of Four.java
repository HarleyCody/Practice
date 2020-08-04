_____________________________________________________________________________Best Solution_____________________________________________________________________________
class Solution {
    // move bit by bit. move 1 bit == divide 2 so move twice in a loop
    // times of 4 in the end will be inclusive to 1
    public boolean isPowerOfFour(int num) {
        int d = 0;
        while(num > 1){
            d = num & 1;
            if(d == 1){
                return false;
            }
            num >>= 1;
            
            d = num & 1;
            if(d == 1){
                return false;
            }
            num >>= 1;
        }
        return num == 1;
    }
}
