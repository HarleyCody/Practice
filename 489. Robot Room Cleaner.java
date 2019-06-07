/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class Solution {
    public void cleanRoom(Robot robot) {
        
        //record cleaned place
        Set<String> record = new HashSet();
        // position of robot
        int i = 0, j = 0;
        backtrack(robot, record, 0, 0, 0);
    }
    public void backtrack(Robot robot, Set<String> record, int i, int j, int dir){
        // current pos record.
        String pos = i + "," + j;
        if(record.contains(pos)) return;
        
        record.add(pos);
        robot.clean();
        
        for(int d = 0; d < 4; ++d){
            if(robot.move()){
                
/*switch only change one of x, y so the last postion should be recorded， when backtrack, i j should be covered, if edit i j in cases, i j will not be recovered. It is still value transfer.*/
                
                //current position
                int x = i, y = j;
                
                //new postion
                switch(dir){
                    case 0:
                        --x;
                        break;
                    case 90:
                        ++y;
                        break;
                    case 180:
                        ++x;
                        break;
                    case 270:
                        --y;
                        break;
                    default:
                        break;
                }
                
                backtrack(robot, record, x, y, dir);
                //backtrack recover postion and direction
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            // after backtrack, choose a new direction to move.
            robot.turnRight();
            dir += 90;
            dir %= 360;
        }
    }
}
