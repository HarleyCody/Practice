___________________________________________________Best Solution_______________________________________________________________
class Solution {
    public int primePalindrome(int N) {
        if (8 <= N && N <= 11) return 11;
        // 100000 covers all int from 1 - 999999999( > 2 * 10^8) answer is garranteed based on question
        for (int x = 1; x < 100000; x++) {
            // if here x is single digi, r.substing(1) == "";
            String s = Integer.toString(x), r = new StringBuilder(s).reverse().toString();
            // y is definitely palindrom eg 12, + 1(2 is cut) == 121
            int y = Integer.parseInt(s + r.substring(1));
            if (y >= N && isPrime(y)) return y;
        }
        return -1;
    }
    
    public Boolean isPrime(int x) {
        // even num only 2 is prime
        if (x < 2 || x % 2 == 0) return x == 2;
        // odd * even == even, odd * odd = odd; so only need to check odd factors.
        for (int i = 3; i * i <= x; i += 2)
            if (x % i == 0) return false;
        return true;
    }
}
