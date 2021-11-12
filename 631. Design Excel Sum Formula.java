//Best Solution: Design class cell to control val and formula
//Best Solution: Design class cell to control val and formula
class Excel {
    class Cell {
        Integer val;
        boolean isNum;
        String[] formula;   
        public void setNum(int val) {
            this.val = val;
            this.isNum = true;
        }  
        public void setFormula(String[] s) {
            this.formula = s;
            this.isNum = false;
        }    
        public Integer calcFormula() {
            int sum = 0;
            for (String s: this.formula) {
                int c1 = s.charAt(0) - 'A';
                if (s.length() <= 3) {
                    Integer r1 = Integer.parseInt(s.substring(1)) - 1;
                    sum += grid[r1][c1].isNum ? grid[r1][c1].val : grid[r1][c1].calcFormula();                    
                } else {
                    int i = 0;
                    while (s.charAt(i) != ':') {
                        i++;
                    }
                    int r1 = Integer.parseInt(s.substring(1, i)) - 1;
                    int c2 = s.charAt(i+1) - 'A';
                    int r2 = Integer.parseInt(s.substring(i+2)) - 1;
                    for (i=r1; i<=r2; i++) {
                        for (int j=c1; j<=c2; j++) {
                            sum += grid[i][j].isNum ? grid[i][j].val : grid[i][j].calcFormula();   
                        } 
                    } 
                } 
            }                
            return sum; 
        } 
    }
    
    private Cell[][] grid;
    
    public Excel(int height, char width) {
        this.grid = new Cell[height][width-'A'+1];
        for (int h=0; h<height; h++) {
            for (int w=0; w<(width-'A'+1); w++) {
                grid[h][w] = new Cell();
                grid[h][w].setNum(0);
            } 
        } 
    }
    
    public void set(int row, char column, int val) {
        grid[row-1][column-'A'].setNum(val);
    } 
    
    public int get(int row, char column) {
        return grid[row-1][column-'A'].isNum ? grid[row-1][column-'A'].val : grid[row-1][column-'A'].calcFormula();

    } 
    public int sum(int row, char column, String[] numbers) {
        grid[row-1][column-'A'].setFormula(numbers);
        return grid[row-1][column-'A'].calcFormula(); 
    }
}
//My Solution: Due to existence of formula, so we can use prefix sum
//Record formula and value in the cell
//set will clear the formula
//sum will set the fomula
//get from formula if cell has formula, otherwise get from table
class Excel {
    int[][] table;
    int H;
    int W;
    String[][][] formula;
    public Excel(int height, char width) {
        H = height + 1;
        W = getCol(width) + 1;
        table = new int[H][W];
        
        formula = new String[H][W][];
    }
    
    public void set(int row, char column, int val) {
        set(row, getCol(column), val);
    }
    private void set(int row, int col, int val){
        formula[row][col] = null;
        table[row][col] = val;
    }
    public int get(int row, char column) {
        return get(row, getCol(column));
    }
    
    private int get(int row, int col){
        if(formula[row][col] != null){
            return sum(row, col, formula[row][col]);
        }
        return table[row][col];
    }

    public int sum(int row, char column, String[] numbers) {
        return sum(row, getCol(column), numbers);
    }
    private int sum(int row, int col, String[] numbers){
        int ans = 0;
        for(String number : numbers){
            int[] co = getCoord(number.split(":"));
            if(co[3] == -1){
                ans += get(co[0], co[1]);
                continue;
            }
            for(int r = co[0]; r <= co[2]; ++r){
                for(int c = co[1]; c <= co[3]; ++c){
                    ans += get(r, c);
                }
            }
        }
        set(row, col, ans);
        formula[row][col] = numbers;
        return ans;
    }
    private int getCol(char column){
        return column - 'A';
    }
    private int getRow(String formula){
        int ans = 0;
        for(int i = 1; i < formula.length(); ++i){
            ans *= 10;
            ans += formula.charAt(i) - '0';
        }
        return ans;
    }
    private int[] getCoord(String[] formula){
        int lc = getCol(formula[0].charAt(0));
        int lr = getRow(formula[0]);
        int rc = formula.length > 1 ? getCol(formula[1].charAt(0)) : -1;
        int rr = formula.length > 1 ? getRow(formula[1]) : -1;
        
        return new int[]{lr, lc, rr, rc};
    }
}
