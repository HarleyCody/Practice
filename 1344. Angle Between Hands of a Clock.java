class Solution {
    public double angleClock(int hour, int minutes) {
        //v1 angle 12 to h and v2 angle 12 to m
        //tar = v1 - v2 or 360 - tar;
        double m = 6 * minutes;
        double h = 30 * hour + 30.0 * minutes / 60;
        double ans = h - m < 0 ? (m - h > 180 ? 360 + h - m : m - h) :
        h - m > 180 ? 360 + m - h : h - m;
        
        return ans;
    }
}
