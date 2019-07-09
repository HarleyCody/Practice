class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        // record status of every day within a cycle
        List<StringBuilder> recorder = new ArrayList();
        StringBuilder initial = new StringBuilder();
        // record status on 1st day
        int init = 0;
        
        int Days = N;
        // calculate status of every day
        while(N-- > 0){
            StringBuilder med = new StringBuilder();
            int[] next = new int[8];
            // current day status
            for(int i = 1; i < 8 - 1; ++i){
                if(cells[i - 1] == cells[i + 1]){
                    next[i] = 1;
                }
                else next[i] = 0;
            }
            if(init == 0){
                initial = intToSB(next);
                init = 1;
            }
            // if cycle emerge
            if(initial.toString().equals(intToSB(next).toString()) && recorder.size() != 0){
                int days = (Days - 1) % recorder.size();
                return SBToInt(recorder.get(days));
            }
            // current day status gives to last day
            cells = next.clone();
            // add recorde
            med = intToSB(next);
            recorder.add(med);
        }
        return cells;
    }
    private StringBuilder intToSB(int[] arr){
        StringBuilder ans = new StringBuilder();
        for(int i : arr){
            ans.append(i);
        }
        return ans;
    }
    private int[] SBToInt(StringBuilder s){
        int[] ans = new int[s.length()];
        for(int i = 0; i < s.length(); ++i){
            ans[i] = s.charAt(i) - '0';
        }
        return ans;
    }
}
