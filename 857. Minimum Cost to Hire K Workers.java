________________________________________________________________________________Best Solution______________________________________________________________________
// same but encapsulate worker class and also from int to double by 1.0*Integer
// sumAll the rlt with all worker and minimize one by one, avoid pq.size() > k and pq.size() == k which is kind of time consuming
// pq record the quality of chosen worker, as cost = w/q * q, so choose min qSum to get min cost, pq is max heap
class Solution {
    class Worker{
        double ratio;
        int quality;
        int wage;
        public Worker(double r, int q, int w){
            ratio = r;
            quality = q;
            wage = w;
        }
    }
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Worker[] workers = new Worker[wage.length];
        for(int i = 0; i < quality.length; i++){
            double ratio = (1.0*wage[i])/quality[i];
            workers[i] = new Worker(ratio, quality[i], wage[i]);
            
        }
        
        Arrays.sort(workers, new Comparator<Worker>(){
            public int compare(Worker w1, Worker w2){
                if(w1.ratio < w2.ratio) return -1;
                if(w1.ratio > w2.ratio) return 1;
                return 0;
            }
        });
        int qual_sum = 0; 
        PriorityQueue<Integer> qualities = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return -1*i1.compareTo(i2);
            }
        });
        for(int i = 0; i < K; i++){
            qualities.add(workers[i].quality);
            qual_sum += workers[i].quality;
        }
        double ret = qual_sum * workers[K-1].ratio;
        for(int i = K; i < workers.length; i++){
            if(workers[i].quality < qualities.peek()){
                qual_sum -= qualities.poll();
                qualities.add(workers[i].quality);
                qual_sum += workers[i].quality;
            }
            ret = Math.min(ret, qual_sum*workers[i].ratio);
        }
        return ret;
    }
}
________________________________________________________________________________Best Solution______________________________________________________________________
//sort by expectation of workers, worker with less expectation ratio comes first.
//as the expectation is sorted in ascending order, later workers can work with previous worker
//maintain sliding window with size k and minimize the cost
//cost = sum of previous k quality * current w/q ratio.
class Solution {
    public double mincostToHireWorkers(int[] q, int[] w, int K) {
        double[][] workers = new double[q.length][2];
        for (int i = 0; i < q.length; ++i)
            workers[i] = new double[]{(double)(w[i]) / q[i], (double)q[i]};
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        double res = Double.MAX_VALUE, qsum = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (double[] worker: workers) {
            qsum += worker[1];
            pq.add(-worker[1]);
            if (pq.size() > K)qsum += pq.poll();
            // when it first arrive K is by K + 1, so cannot be put into if(pq.size() > K) even it is sliding window 
            if (pq.size() == K) res = Math.min(res, qsum * worker[0]);
        }
        return res;
    }
}
