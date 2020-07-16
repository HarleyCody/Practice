______________________________________________________________________________ Best Solution_____________________________________________________________________________
class Solution {
    //In 1770, Joseph Louis Lagrange proved a theorem, called Lagrange's four-square theorem
    //also known as Bachet's conjecture, which states that every natural number can be represented as the sum of four integer squares
    //p=a0^2 +a1^2 +a2 ^2 +a3^2 
    protected boolean isSquare(int n) {
        int sq = (int) Math.sqrt(n);
        return n == sq * sq;
    }

    public int numSquares(int n) {
        // four-square and three-square theorems.
        while (n % 4 == 0)
        n /= 4;
        if (n % 8 == 7)
        return 4;

        if (this.isSquare(n))
        return 1;
        // enumeration to check if the number can be decomposed into sum of two squares.
        for (int i = 1; i * i <= n; ++i) {
        if (this.isSquare(n - i * i))
            return 2;
        }
        // bottom case of three-square theorem.
        return 3;
    }
}
______________________________________________________________________________ DP Solution_____________________________________________________________________________
class Solution {
    // dp[i] = dp[i - square] + 1;
    // from small to large, so when check i - square + 1 it will never be null
    public int numSquares(int n) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // bottom case
        dp[0] = 0;

        // pre-calculate the square numbers.
        int max_square_index = (int) Math.sqrt(n) + 1;
        int square_nums[] = new int[max_square_index];
        for (int i = 1; i < max_square_index; ++i) {
            square_nums[i] = i * i;
        }

        for (int i = 1; i <= n; ++i) {
            for (int s = 1; s < max_square_index; ++s) {
                if (i < square_nums[s])
                    break;
                    dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
            }
        }
        return dp[n];
    }
}
______________________________________________________________________________ BFS Solution_____________________________________________________________________________
class Solution {
    // bfs assures shortest path, only check reminder, which is cur - square in next round
    public int numSquares(int n) {

        ArrayList<Integer> square_nums = new ArrayList<Integer>();
        for (int i = 1; i * i <= n; ++i) {
          square_nums.add(i * i);
        }

        Set<Integer> queue = new HashSet<Integer>();
        queue.add(n);

        int level = 0;
        while (queue.size() > 0) {
            level += 1;
            Set<Integer> next_queue = new HashSet<Integer>();

            for (Integer remainder : queue) {
                for (Integer square : square_nums) {
                    if (remainder.equals(square)) {
                        return level;
                    } else if (remainder < square) {
                        break;
                    } else {
                        next_queue.add(remainder - square);
                    }
                }
            }
            queue = next_queue;
        }
        return level;
    }
}
