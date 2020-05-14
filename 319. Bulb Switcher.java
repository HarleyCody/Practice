______________________________________________________________Best Solution________________________________________________________________
class Solution {
    // only number switched odd number times keeps on
    // factor comes in pairs so only sqaure number comes with odd number cause one of pair has two same values
    // switched time == number of factor, only when n is squre number it would be a odd(on status)
    // the largest root would be sqrt(n), so number with root factor 1 ~ sqrt(n) can be on, ie sqrt(n) number
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}
