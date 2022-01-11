//My Solution: Insert two char together as much as possible, sort char with freq, use up largest freq as much as possible
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        int[][] freq = new int[3][2];
        freq[0][0] = a;
        freq[0][1] = 97;
        freq[1][0] = b;
        freq[1][1] = 98;
        freq[2][0] = c;
        freq[2][1] = 99;
        Arrays.sort(freq, (x, y) -> y[0] - x[0]);
        StringBuilder sb = new StringBuilder();
        while(freq[0][0] > 0 && (freq[1][0] > 0 || freq[2][0] > 0)){
            if(freq[0][0] > freq[1][0] + freq[2][0] + 2){
                sb.append((char)freq[0][1]);
                --freq[0][0];
            }
            sb.append((char)freq[0][1]);
            --freq[0][0];
            if(freq[1][0] > freq[2][0]){
                sb.append((char)freq[1][1]);
                --freq[1][0];
            }else{
                sb.append((char)freq[2][1]);
                --freq[2][0];
            }
        }
        if(freq[0][0] > 0){
            sb.append((char)freq[0][1]);
            --freq[0][0];
        }
        if(freq[0][0] > 0){
            sb.append((char)freq[0][1]);
            --freq[0][0];
        }
        while(freq[1][0] > 0 && freq[2][0] > 0){
            if(freq[1][0] >= freq[2][0] + 2){
                sb.append((char)freq[1][1]);
                --freq[1][0];
            }
            sb.append((char)freq[1][1]);
            --freq[1][0];
            sb.append((char)freq[2][1]);
            --freq[2][0];
        }        
        if(freq[1][0] > 0){
            sb.append((char)freq[1][1]);
            --freq[1][0];
        }
        if(freq[1][0] > 0){
            sb.append((char)freq[1][1]);
            --freq[1][0];
        }
        return sb.toString();
    }
}
