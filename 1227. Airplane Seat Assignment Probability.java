/* Best Solution: Deduction to when n >= 2 prob = 0.5 otherwise it is 1;
Let f(n) be the probability that the n-th passenger will get his own seat.
If the 1st passenger get the 1st seat, then everyone will get their own seats, so the n-th passenger gets his own seat with probability: 1/n
If the 1st passenger get the 2nd seat, with probability 1/n, then n-1 seats left. Here, the 2nd passenger faces the same situation of the 1st passenger, he can just randomly choose a seat. So it is the same question but n becomes n-1. In this situation, the probability that the n-th passenger gets his seat is f(n-1). So the final probability is f(n-1)*(1/n)
If the 1st passenger get the 3rd seat, ..., the probability is f(n-2)*(1/n)
...
....
......
Then f(n) = 1/n + f(n-1)*(1/n)+ f(n-2)*(1/n) + ... + f(2)*(1/n)
Let's solve this recursive formula:
nf(n) = 1+ f(n-1)+ f(n-2) + ... + f(2) ...... Equation 1
(n-1)f(n-1) = 1+ f(n-2)+ ... + f(2) ...... Equation 2
Use Equation 1 - Equation 2
nf(n) - (n-1)f(n-1) = f(n-1)
so
f(n) = f(n-1) when n>=3
We already know f(2)=0.5
So f(n) = 0.5 when n>=2
*/

class Solution {
    public double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1.0 : 1.0 / 2;     
    }
}
//More naive solution:
//dp: f(n) = 1/ n * (f(n-1) + f(n-2) + ... + f(2) + f(1))
class Solution {
    public double nthPersonGetsNthSeat(int n) {
        double cur = 1;
        double prev = 1;
        for(int i = 2; i < n + 1; ++i){
            cur = 1.0 / i * prev;
            prev += cur;
        }
        
        return cur;
    }
}
