_______________________________________________________________________________Best Solution____________________________________________________________________________________
class Solution {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length, new int[nums.length]);
    }
    private int mergeSort(int[] nums, int lo, int hi, int[] temp) {
        if (hi - lo < 2) return 0;
        int mid = (lo + hi) >> 1;
        int cur = mergeSort(nums, lo, mid, temp) + mergeSort(nums, mid, hi, temp);
        //find first element > nums[r] * 2 accumulate num
        for (int irp = lo, r = mid; irp < mid && r < hi; ++r) {
            irp = nextGT(nums, irp, mid, (long) nums[r] << 1);
            cur += mid - irp;
        }
        System.arraycopy(nums, lo, temp, lo, hi - lo);
        // merge sort low to hi (merge sort temp[lo ~ mid] and temp[mid ~ hi])
        for (int l = lo, r = mid, i = lo; l != mid; ++i, ++l) {
            while (r != hi && temp[r] < temp[l]) nums[i++] = temp[r++];
            nums[i] = temp[l];
        }
        return cur;
    }
    
    // find next greater than targer
    private int nextGT(int[] nums, int lo, int mid, long target) {
        while (lo < mid && nums[lo] <= target) ++lo;
        return lo;
    }
}
__________________________________________________________________________________Bit____________________________________________________________________________________
public class Solution {
    //https://leetcode.com/problems/reverse-pairs/discuss/97268/General-principles-behind-problems-similar-to-%22Reverse-Pairs%22
    private int search(int[] bit, int i) {
        int sum = 0;

        while (i < bit.length) {
            sum += bit[i];
            i += i & -i;
        }

        return sum;
    }

    private void insert(int[] bit, int i) {
        while (i > 0) {
            bit[i] += 1;
            i -= i & -i;
        }
    }
    
    public int reversePairs(int[] nums) {
        int res = 0;
        int[] copy = Arrays.copyOf(nums, nums.length);
        int[] bit = new int[copy.length + 1];

        Arrays.sort(copy);

        for (int ele : nums) {
            res += search(bit, index(copy, 2L * ele + 1));
            insert(bit, index(copy, ele));
        }
        return res;
    }

    private int index(int[] arr, long val) {
        int l = 0, r = arr.length - 1, m = 0;

        while (l <= r) {
            m = l + ((r - l) >> 1);

            if (arr[m] >= val) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return l + 1;
    }
}
______________________________________________________________________________________BST____________________________________________________________________________________
public class Solution {
    //merge sort with quicksort
    public int reversePairs(int[] nums) {
        return reversePairsSub(nums, 0, nums.length - 1);
    }

    private int reversePairsSub(int[] nums, int l, int r) {
        if (l >= r) return 0;

        int m = l + ((r - l) >> 1);
        int res = reversePairsSub(nums, l, m) + reversePairsSub(nums, m + 1, r);

        int i = l, j = m + 1, k = 0, p = m + 1;
        int[] merge = new int[r - l + 1];

        while (i <= m) {
            while (p <= r && nums[i] > 2L * nums[p]) p++;
            res += p - (m + 1);

            while (j <= r && nums[i] >= nums[j]) merge[k++] = nums[j++];
            merge[k++] = nums[i++];
        }

        while (j <= r) merge[k++] = nums[j++];

        System.arraycopy(merge, 0, nums, l, merge.length);

        return res;
    }
}
__________________________________________________________________________________Merge Sort____________________________________________________________________________________
public class Solution {
    // sort and compare
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);
    }
    private int mergeSort(int[] nums, int s, int e){
        if(s>=e) return 0; 
        int mid = s + (e-s)/2; 
        int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid+1, e); 
        for(int i = s, j = mid+1; i<=mid; i++){
            while(j<=e && nums[i]/2.0 > nums[j]) j++; 
            cnt += j-(mid+1); 
        }
        Arrays.sort(nums, s, e+1); 
        return cnt; 
    }
}
