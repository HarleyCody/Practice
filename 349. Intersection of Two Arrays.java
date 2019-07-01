___________________________________________________My Solution_________________________________________________________________
class Solution {
    // O(nlogn)
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> recorder = new ArrayList();
        // ascending array;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // compare with two arrays, smaller one goes to next element. 
        // same one stored in recorder array
        // duplicated one skip by ++n1 and ++n2;
        int n1 = 0, n2 = 0;
        while(n1 < nums1.length && n2 < nums2.length){
            if(nums1[n1] > nums2[n2]){
                ++n2;
            }else if(nums1[n1] < nums2[n2]){
                ++n1;
            }else if(recorder.size() == 0 || nums1[n1] != recorder.get(ans.size() - 1)){
                recorder.add(nums1[n1]);
                ++n1;
                ++n2;
            }else{
                ++n1;
                ++n2;
            }
        }
        // fastest way to get list to array;
        int[] rlt = new int[ans.size()];
        for( int i = 0; i < ans.size(); ++i){
            rlt[i] = ans.get(i);
        }
        return rlt;
    }
}
___________________________________________________General Solution____________________________________________________________
class Solution{
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> ans = new ArrayList();
        List<Integer> recorder = new ArrayList();
        for(int i = 0; i < nums1.length; ++i){
            recorder.add(nums1[i]);
        }
        for(int i = 0; i < nums2.length; ++i){
            if(recorder.contains(nums2[i]) && !ans.contains(nums2[i])){
                ans.add(nums2[i]);
            }
        }
        return ans.stream().mapToInt(k->k).toArray();
    }
}
