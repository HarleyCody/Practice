_______________________________________________________________________________Best Solution________________________________________________________________________________
class Solution { 
        // after one cycle:
        // robot returns into initial position
        // or robot doesn't face north
    public boolean isRobotBounded(String instructions) {
        // north = 0, east = 1, south = 2, west = 3
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Initial position is in the center
        int x = 0, y = 0;
        // facing north
        int idx = 0;
        
        for (char i : instructions.toCharArray()) {
            if (i == 'L')
                idx = (idx + 3) % 4;
            else if (i == 'R')
                idx = (idx + 1) % 4;
            else {
                x += directions[idx][0];
                y += directions[idx][1];   
            }    
        }
        
        // after one cycle:
        // robot returns into initial position
        // or robot doesn't face north
        return (x == 0 && y == 0) || (idx != 0);
    }
}
________________________________________________________________________________My Solution________________________________________________________________________________
// Record every status
class Solution {
    public boolean isRobotBounded(String ins) {
        char[] com = ins.toCharArray();
        int len = com.length;
        HashSet<String> been = new HashSet();
        int dir = 0, x = 0, y = 0;
        been.add("0 0 -1");
        int times = 0;
        for(int i = 0; times < 4 && i < len; ++i){            
            if(com[i] == 'G'){
                switch(dir){
                    case 0:
                        --x;
                        break;
                    case 90 :
                        ++y;
                        break;
                    case 180:
                        ++x;
                        break;
                    case 270:
                        --y;
                        break;
                }
            }else if(com[i] == 'R'){
                dir += 90;
            }else{
                dir -= 90;
            }
            if(dir < 90){
                dir += 360;
            }
            dir %= 360;
            if(i == len - 1){
                ++times;
                i = -1;
            }
            if(!been.add(x + " " + y + " " + i)){
                return true;
            }
        }
        
        return false;
    }
}
