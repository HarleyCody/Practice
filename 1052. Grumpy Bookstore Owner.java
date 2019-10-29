____________________________________________________Best Solution_____________________________________________________________
// just improv the procedure
// use sliding window to caculate the sum of grumpy
public class Solution {
	public int maxSatisfied(int[] customers, int[] grumpy, int x) {
		if (customers == null || customers.length == 0) {
			return 0;
		}
		int sum_not_grumpy = 0;
		for (int i = 0; i < customers.length; i++) {
			if (grumpy[i] == 0) {
				sum_not_grumpy += customers[i];
				customers[i] = 0;
			}
		}
		int sum_grumpy = 0;
		for (int i = 0; i < x; i++) {
			sum_grumpy += customers[i];
		}
		int max = sum_grumpy;
		for (int i = x; i < customers.length; i++) {
			sum_grumpy += (customers[i] - customers[i - x]);
			max = Math.max(max, sum_grumpy);
		}
		return sum_not_grumpy + max;
	}
}
_________________________________________________________My Solution__________________________________________________________
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int ans = 0;
        int unaffected = 0;
        int[] a = new int[grumpy.length + X];
        int affected = 0;
        boolean angry = false;
        for(int i = 0; i < a.length; i++){
            if(i > customers.length){
                a[i] = a[i - 1];
            }
            if(i < customers.length && grumpy[i] == 0) {
                unaffected += customers[i];
            }else if (i < customers.length){
                affected += customers[i];
                angry = true;
            }
            if(i > X) a[i - X + 1] = affected - a[i - X];
        }
        if(!angry) return unaffected; 
        for(int i = 0; i < customers.length; i++){
            if(grumpy[i] == 1){
                ans = Math.max(ans, unaffected + a[i]);
            }
        }
        return ans;
    }
}
