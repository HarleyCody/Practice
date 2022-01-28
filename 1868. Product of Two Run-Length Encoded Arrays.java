//Best Solution: do the calculate when encoded1[i][1] and encoded2[j][1] is not 0
class Solution {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
     
        int i = 0;
        int j = 0;
        List<List<Integer>> ans = new ArrayList<>();
        int last = 0;
        int count = 0;
        while (i < encoded1.length && j < encoded2.length) {
            // System.out.println("last = " + last + " count = " + count);
            if (encoded1[i][1] == 0) {
                i++;
            } else if (encoded2[j][1] == 0) {
                j++;
            } else {
                //ok neither is 0, so we are in business
                if (count == 0) {
                    last = encoded1[i][0] * encoded2[j][0];
                    count = Math.min(encoded1[i][1], encoded2[j][1]);
                    encoded1[i][1] -= count;
                    encoded2[j][1] -= count;
                } else if (last == encoded1[i][0] * encoded2[j][0]) {
                    int dif = Math.min(encoded1[i][1], encoded2[j][1]);
                    count += dif;
                    encoded1[i][1] -= dif;
                    encoded2[j][1] -= dif;
                } else {
                    //doesnt match, so add what was there and then move on
                    ans.add(List.of(last, count));
                    count = Math.min(encoded1[i][1], encoded2[j][1]);
                    last = encoded1[i][0] * encoded2[j][0];
                    encoded1[i][1] -= count;
                    encoded2[j][1] -= count;
                }
            }
        }
        ans.add(List.of(last, count));
        return ans;
    }
}
//My Solution: calcualte and move the pinter for each encoded array, collect sum if prevVal == encoded1 * encoded2
class Solution{
	public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2){
	int len = encoded1.length;
	int idx1 = 0;
	int idx2 = 0;
	int prevVal = -1;
	int num = 0;
	LinkedList<List<Integer>> ans = new LinkedList();
	while(idx1 < len){
		int curVal = encoded1[idx1][0] * encoded2[idx2][0];
int increment = Math.min(encoded1[idx1][1], encoded2[idx2][1]);
		
		if(curVal != prevVal){
			if(num != 0){
ans.offer(Arrays.asList(prevVal, num));
	}
prevVal = curVal;
	num = 0; 

}
encoded1[idx1][1] -= increment;
encoded2[idx2][1] -= increment; 
num += increment;
if(encoded1[idx1][1] == 0){
	++idx1;
}
if(encoded2[idx2][1] == 0){
	++idx2;
}
}
ans.offer(Arrays.asList(prevVal, num));
return ans;
}
}
