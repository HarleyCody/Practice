class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] pair : prerequisites) {
            inDegree[pair[0]]++;
            graph[pair[1]].add(pair[0]);
        }
        Queue<Integer> toVisit = new ArrayDeque<>();
        // base course who don't have prerequisites
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {// point with indgree == 0 is start point, put it to queue so can study first;
                toVisit.offer(i);
            }
        }
        // ans array
        int[] order = new int[numCourses];
        // index of point in answer arrary
        int visited = 0;
        // have course to study, eliminate start point and its edge (indgree of other points)
        while (!toVisit.isEmpty()) {
            int from = toVisit.poll();// finished one course
            order[visited++] = from;// record in answer
            for (int to : graph[from]) {
                inDegree[to]--; // edge start from [from] should be eliminate and update indegree of [to]
                if (inDegree[to] == 0) {
                    toVisit.offer(to);// new start point, put it into queue;
                } else if (inDegree[to] < 0) {
                    return new int[0];// circle in the study pre 
                }
            }
        }
        return visited == numCourses ? order : new int[0];
    }
}
