class Solution {
    Map<String, String> father;// key: child value: parent
    Map<String, Double> weight;// key: child value: parent / child -> shows ratio of parent : child
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        father = new HashMap();
        weight = new HashMap();
        for(int i = 0; i < equations.size(); i ++){
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            father.putIfAbsent(a, a);// initiate father node of Union-Find
            weight.putIfAbsent(a, 1.0);
            father.putIfAbsent(b, b);// initiate father node of Union-Find
            weight.putIfAbsent(b, 1.0);
            union(a, b, values[i]);// combine a and b;
        }
        double[] res = new double[queries.size()];
        for(int i = 0; i < queries.size(); i ++){
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if(find(a) == null || find(b) == null || !find(a).equals(find(b))){
                res[i] = -1.0;
            } else{
                res[i] = weight.get(b) / weight.get(a); // reverse order b/a -> root/b  / root/ a == a/b
            }
        }
        return res;
    }
    
    private String find(String str){
        if(father.get(str) == null) return null;
        if(father.get(str).equals(str)) return str;
        // compress and update path as father can be reset in Union process
        weight.put(str, weight.get(str) * weight.get(father.get(str))); // a/b , b/c -> a/c = a/b * b/c original father * ogfather / newfather 
        father.put(str, find(father.get(str)));// update new connections, a/c 
        return father.get(str);
    }
    
    private void union(String a, String b, double w){
        // get root of a;
        String root_a = find(a);
        // get root of b;
        String root_b = find(b);
        // compress path, root a is father of root b -> conduction of one queries 
        if(!root_a.equals(root_b)){
            // update father
            father.put(root_b, root_a);
            // update weight between original father of b and its new father based on connection of their children
            weight.put(root_b, w * weight.get(a) / weight.get(b));
        }
    }
}
