__________________________________________________________Best Solution__________________________________________________________________
class Solution {
   public int[][] reconstructQueue(int[][] people) {
		return reconstructQueueInsert(people);
	}
	public int[][] reconstructQueueInsert(int[][] people) {
		if(people == null || people.length < 2) return people;
        
		quickSortByReverseHeight(people, 0, people.length - 1);
        
		List<int[]> list = new ArrayList<int[]>();
        // high -> low if height same, smaller k -> larger k;
		for(int[] person : people) list.add(person[1], person);
		return list.toArray(people);
	}
	//First Sort By Height Reverse, make sure only need to insert by k can be answer
	//If Height Is Same, Sort By Position
	private void quickSortByReverseHeight(int[][] arr, int low, int high) {
		if(low >= high) return;
		int nextParitionHigh = low, nextParitionLow = high;
		int[] pivot = arr[low + (high - low) / 2];
		while(nextParitionHigh < nextParitionLow) {
            // put forward
			while(arr[nextParitionHigh][0] > pivot[0] ||
					(arr[nextParitionHigh][0] == pivot[0] && arr[nextParitionHigh][1] < pivot[1])){
				nextParitionHigh++;
			}
            // put back
			while(arr[nextParitionLow][0] < pivot[0] ||
					(arr[nextParitionLow][0] == pivot[0] && arr[nextParitionLow][1] > pivot[1])){
				nextParitionLow--;
			}
			if(nextParitionHigh <= nextParitionLow)swap(arr, nextParitionHigh++, nextParitionLow--);
		}
		quickSortByReverseHeight(arr, low, nextParitionLow);
		quickSortByReverseHeight(arr, nextParitionHigh, high);
	}
    public void swap(int[][] arr, int i, int j) {
		if( i == j) return;
		int[] t = arr[i];
		arr[i] = arr[j];
		arr[j]  = t;
	}
}
__________________________________________________________My Solution____________________________________________________________________
class Solution {
    List<int[]> undecided = new ArrayList();
    List<int[]> decided = new ArrayList();
    public int[][] reconstructQueue(int[][] people) {
        // sort by num of people, if equal, sort by height;
        Arrays.sort(people, new Comparator<int[]>(){
           public int compare(int[] p1, int[] p2){
               return p1[1] == p2[1] ? p1[0] - p2[0] : p1[1] - p2[1];
           }
        });
        // people with num 0 sorted first;
        for(int i = 0; i < people.length; i++){
            if(people[i][1] != 0){
                undecided.add(people[i]);
            }
            else{
                decided.add(people[i]);
            }
        }
        // insert to decided
        while(!undecided.isEmpty()){
            int[] element = undecided.remove(0);
            // need more people ahead, so cannot decided
            if(element[1] > decided.size()){
                undecided.add(element);
            }else{
                int insert = 0, num = 0;
                while(insert < decided.size()){
                    // record num of people;
                    if(decided.get(insert)[0] >= element[0]){
                        num++;
                    }
                    // move to next pos;
                    insert++;
                    // met reqirement, find exact pos;
                    if(num == element[1]){
                        // insert before element[0] < next[0]
                        while(insert < decided.size() && element[0] > decided.get(insert)[0]){
                            insert++;
                        }
                        // insert as last
                        if(insert == decided.size()){
                            decided.add(element);
                        }else{
                            // insert as the middle, element in insert will be move to next
                            decided.add(insert, element);
                        }
                    }
                }
                // does not meet reqirement, can not decided
                if(num < element[1]){
                    undecided.add(element);
                }
            }
        }
        // list to array
        for(int i = 0 ; i < decided.size(); i ++){
            people[i] = decided.get(i);
        }
        return people;
    }
}
