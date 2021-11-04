class Solution {
    public int minMutation(String start, String end, String[] bank) {
        char[] mutates = {'A', 'C', 'G', 'T'};
        HashSet<String> bankSet = new HashSet();
        for(String gene : bank){
            bankSet.add(gene);
        }
        if(!bankSet.contains(end)) return -1;
        
        Queue<String> q = new LinkedList<String>();
        Set<String> visited = new HashSet();
        q.offer(start);
        int steps = 0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                String cur = q.poll();
                if(cur.equals(end)) return steps;
                char[] nChs = cur.toCharArray();
                for(int i = 0; i < 8; ++i){
                    for(char c : mutates){
                        if(nChs[i] == c) continue;
                        char tmp = nChs[i];
                        nChs[i] = c;
                        String nGene = new String(nChs);
                        if(bankSet.contains(nGene) && visited.add(nGene)){
                            q.offer(nGene);
                        }
                        nChs[i] = tmp;
                    }
                }
            }
            ++steps;
        }
        return -1;
    }
}
