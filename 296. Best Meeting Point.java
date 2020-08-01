_______________________________________________________________________Best Solution_____________________________________________________________________________
// Improve: for the distance calculation, |mid - left| + |mid - right| == right - mid;
// As number of left is equals to it of right, two pointer to cumulate all right - left
// reduce complexity by half and do not use Math.abs() any more;
class Solution {
    // collect in order so do not need to sort any more, use same way collecting rows to collect cols
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);

        return minDistance1D(rows) + minDistance1D(cols);
    }

    private int minDistance1D(List<Integer> points) {
        int distance = 0;
        int i = 0;
        int j = points.size() - 1;
        while (i < j) {
            distance += points.get(j) - points.get(i);
            i++;
            j--;
        }
        return distance;
    }

    private List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    private List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }
}
_______________________________________________________________________Almost Best Solution_____________________________________________________________________________
//Improve: donot sort
class Solution {
    // collect in order so do not need to sort any more, use same way collecting rows to collect cols
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        int row = rows.get(rows.size() / 2);
        int col = cols.get(cols.size() / 2);
        return minDistance1D(rows, row) + minDistance1D(cols, col);
    }

    private int minDistance1D(List<Integer> points, int origin) {
        int distance = 0;
        for (int point : points) {
            distance += Math.abs(point - origin);
        }
        return distance;
    }

    private List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    private List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }
}
______________________________________________________________________General Solution_____________________________________________________________________________
class Solution {
    // think about 2D to 1D, in 1D the minDistance can split a group to left and right with equal number of numbers
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                    cols.add(col);
                }
            }
        }
        // do not have to sort rows, as it has been added with ascending order
        // choose mid of row
        int row = rows.get(rows.size() / 2);
        
        // choose mid or col
        Collections.sort(cols);
        int col = cols.get(cols.size() / 2);
        return minDistance1D(rows, row) + minDistance1D(cols, col);
    }

    private int minDistance1D(List<Integer> points, int origin) {
        int distance = 0;
        for (int point : points) {
            distance += Math.abs(point - origin);
        }
        System.out.println("dis is " + distance);
        return distance;
    }
}
________________________________________________________________________My Solution(Brute Force)_____________________________________________________________________________
class Solution {
    public int minTotalDistance(int[][] grid) {
        int ans = Integer.MAX_VALUE;
        
        int R = grid.length, C = grid[0].length;
        List<int[]> member = new ArrayList();
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(grid[r][c] == 1){
                    member.add(new int[]{r, c});
                }
            }
        }
        
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                int curDis = 0;
                for(int[] m : member){
                    curDis += Math.abs(m[0] - r) + Math.abs(m[1] - c);
                }
                
                ans = Math.min(curDis, ans);
            }
        }
        
        return ans;
    }
}
