    
// choose cur num by calculate the idx of it
// formula: total / len => how many perms can start with number i (0 < i < len);
// idx = k / per, choose number at idx to append, narrow down the range for next search;
// recursivly search untill all numbers are used up;
class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> can = new ArrayList();
        int total = 1;
        for(int i = 1; i <= n; ++i){
            can.add(i);
            total *= i;
        }
        // perm idx from 0 => -1 get idx of target per
        build(can, k - 1, total, sb);
        return sb.toString();
    }
    private void build(List<Integer> can, int k, int total, StringBuilder ans){
        if(can.isEmpty()){
            return;
        }
        int len = can.size();
        int per = total / len;
        int idx = k / per;
        ans.append(can.get(idx));
        can.remove(idx);
        build(can, k - idx * per, total / len, ans);
    }
}
