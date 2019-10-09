class Solution {
    // record best result in range "start,end" avoid repeated calculation
    // split to left and right, caculate minSum and MaxValue in left and right respectively.
    
    HashMap<String, int[]> recorder = new HashMap();
    public int mctFromLeafValues(int[] arr) {
        if(arr.length < 2) return 0;
        if(arr.length == 2) return arr[0] * arr[1];
        int len = arr.length;
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++){

            if(recorder.containsKey(0 + "," + i)){
                left = recorder.get(0 + "," + i);
            }else{
                left = findMax(arr, 0, i);
            }
            int r = i + 1;
            int e = len - 1;
            if(recorder.get(r + "," + e) != null){
                right = recorder.get(r + "," + e);
            }else{
                right = findMax(arr, r, e);
            }
            // sum in current level
            int sum = left[1] * right[1];
            // sub sum
            sum += left[0] + right[0];
            ans = Math.min(sum, ans);
        }
        return ans;
    }
    // return MinSum and MaxElement
    private int[] findMax(int[] arr, int s, int e){
        int size = e - s;
        // only one element, s could be larger than e;
        if(size <= 0){
            return new int[]{0, arr[e]};
        }
        // two element
        if(size == 1){
            recorder.put(s + "," + e, new int[]{arr[s] * arr[e], Math.max(arr[s], arr[e])});
            return new int[]{arr[s] * arr[e], Math.max(arr[s], arr[e])};
        }
        // multiple element
        int ans = Integer.MAX_VALUE;
        int max = 0;
        for(int i = s; i < e; i++){
            int[] left;
            int[] right;
            if(recorder.containsKey(s + "," + i)){
                left = recorder.get(s + "," + i);
            }else{
                left = findMax(arr, s, i);
            }
            int r = i + 1;
            if(recorder.get(r + "," + e) != null){
                right = recorder.get(r + "," + e);
            }else{
                right = findMax(arr, r, e);
            }
            // sum in current level
            int sum = left[1] * right[1];
            // sub Sum 
            sum += left[0] + right[0];
            if(sum < ans){
                max = Math.max(left[1], right[1]);
            }
            ans = Math.min(sum, ans);
        }
        // record best result for later use
        recorder.put(s + "," + e, new int[]{ans, max});
        return new int[]{ans, max};
    }
}
