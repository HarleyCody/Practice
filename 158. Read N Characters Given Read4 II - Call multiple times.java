/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf); 
 */
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    private int buffPtr = 0; // 
    private int buffCnt = 0; // length of buff as maxmial as it can be. 1234567 return 4 then return 3.
    private char[] buff = new char[4]; // record read buf
    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) { // last buff has not been totally read yet
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (ptr < n && buffPtr < buffCnt) { // only write 4 char to buff in maximal.
                // ptr >= n required buffer has been read; buffPtr >= buffCnt read4 buffer has been read, but need more data from buffer 
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr >= buffCnt) buffPtr = 0; // buff has been read, should get next buffer as read4 can only read 4 char from buffer, if n > 4, then bufftr should be able to be larger than cnt
        }
        return ptr;
    }
}
