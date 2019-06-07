public class Solution {
    public boolean validUtf8(int[] data) {
        int count = 0;
        for (int c : data) {
            
            // Decide how many bytes it wants to present. N bytes for example.
            if (count == 0) {
                if ((c >> 5) == 0b110) count = 1;// 2 bytes
                else if ((c >> 4) == 0b1110) count = 2;//3 bytes
                else if ((c >> 3) == 0b11110) count = 3; // 4 bytes
                else if ((c >> 7) != 0) return false; // 1 byte count == 0 true then.
            }
            
            // Decide the begining of next bytes is 10. UTF of N -> 0 bytes should start with 10. count will be 0 if valid.
            else {
                if ((c >> 6) != 0b10) return false; 
                count--;
            }
        }
        return count == 0;
    }
}
