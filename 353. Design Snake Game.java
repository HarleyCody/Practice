class SnakeGame {
//     class bodyNode{
//         int x;
//         int y;
        
//     }
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    // record index food 
    int foodIdx = -1;
    // column
    int w;
    // row
    int h;
    int[] head = new int[2];
    int[] tail = new int[2];
    // record food.
    int[][] f;
    // record body
    Queue<Integer> body = new LinkedList();
    public SnakeGame(int width, int height, int[][] food) {
        if(food.length > 0 && food[0].length > 0){
            foodIdx = 0;
        }else{
            return;
        }
        w = width;
        h = height;
        f = food;
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] newPos = head;
        // calcualte current position.
        switch(direction){
            case "U":
                newPos[0]--;
                break;
            case "D":
                newPos[0]++;
                break;
            case "L":
                newPos[1]--;
                break;
            case "R":
                newPos[1]++;
                break;
        }
        // if curPos in the panel and not overlap with body
        if(validNewPos(newPos)){
            // head == curPos
            int[] head = new int[]{newPos[0], newPos[1]};
            // record head to body
            body.offer(newPos[0] * w + newPos[1]);
            // current pos is food, get newPos of food. tail keeps unchanged
            if(foodIdx < f.length && head[0] == f[foodIdx][0] && head[1] == f[foodIdx][1]){
                    foodIdx++;
            }else{
                // not food, tail goes forward;
                // tail is not in the body, as next time tail grid can be head(circle)
                int temp = body.poll();
                tail[0] = temp / w;
                tail[1] = temp % w;
            }
            // score == size of body;
            return body.size();
        }
        // not a valid pos, game over
        return -1;
    }
    // check if new pos is valid
    private boolean validNewPos(int[] pos){
        // out of range
        if(pos[0] < 0 || pos[0] == h || pos[1] < 0 || pos[1] == w){
            return false;
        }else if(body.contains(pos[0] * w + pos[1])){ // hit body.
            return false;
        }
        return true;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
