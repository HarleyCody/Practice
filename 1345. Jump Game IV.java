________________________________________________________________________________Best Solution________________________________________________________________________
// bfs add next and i - 1 and i + 1
class Solution {
    public int minJumps(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        
        int n = arr.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n-1);
        int[] steps = new int[n];
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int nxt : map.get(arr[cur])) {
                if (nxt != cur && steps[nxt] == 0) {
                    steps[nxt] = steps[cur] + 1;
                    if (nxt == 0) return steps[nxt];
                    queue.offer(nxt);
                }
            }
            if (cur - 1 >= 0 && steps[cur - 1] == 0) {
                int nxt = cur - 1;
                steps[nxt] = steps[cur] + 1;
                if (nxt == 0) return steps[nxt];
                queue.offer(nxt);
            }
            if (cur + 1 < n && steps[cur + 1] == 0) {
                int nxt = cur + 1;
                steps[nxt] = steps[cur] + 1;
                if (nxt == 0) return steps[nxt];
                queue.offer(nxt);
            }
        }
        return steps[0];
    }
}
//加速：1. ArrayList --> LinkedList; 2. HashSet --> boolean[]; 3.
