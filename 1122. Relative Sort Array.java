_______________________________________________________My Solution____________________________________________________________
class Solution {
    // get frequence of num, assign it to arr1 from 0;
    // after all num in arr2 is assigned in arr1, continuously  insert rest of num in recorder to arr1 
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] recorder = new int[1001];
        for(int i : arr1){
            ++recorder[i];
        }
        int start = 0;
        for(int i = 0; i < arr2.length; ++i){
            if(recorder[arr2[i]]-- > 0){
                arr1[start++] = arr2[i];
                --i;
            }
        }
        for(int i = 0; i < 1001; ++i){
            if(recorder[i]-- > 0){
                arr1[start++] = i;
                --i;
            }
        }
        return arr1;
    }
}
