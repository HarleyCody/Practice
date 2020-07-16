________________________________________________________________________________My Solution_____________________________________________________________________________________
class TopVotedCandidate {
    // record rlt from time[i] in rlts[]
    // use binary search to match start time;
    // if idx < 0 eactly position will be -(idx + 1);
    // however should return the rlt in previous start time; so idx need -1 to get rlt;
    // return rlts[idx]
    int[] time;
    int[] rlts;
    public TopVotedCandidate(int[] persons, int[] times) {
        int len = persons.length;
        time = times;
        rlts = new int[len];
        
        int topVotes = 0;
        int topCandid = -1;
        int[] votes = new int[len];
        
        for(int i = 0; i < len; ++i){
            ++votes[persons[i]];
            if(votes[persons[i]] >= topVotes){
                topVotes = votes[persons[i]];
                topCandid = persons[i];
            }
            rlts[i] = topCandid;
        }
    }
    
    public int q(int t) {
        int idx = Arrays.binarySearch(time, t);
        if(idx < 0){
            idx = -(idx + 1);
            --idx;
        }
        return rlts[idx];
    }
}
_________________________________________________________________________________My Solution_____________________________________________________________________________________
class TopVotedCandidate {
    // use sorted map to get the rlt in most recent election
    TreeMap<Integer, Integer>recorder = new TreeMap();
    public TopVotedCandidate(int[] persons, int[] times) {
        int topVotes = 0;
        int topCandid = -1;
        int len = persons.length;
        int[] votes = new int[len];
        for(int i = 0; i < len; ++i){
            ++votes[persons[i]];
            if(votes[persons[i]] >= topVotes){
                topVotes = votes[persons[i]];
                topCandid = persons[i];
            }
            recorder.put(times[i], topCandid);
        }
    }
    
    public int q(int t) {
        int f = recorder.floorKey(t);
        return recorder.get(f);
    }
}
