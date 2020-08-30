___________________________________________________________________________Best Solution(TLE)________________________________________________________________________
// precalcualte prime numbers within 100001;
// check common factor by checking prime, if these prime can be a factor, then it should be union, also update index of prime number
// when the first time meet prime, idx will be -1, only need to make it as a root(ie, update index)
class Solution {
    int max = 0;
    public int largestComponentSize(int[] A) {
        boolean[] isPrime = new boolean[100001];
        Arrays.fill(isPrime, true);
        Set<Integer> primes = new HashSet<>();
        // all primes less than 100000
        for (int i = 2; i <= 100000; i++) {
            if (isPrime[i]) {
                primes.add(i);
                for (int j = 2; j * i <= 100000; j++) {
                    isPrime[j * i] = false;
                }
            }
        }
        int n = A.length;
        int[] counts = new int[n];
        int[] parents = new int[n];
        int[] primeToIndex = new int[100001];
        Arrays.fill(primeToIndex, -1);
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            counts[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            int a = A[i];
            for (int p : primes) {
                if (primes.contains(a)) { // Optimization
                    p = a;
                }
                if (a % p == 0) {
                    if (primeToIndex[p] > -1) {
                        union(parents, counts, primeToIndex[p], i);
                    }
                    primeToIndex[p] = i;
                    while (a % p == 0) {
                        a /= p;
                    }
                }
                if (a == 1) {
                    break;
                }
            }
        }
        return max;
    }
    private int find(int[] parents, int a) {
        if (parents[a] != a) {
            parents[a] = find(parents, parents[a]);
        }
        return parents[a];
    }
    private void union(int[] parents, int[] counts, int a, int b) {
        int root1 = find(parents, a), root2 = find(parents, b);
        if (root1 == root2) {
            return;
        }
        int count = counts[root2] + counts[root1];
        max = Math.max(count, max);
        parents[root1] = root2;
        counts[root2] = count;
    }
}
_____________________________________________________________________________My Solution(TLE)________________________________________________________________________
// Union found, calcualte gcd for every pair( can be improved), union if their parent is not same
class Solution {
    int ans = 0;
    int[] root;
    int[] size;
    public int largestComponentSize(int[] A) {
        int len = A.length;
        root = new int[len];
        size = new int[len];
        
        for(int i = 0; i < len; ++i){
            root[i] = i;
            size[i] = 1;
        }
        
        for(int i = 0; i < len; ++i){
            int r = A[i];
            for(int j = i + 1; j < len; ++j){
                int c = A[j];
                if(gcd(r, c) > 1){
                    union(i, j);
                }
            }
        }
        
        return ans;
    }
    
    private int find(int cur){
        while(cur != root[cur]){
            cur = root[cur];
        }
        
        return cur;
    }
    
    private void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px == py){
            return;
        }
        if(size[px] > size[py]){
            root[py] = px;
            size[px] += size[py];
            ans = Math.max(size[px], ans);
        }else{
            root[px] = py;
            size[py] += size[px];
            ans = Math.max(size[py], ans);
        }
    }
    
    private int gcd(int a, int b){
        int t = 1;
        while(b != 0){
            t = a;
            a = b;
            b = t % b;
        }
        
        return a;
    }
}
