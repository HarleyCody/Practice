________________________________________________________________My Solution_________________________________________________________________
// create copy to get 4 chars every time, copy the copy to buf
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        char[] src = new char[4];
        int idx = 0, ans = 0;
        do{
            ans += read4(src);
            for(char c : src){
                buf[idx++] = c;
                if(idx == n){
                    break;
                }
            }
        }while(ans != 0 && idx < n);
        return Math.min(ans, n);
    }
}
