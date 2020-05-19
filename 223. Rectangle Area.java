__________________________________________________________My Solution___________________________________________________________________
class Solution {
    // two rect - overlap
    // overlap len: min right - max left; int might over flow here
    // overlap wid: min high - max low; int migh over flow here
    // swap to standardize the two rectangles. => A represents most left when calcualte len; F represents lowest point when calculate wid; 
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int[] data = new int[8];
        data[0] = A; data[1] = B; data[2] = C; data[3] = D;
        data[4] = E; data[5] = F; data[6] = G; data[7] = H;
        
        int rec1Area = (C - A) * (D - B), rec2Area = (G - E) * (H - F);
        int overlap = 0;
        
        if(E < A){
            swapRecs(data);
        }
        
        int len =  Math.min(data[6], data[2]) - data[4];
        
        // incase of overflow
        if(C > E){
            overlap = len <= 0 ? 0 : len;
        }
        if(data[1] < data[5]){
            swapRecs(data);
        }
        int wid = Math.min(data[3], data[7]) - data[1];
        overlap *=  (wid <= 0 ? 0 : wid);
        return rec1Area + rec2Area - overlap;
    }
    
    private void swapRecs(int[] data){
        int i = 0;
        while(i < 4){
            int temp = data[i + 4];
            data[i + 4] = data[i];
            data[i++] = temp;
        }
    }
}
