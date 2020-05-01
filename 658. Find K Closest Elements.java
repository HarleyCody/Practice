__________________________________________________________Best Solution_____________________________________________________________________
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int i = 0;
        int j = arr.length;
        
        //find a middle point, mid could < x or > x but closest to x; 
        while (i < j) {
            int m = i + (j-i)/2;
            if (arr[m] == x) {
                i = m;
                break;
            }
            if (arr[m] < x) i = m+1;
            else j = m;
        }

        int posl = i-k;
        int posr = i+k;
        if (posl < 0) posl = 0;
        if (posr > arr.length-1) posr = arr.length-1;
        
        // narrow down window
        while (posr - posl + 1 > k) {
            if (x - arr[posl] > arr[posr] - x) posl++;
            else posr--;
        }
        
        List<Integer> res = new ArrayList<>(k);
        
        for (int l = posl; l <= posr; l++) res.add(arr[l]);
        
        return res;
    }
}

_______________________________________________________Narrow Down window Solution_____________________________________________________________________
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        
        if(len == 0 || k <= 0) return new ArrayList();
        
        int start = 0, end = len;
        
        while(end - start > k){
            int startDis = x- arr[start];
            int endDis = arr[end - 1] - x;
            if(startDis > endDis){
                ++start;
            }else{
                --end;
            }
        }
        
        List<Integer> ans = new ArrayList();
        while(start < end){
            ans.add(arr[start++]);
        }
        return ans;
    }
}
_______________________________________________________Naive Solution_____________________________________________________________________class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        
        int len = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer e1, Integer e2){                
                int dis1 = Math.abs(e1 - x);
                int dis2 = Math.abs(e2 - x);
                
                return dis1 == dis2 ? e2 - e1 : dis2 - dis1; 
            }
        });
        
        for(int i = 0; i < len; ++i){
            pq.offer(arr[i]);
            if(pq.size() > k){
                pq.poll();
            }
        }
        
        List<Integer> ans = new ArrayList();
        while(!pq.isEmpty()){
            ans.add(pq.poll());
        }
        Collections.sort(ans);
        return ans;
    }
}
