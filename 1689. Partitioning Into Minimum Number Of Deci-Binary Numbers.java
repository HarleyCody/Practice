//Check the number instead of character
____________________________________________________________________________Best Solution____________________________________________________________________________
class Solution {
    public int minPartitions(String n) {
        for(int i = 9; i > 0; i--) {
            if(n.contains(String.valueOf(i))) return i;
        }
        return 0;
    }
}
//Check the character and find the max;
_____________________________________________________________________________My Solution_____________________________________________________________________________
class Solution {
    public int minPartitions(String n) {
        char[] chs = n.toCharArray();
        int ans = '0';
        for(char c : chs){
            ans = Math.max(ans, c);
        }
        if(ans - '0' < 2) return 1;
        return ans - '0';
    }
}
