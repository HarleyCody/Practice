class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if(matrix.length == 0) return ans;
        int r = matrix.length, c = matrix[0].length;
        int i = 0, j = 0, leftJ = 0, rightJ = c - 1, upI = 0, downI = r - 1, directI = 0, directJ = 1;
        
        // in case of in the margin the boarder will change everytime 
        boolean up = false, down = false, left = false, right = true; 
        
        while( upI <= i && i <= downI && leftJ <= j && j <= rightJ){
            ans.add(matrix[i][j]);
            
            if(j == rightJ && right){// go down Flag right states the current direction before turning
                directI = 1;
                directJ = 0;
                ++upI;
                right = false;
                down = true;
            }else if(i == downI && down){//go left Flag down states the current direction before turning
                directI = 0;
                directJ = -1;
                --rightJ;
                down = false;
                left = true;
            }else if(j == leftJ && left){//go up Flag left states the current direction before turning
                directI = -1;
                directJ = 0;
                --downI;
                left = false;
                up = true;
            }else if(i == upI && up){// go right Flag up states the current direction before turnin
                directI = 0;
                directJ = 1;
                ++leftJ;
                right = true;
                up = false;
            }
            
            i += directI*1;
            j += directJ*1;
            
        }
        return ans;
    }
}
