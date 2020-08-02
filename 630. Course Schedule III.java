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
