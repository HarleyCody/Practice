________________________________________________________________________My Solution______________________________________________________
// get max of three, try to move pointers to meet max
// can meet add, otherwise, all move to next;
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int p1 = 0, p2 = 0, p3 = 0;
        int l1 = arr1.length, l2 = arr2.length, l3 = arr3.length;
        
        List<Integer> ans = new ArrayList(); 
        while(p1 < l1 && p2 < l2 && p3 < l3){
            int max = Math.max(Math.max(arr1[p1], arr2[p2]),arr3[p3]);
            while(p1 < l1  && arr1[p1] < max){
                ++p1;
            }
            while(p2 < l2 && arr2[p2] < max){
                ++p2;
            }
            while(p3 < l3 && arr3[p3] < max){
                ++p3;
            }
            if(p1 < l1 && p2 < l2 && p3 < l3 &&
              arr1[p1] == arr2[p2] && arr2[p2] == arr3[p3]){
                ans.add(arr1[p1]);
                ++p1; ++p2; ++p3;
                continue;
            }
        }
        return ans;
    }
}
