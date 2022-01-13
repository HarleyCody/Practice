/*Best Solution: bitmask to record the availability of section
Also there are 10 colums of seats, in this picture we can easily see there are only 3 sections. [2,5] , [4,7] , [6,9] (Show as in the yellow bars)
If seat #1 has been taken, none of these 3 sections will be affected.
But if #2 is taken, sections 1 is not available.
If seat #4 is taken, sections 1 and sections 2 both are not available.

So let's mark these three sections with numbers. 0 means available, 1 means taken
0 0 0 -------> all 3 sections are availble
1 0 0 -------> The left side section is taken

The seat #4 has the "skills" to "kill" 2 sections (left and middle).
So we give #4 a "weapon" --> 110
Because 000 | 110 = 110 (1 means taken)

As in the pictur's first row, seat #2 #3 #8 are taken.
#2 ---> 100 (if seat #2 is taken, then the left section is taken)
#3 ---> 100
#8 ---> 001(if seat #8 is taken, then the right section is taken)
*/
class Solution {
    private final int[] marks = new int[]{0, 0, 0b100, 0b100, 0b110, 0b110, 0b011, 0b011, 0b001, 0b001, 0}; //occupied
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] res :  reservedSeats) {
            if (res[1] == 1 || res[1] == 10)
                continue;
            int mask = map.getOrDefault(res[0], 0);
            mask |= marks[res[1]];
            map.put(res[0], mask);
        }
        
        int maxGroup = (n - map.size())*2;
        for (int mask : map.values()) {
            //right and left is taken
            int group = 2 -  (mask&0b001) - ((mask&0b100) >>2);
            if(group == 0 && ((mask&0b010)>>1 == 0)) {
                group = 1;
            }
            maxGroup += group;
        }
        
        return maxGroup;
    }
}

//My Solution: Calcualte seats and add to answer, coner case, not from 1, not end with n, middle is empty, asile must be in the middle
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Arrays.sort(reservedSeats, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int curRow = reservedSeats[0][0];
        int ans = (curRow - 1) << 1;
        if(reservedSeats[0][1] > 9){
            ans += 2;
        }else if(reservedSeats[0][1] > 5){
            ans += 1;
        }
        for(int i = 0; i < reservedSeats.length - 1; ++i){
            if(curRow < reservedSeats[i][0]){
                ans += (reservedSeats[i][0] - 1 - curRow) << 1;
                curRow = reservedSeats[i][0];
                if(reservedSeats[i][1] > 9){
                    ans += 2;
                }else if(reservedSeats[i][1] > 5){
                    ans += 1;
                }
            }
            if(reservedSeats[i][0] == reservedSeats[i + 1][0]){
                if(reservedSeats[i][1] < 2){
                    ans += reservedSeats[i + 1][1] > 9 ? 2 : reservedSeats[i + 1][1] > 5 ? 1 : 0;
                }else if(reservedSeats[i][1] < 4){
                    ans += reservedSeats[i + 1][1] > 7 ? 1 : 0;
                }else if(reservedSeats[i][1] < 6){
                    ans += reservedSeats[i + 1][1] > 9? 1 : 0;
                }
            }else{
                if(reservedSeats[i][1] < 2){
                    ans += 2;
                }else if(reservedSeats[i][1] < 6){
                    ans += 1;
                }
                if(reservedSeats[i + 1][1] > 9){
                    ans += 2;
                }else if(reservedSeats[i + 1][1] > 5){
                    ans += 1;
                }
                ans += (reservedSeats[i + 1][0] - reservedSeats[i][0] - 1) << 1;
                curRow = reservedSeats[i + 1][0];
            }
        }
        if(reservedSeats[reservedSeats.length - 1][1] < 2){
            ans += 2;
        }else if(reservedSeats[reservedSeats.length - 1][1] < 6){
            ans += 1;
        }
        ans += (n - curRow) << 1;
        return ans;
    }
}
