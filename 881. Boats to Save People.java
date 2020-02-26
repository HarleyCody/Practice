________________________________________________________My Solution(Best Solution)______________________________________________________
class Solution {
    // two pointers, sort, choose left and choose right;
    // if left + right > limit, only pick right;
    public int numRescueBoats(int[] people, int limit) {
        int len = people.length;
        int numBoat = 0;
        Arrays.sort(people);
        int i = 0, j = len -1;
        // set to valid right;
        while(j >= 0 && people[j] >= limit){
                ++numBoat;
                --j;
        }
        // choose left and choose right
        while(i <= j){       
            if(people[i] + people[j] > limit){
                ++numBoat;
                --j;
            }else{
                ++i;
                --j;
                ++numBoat;
            }
        }
        return numBoat;
    }
}
