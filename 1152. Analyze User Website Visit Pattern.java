class Solution {
    // bond time with website name for the convenience of sort by time
    class History{
        int time;
        String name;
        public History(int t, String n){
            time = t;
            name = n;
        }
    }
    HashMap<String, List<History>> recorder = new HashMap();
    HashMap<String, Integer> counter = new HashMap();
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        // seperate histories by user
        for(int i = 0; i < username.length; i++){
            List<History> histories = recorder.getOrDefault(username[i], new ArrayList());
            histories.add(new History(timestamp[i],website[i]));
            recorder.put(username[i], histories);
        }
        // find combination, record maxTimes;
        int max = 0;
        for(String user : recorder.keySet()){
            List<History> histories = recorder.get(user);
            // sort by time
            Collections.sort(histories, (x,y)->{ return x.time - y.time;});
            // in case of same trace being accumalted twice by same user
            HashSet<String> visited = new HashSet();
            for(int i = 0; i < histories.size() - 2; i++){
                String trace;
                String web1 = histories.get(i).name;
                for(int j = i + 1; j < histories.size() - 1; j++){
                    String web2 = histories.get(j).name;
                    // prepare for complete trace;
                    StringBuilder threeWebs = new StringBuilder(web1).append(",").append(web2).append(",");
                    for(int k = j + 1; k < histories.size(); k++){
                        String web3 = histories.get(k).name;
                        StringBuilder temp = new StringBuilder(threeWebs);
                        temp.append(web3);
                        trace = temp.toString();
                        // trace is visited by same user, find next trace;
                        if(visited.contains(trace)) continue;
                        int num = counter.getOrDefault(trace, 0) + 1;
                        counter.put(trace, num);
                        // update maxTimes
                        max = Math.max(num, max);
                        // record visited trace;
                        visited.add(trace);
                    }
                }
            }
        }
        // find trace with maxTimes with lexicographical order;
        String ans = null;
        for(String webs : counter.keySet()){
            if(counter.get(webs) == max){
                ans = ans == null? webs : ans.compareTo(webs) <= 0 ? ans : webs;
            }
        }
        return Arrays.asList(ans.split(","));
    }
}
