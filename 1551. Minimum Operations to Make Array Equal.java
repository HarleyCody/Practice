//My Solution: Equal distance array, (first item + last item) * number of item / 2
class Solution {
    public int minOperations(int n) {
        int mid = (n + 1) / 2;
        int first = 2;
        if(n % 2 == 0){
            ++mid;
            first = 1;
        }
        --mid;
        return mid * (first + mid - 1);
    }
}
