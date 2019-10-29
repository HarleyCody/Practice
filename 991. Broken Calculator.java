class Solution {
    public int brokenCalc(int X, int Y) {
        if(X == Y) return 0;
        if(X > Y) return X - Y;
        // only can reach Y by times 2 and subtract 1, so the second last recursion should be one of them(Y/2) or(Y + 1)
        return (Y % 2 == 0 ? brokenCalc(X, Y / 2) : brokenCalc(X, Y + 1)) + 1;
    }
}
