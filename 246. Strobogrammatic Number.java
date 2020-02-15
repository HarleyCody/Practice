// compare one by one;
// evalue 6 and 9 must be pair
// candiates can be pair or single when i = j;
class Solution {
    HashSet<Character> candidates = new HashSet(Arrays.asList('0', '1', '8'));
    public boolean isStrobogrammatic(String num) {
        char[] chs = num.toCharArray();
        int len = chs.length;
        int i = 0, j = len - 1;
        while(i <= j){
            if((chs[i] == '6' && chs[j] == '9') || (chs[i] == '9' && chs[j] == '6')){
                ++i;
                --j;
                continue;
            }
            if(!candidates.contains(chs[i])) return false;
            if(chs[i++] != chs[j--]) return false;
        }
        return true;
    }
}
