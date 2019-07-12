class Solution {
    Map<String, Integer> map = new HashMap<>();
    // record father of index
    private int[] iParent;
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();

        iParent = new int[accounts.size()];
        // every body poins to himself
        for(int i = 0;i<iParent.length;i++)
            iParent[i] = i;

        int j = 0;
        for(List<String> acc:accounts){
            for(int i = 1, l = acc.size();i<l;i++){
                Integer v = map.putIfAbsent(acc.get(i),j);
                //acc.get(i) and j is first appear v == null
                //acc.get(i) has appeared before; v == value previous than j(acc.get(i) father);
                if(v != null)
                    union(v,j);
            }
            // next father, next record
            j++;
        }
        ArrayList<String>[] d= new ArrayList[accounts.size()];

        for(Map.Entry<String, Integer> e:map.entrySet())
        {
            int inx = getParent(e.getValue());
            if(d[inx] == null) {
                d[inx] = new ArrayList<>();
                //d[inx].add(accounts.get(inx).get(0));
            }
            d[inx].add(e.getKey());
        }

        for (int i = 0; i < d.length; i++) {
            if(d[i] != null){
                List<String> l = new ArrayList<>();
                l.add(accounts.get(i).get(0));
                Collections.sort(d[i]);
                l.addAll(d[i]);
                res.add(l);

            }
        }

        return res;
    }
    // connect j's father to v's father
    private void union(int v, int j){
        iParent[getParent(j)] = getParent(v);
    }

    private int getParent(int j){
        int p = iParent[j];
        while(p != iParent[p]){
            p = iParent[p];
            // reduce finding for later
            iParent[j] = p;
        }
        return p;
        // while (iParent[j] != j) {
        //     j = iParent[j];
        // }
        // return j;
                
    }
}
