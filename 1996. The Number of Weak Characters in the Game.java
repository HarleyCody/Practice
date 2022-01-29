//Best Solution: bucket sort by first properties and preMax array from tail to head to get the later max 
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        int[] maxH = new int[100002];
        int count = 0;
        for(int[] point:properties){
            maxH[point[0]] = Math.max(point[1],maxH[point[0]]);
        }
        for(int i=100000;i>=0;i--){
            maxH[i] = Math.max(maxH[i+1],maxH[i]);
        }
        
        for(int[] point:properties){
            if(point[1]<maxH[point[0]+1])
                count++;
        }
        return count;
    }
}
//My Solution: Ascending by first properties and descending by second property monoton stack that store the stronger person
class Solution{
	public int numberOfWeakCharacters(int[][] properties){
        int len = properties.length;
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int idx = -1;
        for(int i = 0; i < len; ++i){
            while(idx >= 0 && properties[i][0] > properties[idx][0] && properties[i][1] > properties[idx][1]){
                --idx;
            }
            properties[++idx][0] = properties[i][0];
            properties[idx][1] = properties[i][1];
        }

        return len - idx - 1;
    }
}
