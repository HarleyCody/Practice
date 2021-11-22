//DP Solution: Calcualate distance from right to left.
//After getting distance find the path by looking backward with certain previous city by dfs
//Improve: Do not need to record the path for every node and use array to record neighbors are faster
class Solution {
    int n;
    String[] names;
    String[] targetPath;
    int targetLength;
    int[][] distancesAtCities;
    int[][] graph;
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {

        this.n = n;
        this.names = names;
        this.targetPath = targetPath;
        graph = createGraph(n, roads);
       
        targetLength = targetPath.length;
        distancesAtCities = new int[targetLength][n];

        setLastDistances();

        calculateDistancesBackwards();

        return evaluateMostSimilarPath();
    }
    private int[][] createGraph(int n, int[][] roads) {
        int[][] graph = new int[n][];

        int[] adjacentCount = new int[n];
        for (int[] road : roads) {
            adjacentCount[road[0]]++;
            adjacentCount[road[1]]++;
        }

        for (int i = 0; i < n; i++) {
            graph[i] = new int[adjacentCount[i]];
        }
        int[] currIdx = new int[n];
        for (int[] road : roads) {
            graph[road[0]][currIdx[road[0]]++] = road[1];
            graph[road[1]][currIdx[road[1]]++] = road[0];
        }
        return graph;
    }
    
    private void setLastDistances() {
        String targetCity = targetPath[targetLength - 1];
        for (int i = 0; i < n; i++) {
            String curCity = names[i];
            if (!targetCity.equals(curCity)) {
                distancesAtCities[targetLength - 1][i] = 1;
            }
        }
    }

    private void calculateDistancesBackwards() {
        String targetCity;

        for (int t = targetLength - 2; t >= 0; t--) {
            targetCity = targetPath[t];

            for (int c = 0; c < n; c++) {
                String curCity = names[c];

                if (!curCity.equals(targetCity)) {
                    distancesAtCities[t][c] = 1;
                }
                int[][] graph = this.graph;
                int minNextValue = Integer.MAX_VALUE;
                if(graph[c]!=null)
                for (int nextCity : graph[c]) {
                    
                        minNextValue = Math.min(distancesAtCities[t + 1][nextCity], minNextValue);
                    
                }

                distancesAtCities[t][c] += minNextValue;
            }
        }
    }

    private List<Integer> evaluateMostSimilarPath() {
        List<Integer> mostSimilarPath = new ArrayList<>(targetLength);
        int prevCity = 0;

        for (int i = 1; i < n; i++) {
            if (distancesAtCities[0][i] < distancesAtCities[0][prevCity]) {
                prevCity = i;
            }
        }
        mostSimilarPath.add(prevCity);

        addNextVertices(mostSimilarPath, prevCity, 1);

        return mostSimilarPath;
    }

    private void addNextVertices(List<Integer> mostSimilarPath, int prevCity, int idx) {

        if (idx == targetLength) {
            return;
        }

        int curMinCity = -1;
        int curMinDist = Integer.MAX_VALUE;

          for (int nextCity : graph[prevCity]) {

            if (distancesAtCities[idx][nextCity] < curMinDist) {
                curMinDist = distancesAtCities[idx][nextCity];
                curMinCity = nextCity;
            }
        }

        mostSimilarPath.add(curMinCity);
        addNextVertices(mostSimilarPath, curMinCity, idx + 1);
    }
}
//My Solution: Score[i][j]: max number of matches in current path that has names[i] at position j;
//Calculate score and get the max and return the max Path
class Solution {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        int len = targetPath.length;
        int[][] score = new int[n][len];
        List<Integer>[][] paths = new ArrayList[n][len];
        List<Integer>[] map = new ArrayList[n];
        for(int[] road : roads){
            int from = road[0];
            int to = road[1];
            if(map[from] == null){
                map[from] = new ArrayList();
            }
            map[from].add(to);
            if(map[to] == null){
                map[to] = new ArrayList();
            }
            map[to].add(from);
        }
        int maxScore = 0;
        for(int i = 0; i < n; ++i){
            paths[i][0] = new ArrayList();
            paths[i][0].add(i);
            if(names[i].equals(targetPath[0])){
                score[i][0] = 1;
                maxScore = 1;
            }else{
                score[i][0] = 0;
            }
        }
        for(int i = 0; i < len - 1; ++i){
            for(int j = 0; j < n; ++j){
                if(map[j] == null) continue;
                for(int next : map[j]){
                    int val = names[next].equals(targetPath[i + 1]) ? 1 : 0;
                    if(score[next][i + 1] <= score[j][i] + val){
                        score[next][i + 1] = score[j][i] + val;
                        paths[next][i + 1]  = new ArrayList(paths[j][i]);
                        paths[next][i + 1].add(next);
                        maxScore = Math.max(maxScore, score[next][i + 1]);
                    }
                }
            }
        }
        if(maxScore == 0){
            for(int i = 0; i < n; ++i){
                if(paths[i][len - 1] != null) return paths[i][len - 1];
            }
        }else{
            for(int i = 0; i < n; ++i){
                if(score[i][len - 1] == maxScore) return paths[i][len - 1];
            }
        }
        
        return null;
    }
}
