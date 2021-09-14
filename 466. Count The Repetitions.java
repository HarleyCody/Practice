// my post https://leetcode.com/problems/count-the-repetitions/discuss/777733/Java-100Time-55.4-Space-only-Array-used.-In-detail-explaination.
________________________________________________________________________________ My Solution_____________________________________________________________________________
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length(), len2 = s2.length();
		// for detecting cycle
        boolean[] endAt = new boolean[len1];
        char[] chs1 = s1.toCharArray(), chs2 = s2.toCharArray();
		// how many s1 we use and how many s2 we collect when we endAt specific
        int[] nums1 = new int[len1], nums2 = new int[len1];
        
        int tltS1Len = len1 * n1;
        
        int startIdx = s1.indexOf(chs2[0]);
        if(startIdx == -1)return 0;
        
        int s2n = 0, s1n = 0, curIdx = 0, prevIdx = -1;
        while(s1n * len1 + curIdx < tltS1Len){
            for(char c : chs2){
                curIdx = s1.indexOf(c, prevIdx + 1);
                
                if(curIdx == -1){
                    if(prevIdx == -1)return 0;
                    ++s1n;
                    curIdx = s1.indexOf(c);
                }
                prevIdx = curIdx;
            }
            // cycle found
            if(endAt[curIdx])break;
            
            prevIdx = curIdx;
            ++s2n;
            endAt[curIdx] = true;
            nums1[curIdx] = s1n;
            nums2[curIdx] = s2n; 
        }
		
        int cycleLen = (s1n - nums1[curIdx]) * len1;
        if(cycleLen == 0) return s2n / n2;
        
        int nS2 = (tltS1Len - startIdx - 1) / cycleLen * s2n;
        int remain = (tltS1Len - startIdx - 1) % cycleLen;
        
        int extraNum = 0;
        for(int i = 0; i < len1; ++i){
            if(!endAt[i]) continue;
            
            if(remain >= i + nums1[i] * len1) extraNum = nums2[i];
        }
        
        return (nS2 + extraNum) / n2;
    }
}
