________________________________________________________Best Solution______________________________________________________________________
class Solution {
    // record head and return 
    public int lastRemaining(int n) {
        boolean left = true;
        int remaining = n;
        int step = 1;
        int head = 1;
        while (remaining > 1) {
            // if its from left to right, head will be replaced or remmain odd number of numbers, head will be replaced as well;
            if (left || remaining % 2 ==1) {
                head = head + step;
            }
            // how many number remains in next iteration;
            remaining = remaining / 2;
            step = step * 2;
            // turn 
            left = !left;
        }
        return head;
    }
}
