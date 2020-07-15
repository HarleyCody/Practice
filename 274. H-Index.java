class Solution {
    // binary search the thread hold,
    // l could be thresh hold or l + 1 is threshhold so check rlt when it returns
    public int hIndex(int[] citations) {
        if(citations.length == 0){
            return 0;
        }
        int l = 0, r = citations.length;
        while(l < r){
            int mid = (l + r) / 2;
            if(check(mid, citations)){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        return check(l, citations) ? l : l - 1;
    }
    
    private boolean check(int h, int[] cita){
        
        int num = 0;
        for(int i = 0; i < cita.length; ++i){
            if(cita[i] >= h){
                ++num;
            }
        }
        return num >= h;
    }
}
