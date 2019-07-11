____________________________________________________Best Solution(On)__________________________________________________________
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // nums1 has more element
        if (nums1.length < nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int count = 0;
        // compare num1 and num2 as they are sorted, so moving pointer accordingly backward
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
            // same, save it to nums2[] reuse nums2
            if (nums1[i] == nums2[j]) {
                nums2[count++] = nums2[j];
                j++;
                i++;
            } else if (nums1[i] > nums2[j]) {//nums2 is smaller, to next nums[2]
                j++;
             
            } else {
                i++;
            }  
        }
        int[] res = new int[count];
        for(int i = 0; i < res.length;i++) {//nums2 is smaller, to next nums[2]
            res[i] = nums2[i];
        }
        return res;
    }
}
____________________________________________________My Solution(On)____________________________________________________________
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        // record freq of nums1
        HashMap<Integer, Integer> n1 = new HashMap();
        // record intersection of nums1 and nums2
        HashMap<Integer, Integer> n2 = new HashMap();
        for(int i = 0; i < nums1.length; ++i){
            n1.put(nums1[i], n1.getOrDefault(nums1[i], 0) + 1);
        }
        // calculate size of ans array
        int size = 0;
        for(int i = 0; i < nums2.length; ++i){
            // nums1 has more num2[i] than nums2[i] does
            if(n1.containsKey(nums2[i]) && n1.get(nums2[i]) > n2.getOrDefault(nums2[i], 0)){
                n2.put(nums2[i], n2.getOrDefault(nums2[i], 0) + 1);
                ++size;
            }
        }
        int[] ans = new int[size];
        int s = 0;
        for(int k : n2.keySet()){
            int times = n2.get(k);
            while(times-- > 0){
                ans[s++] = k;
            }
        }
        return ans;
    }
}
____________________________________________________My Solution(On2)_______________________________________________________________
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> ans = new ArrayList();
        // reduce duplicate
        boolean[] recorder = new boolean [nums2.length];
        for(int i = 0; i < nums1.length; ++i){
            for(int j = 0; j < nums2.length; ++j){
            // find first one then break to nums1 try to find next one in nums1
                if(nums1[i] == nums2[j] && !recorder[j]){
                    ans.add(nums1[i]);
                    recorder[j] = true;
                    break;
                }
            }
        }
        int[] res = new int[ans.size()];
        int s = 0;
        for(int i : ans){
            res[s++] = i;
        }
        return res;
    }
}
