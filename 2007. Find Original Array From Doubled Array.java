//Best Solution: Odd or even does not matter, only check if c can find 2 * c with greater frequency
//Check 0 and len in the beginning to avoid computation
class Solution {
    public int[] findOriginalArray(int[] changed) {
        int N = changed.length;
        if(N % 2 == 1) return new int[0];
        int max = 0;
        for(int i : changed){
            max = Math.max(max, i);
        }
        int[] arr = new int[max + 1];
        for(int i : changed) arr[i] ++;
        if(arr[0] % 2 == 1) return new int[0];
        int[] res = new int[N / 2];
        int idx = 0;
        for(int num = 1; num <= max; num ++){
            for(int ct = 0; ct < arr[num]; ct ++){
                if(2 * num > max || arr[2 * num] == 0) return new int[0];
                res[idx ++] = num;
                arr[2 * num] --;
                if(idx == N / 2) return res;
            }
        }
        return res;
    }
}
//My Solution: eliminate odd first then check even, only save c in ans, use while to save it
class Solution {
    public int[] findOriginalArray(int[] changed) {
        int len = changed.length;
        int[] recorder = new int[200001];
        for(int c : changed){
            ++recorder[c];
        }
        int[] ans = new int[len / 2];
        int ansIdx = 0;
        for(int c : changed){
            if(recorder[c] > 0 && c % 2 == 1){
                recorder[c * 2] -= recorder[c];
                if(recorder[c * 2] < 0) return new int[0];
                while(recorder[c]-- > 0){
                    ans[ansIdx++] = c;
                }
            }
        }
        
        for(int i = 2; i < 100001; ++i){
            if(i % 2 == 1 || recorder[i] == 0) continue;
            recorder[i * 2] -= recorder[i];
            if(recorder[i * 2] < 0) return new int[0];
            while(recorder[i]-- > 0){
                ans[ansIdx++] = i;
            }
        }
        
        return recorder[0] % 2 == 0 ? ans : new int[0];
    }
}
