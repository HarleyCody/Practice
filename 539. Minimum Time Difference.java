_______________________________________________________Best Solution___________________________________________________________
class Solution {
    public int time2int(String str) {
        int hour = (str.charAt(0) - '0') * 10 + (str.charAt(1) - '0');
        int min = (str.charAt(3) - '0') * 10 + (str.charAt(4) - '0'); 
        return hour * 60 + min;
    }
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        boolean[] t = new boolean[24 * 60];
        // time to int and sort;
        for (int i = 0; i < n; i++) {
            int c = time2int(timePoints.get(i));
            if (t[c]) return 0;
            else t[c] = true;
        }

        int min = 24 * 60;
        int ans = 24 * 60;
        int last = -1;
        for (int i = 0; i < 24 * 60; i++){
            //calculate minimal minutes between adjascent two time;
            if (t[i]) {
                min = Math.min(min, i);
                if (last >= 0)
                    // i - last == current min;
                    ans = Math.min(ans, i - last);
                last = i;
            }
        }
        // choose minimal one, eg 00:00 23:59, choose min from(1439(23:59 - 00:00), 1(00:00 - 23:59));
        ans = Math.min(ans, 1440 - last + min);

        return ans;
    }
}
_________________________________________________________My Solution___________________________________________________________
class Solution {
    // transfer time to int. sort recorder
    // comparing adjascent two element get min. last one should compare with first one.
    public int findMinDifference(List<String> timePoints) {
        int[] recorder = new int[timePoints.size()];
        for(int i = 0; i < timePoints.size(); ++i){
            recorder[i] = timeToInt(timePoints.get(i));
        }
        Arrays.sort(recorder);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < timePoints.size(); ++i){
            int cur = i == timePoints.size() - 1? recorder[i] - recorder[0] : recorder[i + 1] - recorder[i];
            if(cur > 720) cur = 1440 - cur;
            min = Math.min(min, cur);
        }
        return min;
    }
    public int timeToInt(String s){
        String[] strs = s.split(":");
        int ans = Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
        return ans;
    }
}
