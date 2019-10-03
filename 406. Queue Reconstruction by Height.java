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
