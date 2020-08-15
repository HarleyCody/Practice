_______________________________________________________Rabin_Karp__________________________________________________________________________
  public int search(int L, int a, long modulus, int n, int[] nums) {
    // compute the hash of string S[:L]
    long h = 0;
      // + nums[i] is in case of hashcode is equal but string is not same
      // so when hashcode is same, it could be say that string is same.
    for(int i = 0; i < L; ++i) h = (h * a + nums[i]) % modulus;

    // already seen hashes of strings of length L
    HashSet<Long> seen = new HashSet();
    seen.add(h);
    // const value to be used often : a**L % modulus
    long aL = 1;
    for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;
      
      // check every substring with length L. 
    for(int start = 1; start < n - L + 1; ++start) {
      // compute rolling hash in O(1) time
      h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
      h = (h + nums[start + L - 1]) % modulus;
      if (seen.contains(h)) return start;
      seen.add(h);
    }
    return -1;
  }

___________________________________________________________________________Dijistra Alogrithm___________________________________________________________________________
public class Solution {
    // find a middle point that could reduce the distance between pont A to point B
    // int mazeII distance i, j represents distance from start to point(i, j)
    // use this min to update other distance
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dijkstra(maze, distance, visited);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }
    public int[] minDistance(int[][] distance, boolean[][] visited) {
        int[] min={-1,-1};
        int min_val = Integer.MAX_VALUE;
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                if (!visited[i][j] && distance[i][j] < min_val) {
                    min = new int[] {i, j};
                    min_val = distance[i][j];
                }
            }
        }
        return min;
    }
    public void dijkstra(int[][] maze, int[][] distance, boolean[][] visited) {
        int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
        while (true) {
            int[] s = minDistance(distance, visited);
            if (s[0] < 0)
                break;
            visited[s[0]][s[1]] = true;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                }
            }
        }
    }
}
_________________________________________________________________________________Binary Seach_________________________________________________________________________
// start from left to right, choose mid, right only change when it is possible eaqual to target;
// otherwise change left = m + 1;
// return right only

//Ascending order;
while(l < r){
  int m = (l + r) / 2;
  if(arr[m] >= tar){
      r = m;
  }else{
      l = m + 1;
  }
  return r;
}
//Descending order;
while(l < r){
  int m = (l + r) / 2;
  if(arr[m] > tar){
      l = m - 1;
  }else{
      r = m;
  }
  return r; 
}
____________________________________________________________________________Longest Palindrom Subsequence________________________________________________________________
//dp[i][j] max LPS start from i end to j
// As dp[0][l - 1] is answer so, from tail to start and test tail from i to j
// dp[i][l] = dp[i + 1][l];
private int lengthOfLPS(String s) {
    int L = s.length();
    if (L < 2) {
        return L;
    }
    char[] ca = s.toCharArray();
    int[][] dp = new int[L][L];
    
    for (int i = L - 1; i >= 0; --i) {
        dp[i][i] = 1;
        for (int j = i + 1; j < L; ++j)
            dp[i][j] = ca[i] == ca[j] ? 2 + dp[i + 1][j - 1] : Math.max(dp[i + 1][j], dp[i][j - 1]);
    }
    return dp[0][L-1];
}
____________________________________________________________________________Longest Common String________________________________________________________________
// Does not matter from tail to start or start to tail as it compare two strings
public int minInsertions(String s) {
    int n = s.length();
    int[][] dp = new int[n+1][n+1];
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            dp[i + 1][j + 1] = s.charAt(i) == s.charAt(j) ? dp[i][j] + 1 : Math.max(dp[i][j + 1], dp[i + 1][j]);
    return dp[n][n];
}
____________________________________________________________________________Reverse ListNode________________________________________________________________
    private void reverse(ListNode h, ListNode t){
        ListNode p = h, c = h.next, n = h.next;
        while(p != t){
            n = n.next;
            c.next = p;
            p = c;
            c = n;
        }
    }
