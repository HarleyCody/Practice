//My Solution: DFS to detect and color the border
class Solution{
	boolean[][] visited;
	int R = 0;
	int C = 0;
	public int[][] colorBorder(int[][] grid, int row, int col, int color){
		if(grid[row][col ] == color) return grid;
		R = grid.length;
		C = grid[0].length;
		visited = new boolean[R][C];
		return colorBorder(grid, row, col, color, grid[row][col]);
}

private int[][] colorBorder(int[][] grid, int r, int c, int color, int ogColor){
	visited[r][c] = true;
	boolean isBorder = r == 0 || c == 0 || r == R - 1 || c == C - 1 || grid[r - 1][c] != ogColor || grid[r + 1][c] != ogColor || grid[r][c - 1] != ogColor || grid[r][c + 1] != ogColor;
	if(r > 0 && grid[r - 1][c] == ogColor && !visited[r - 1][c]){
	colorBorder(grid, r - 1, c, color, ogColor);
}
if(r < R - 1 && grid[r + 1][c] == ogColor && !visited[r + 1][c]){
	colorBorder(grid, r + 1, c, color, ogColor);
}
if(c > 0 && grid[r][c - 1] == ogColor && !visited[r][c - 1]){
	colorBorder(grid, r, c - 1, color, ogColor);
}
if(c < C - 1 && grid[r][c + 1] == ogColor && !visited[r][c + 1]){
	colorBorder(grid, r, c + 1, color, ogColor);
}
if(isBorder){
	grid[r][c] = color;
}
return grid;
}
}

