class Solution {
    public List<Integer> partitionLabels(String S) {
        // max record range of current unique string;
        // bre record where last string ends
        int max = 0, len = S.length(), bre = 0;
        List<Integer> ans = new ArrayList();
        for(int i = 0; i < len; ++i){
            // index of current char appears at rest strings.
            int nextIndex = S.indexOf(S.charAt(i), i + 1);
            if(nextIndex >= 0)//find a char that still appears later, extend largest range;
                max = nextIndex > max? nextIndex : max;
            else if (i >= max){
                // find a char that does not appear later and out of largest range, valid!
                max = 0;
                // size = i + 1 - size of last unique string.
                ans.add(i + 1 - bre);
                bre = i + 1;
            }
        }
        return ans;
    }
}
