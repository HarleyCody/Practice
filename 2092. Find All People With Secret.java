//My Solution: union untill there is no earlier time for any of person to know the secret
class Solution {
    int[] parent;
    int[] knowTime;
    Set<Integer> ans = new HashSet();
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        parent = new int[n];
        knowTime = new int[n];
        for(int i = 0; i < n; ++i){
            parent[i] = i;
            knowTime[i] = 100001;
        }
        getMinKnowTime(meetings, 0, firstPerson);
        
        parent[firstPerson] = 0;
        knowTime[0] = 0;
        knowTime[firstPerson] = 0;
        ans.offer(0);
        ans.offer(firstPerson);
        boolean hasNew = true;
        while(hasNew){
            hasNew = false;
            for(int[] meeting : meetings){
                //if(meeting[0] != 0 && meeting[1] != 0 && meeting[1] != firstPerson && meeting[0] != firstPerson) continue;
                if(knowTime[meeting[0]] > meeting[2] && knowTime[meeting[1]] > meeting[2]) continue;
                if(knowTime[meeting[0]] > meeting[2]){
                    hasNew = true;
                    knowTime[meeting[0]] = meeting[2];
                }
                if(knowTime[meeting[1]] > meeting[2]){
                    hasNew = true;
                    knowTime[meeting[1]] = meeting[2];
                }
                int val = union(meeting[0], meeting[1]);
                if(val > 0){
                    ans.add(val);
                }
            }
        }
        return ans;
    }

    private int find(int a){
        int pa = parent[a];
        if(pa == a) return pa;
        return parent[a] = find(pa);
    }
    
    private int union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb || pa != 0 && pb != 0) return -1;
        if(pa < pb){
            parent[pb] = pa;
            return b;
        }else{
            parent[pa] = pb;
            return a;
        }
    }
}
