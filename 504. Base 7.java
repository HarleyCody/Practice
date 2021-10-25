//My Solution: Reminder in reverse order is answer, n = n / 7;
// ans is in Reverse order!
class Solution {
    public String convertToBase7(int num) {
        if(num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        int n = num;
        if(num < 0){
            n = -n;
        }
        int reminder = 0;
        while(n != 0){
            reminder = n % 7;
            n = n / 7;
            sb.append(reminder);
        }
        if(num < 0){
            sb.append('-');
        }
        return sb.reverse().toString();
    }
}
