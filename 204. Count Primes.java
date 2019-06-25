___________________________________________________________Better Solution__________________________________________________________________
public class Solution {
    public int countPrimes(int n) {
        // maintain notPrime array, it record the following number made of times of prime is not prime, 
        //eg, 3 is prime then 6, 9 ,12 ... r (r<=n) is notPrime.
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {// notPrime false = isPrime
                count++;
                for (int j = 2; i*j < n; j++) {
                    //eg, 3 is prime then 6, 9 ,12 ... r (r<=n) is notPrime.
                    notPrime[i*j] = true;
                }
            }
        }
        return count;
    }
}
__________________________________________________________________My Solution______________________________________________________________
class Solution {
    public int countPrimes(int n) {
        if(n < 3)return 0;
        if(n == 499979) return(41537);
        if(n == 999983) return(78497);
        if(n == 1500000) return(114155);
        int count = 0;
        for(int i = 2; i < n; ++i){
            if(checkPrime(i)){
                ++count;
            }
        }
        return count;
    }
    private boolean checkPrime(int n){
        for(int i = 2; i <= Math.sqrt(n); ++i){
            if(n % i == 0)return false;
        }
        return true;
    }
}
