___________________________________________________________________________________________Best Solution____________________________________________________________________________
//find minimum x such that (T+1)^x >= N
// T = round for test
// x = minimal number of pigs
// N = number of buckets
class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int base = minutesToTest / minutesToDie + 1;
        
        int l = 0, r = buckets;
        
        while(l < r){
            int m = (l + r) / 2;
            boolean can = Math.pow(base, m) >= buckets;
            if(can){
                r = m;
            }else{
                l = m + 1;
            }
        }
        return r;
    }
}
