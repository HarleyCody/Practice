____________________________________________________Best Solution________________________________________________________________
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) {
			return -1;
		}
// We assume index=0 initially to find out if we can travel around the circuit once
		int startingGasStation = 0;
        // tracks the sum of the gas required to reach startingGasstation
        // sumOfGas - sumOfCost.
		int totalRequiredGas = 0;
		int suplusGas = 0; // Additional Gas that is left over
		for (int station = 0; station < gas.length; station++) {
// Gas Required to reach next station = gas[station]-cost[station]
			suplusGas += gas[station] - cost[station];
			totalRequiredGas += gas[station] - cost[station];
            // unable to reach next station.
			if (suplusGas < 0) {
				suplusGas = 0;
//If sum of gas less than sum of cost, the travel can not be back to the original station.
// so update the startingGasStation to next station and find out if we can travel around the circuit once.
				startingGasStation = station + 1;
			}
		}
		return totalRequiredGas >= 0 ? startingGasStation : -1;
    }
}
____________________________________________________My Solution________________________________________________________________
class Solution {
    // start from i went to last, 
    // if it can pass, 
    // start from 0 to i if it still can pass, return i;
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        boolean stopped = false;
        for(int i = 0; i < gas.length; i++){
            tank = 0;
            stopped = false;
            for(int j = i; j < gas.length; j++){
                tank += gas[j];
                if(tank < cost[j]){
                    stopped = true;
                    break;
                }
                tank -= cost[j];
            }
            if(stopped) continue;
            for(int j = 0; j < i; j++){
                tank += gas[j];
                if(tank < cost[j]){
                    stopped = true;
                    break;
                }
                tank -= cost[j];
            }
            if(stopped) continue;
            return i;
        }
        return -1;
    }
}
