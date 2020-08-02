________________________________________________________________________________Bestt PQ Solution________________________________________________________________________________
class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
        
        int time = 0;
        for(int[] c: courses) {
            // improved
            // if current courses is most time consuming then continue;
            if(time + c[0] <= c[1]) {
                q.offer(c[0]);
                time += c[0];
            } else if(!q.isEmpty() && q.peek() > c[0]) {
                time += c[0] - q.poll();
                q.offer(c[0]);
            }
        }
        return q.size();
    }
}
________________________________________________________________________________PQ Solution________________________________________________________________________________
class Solution {
    // nlog,
    // pq record the courses with most time consuming first order
    // add course from start to the end;
    // study recent close courses first, if time is not enough, cancel the most time consuming course
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (x, y) -> x[1] - y[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((x, y) -> y - x);
        
        int time = 0;
        for(int[] c : courses){
            time += c[0];
            pq.offer(c[0]);
            
            if(time > c[1]){
                time -= pq.poll();
            }
        }
        
        return pq.size();
    }
}
