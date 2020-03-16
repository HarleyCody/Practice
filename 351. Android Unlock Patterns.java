____________________________________________________________Best Solution________________________________________________________________
public class Solution {
    // dfs cache store number of path with length[i]
    static int[] cache;
    boolean[] visited = new boolean [10];
    public int numberOfPatterns(int m, int n) {
        if (cache == null) {
            cache = new int[10];
            dfs(0, 0);
            for (int i = 1; i < 10; i++)
                cache[i] += cache[i-1];
        }
        return cache[n] - cache[m - 1];
    }
    
    void dfs(int x, int l) {
        if (l > 0) cache[l]++;
        for (int y = 1; y < 10; y++) {
            if (visited[y]) continue;
            if (l == 0 || validX2Y(x, y)) {
                visited[y] = true;
                dfs(y, l + 1);
                visited[y] = false;
            }
            // modify y that only start with 1, 2, 5
            if (l == 0) { //exploit the symmetries
                if (y == 5) break;
                if (y == 2) {
                    y += 2;
                    for (int i = 1; i < 10; i++) cache[i] *= 4;
                }
            }
        }
    }

    // find if X can jump to y
    boolean validX2Y(int x, int y) {
        if (x == 5 || y == 5) return true;
        // one of them are even number or one of them is even number,
        // visited[5] for case 2 to 8 ; 4 to 6, only need 5 is visited 
        if (x % 2 == 0 || y % 2 == 0) return x + y != 10 || visited[5];
        return visited[(y + x)/2];
    }
}
