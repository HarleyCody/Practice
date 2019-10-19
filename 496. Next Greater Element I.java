_______________________________________________________Best Solution__________________________________________________________
class Solution {
    // hash value of nums2[i] with next element greater than it.
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        int[] res = new int[l1];
        if (l1 > l2 || l1 == 0 || l2 == 0){
            return res;
        }
        int maxNum = nums2[0];
        for(int k = 1; k < l2; k++){
            if (nums2[k] > maxNum){
                maxNum = nums2[k];
            }
        }
        // nums2[i] is index in hashNums, value of hashNum is value of next greater number
        int[] hashNums = new int[maxNum+1];
        int i, j;
        
        for(i = 0; i < l2 - 1; i++){
            for(j = i + 1; j < l2; j++){
                if(nums2[j] > nums2[i]){
                    hashNums[nums2[i]] = nums2[j];
                    break;
                }
            }
            // not found greater num
            if (j == l2){
                hashNums[nums2[i]] = -1;
            }
        }
        // last element is -1;
        hashNums[nums2[l2 - 1]] = -1;
        for(i=0; i<l1; i++){
            res[i] = hashNums[nums1[i]];
        }
        return res;
    }
}

_______________________________________________________My Solution____________________________________________________________
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Arrays.fill(ans, -1);
        for(int i = 0; i < nums1.length; i++){
            boolean flag = false;
            for(int j = 0; j < nums2.length; j++){
                if(!flag && nums2[j] == nums1[i]){
                    flag = true;
                }
                if(flag && nums2[j] > nums1[i]){
                    ans[i] = nums2[j];
                    break;
                }
            }
        }
        return ans;
    }
}
