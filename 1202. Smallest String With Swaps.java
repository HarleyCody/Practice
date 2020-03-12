__________________________________________________________________Best Solution_______________________________________________________
class Solution {
    private int[] father;

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        // 1: init father array for union find
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }

        // 2: union nodes with edges in between 
        for (List<Integer> pair : pairs) {
            int x = pair.get(0);
            int y = pair.get(1);
            if (findFather(x) != findFather(y)) {
                union(x, y);
            }
            /* */
        }

        // 3: group connected components in graph
        List<Integer>[] groups = new ArrayList[n];
        // from 0 - n so the grouplist is in ascending order
        for (int i = 0; i < n; i++) {
            int id = findFather(i);
            if (groups[id] == null) {
                groups[id] = new ArrayList<>();
            }
            groups[id].add(i);
        }

        // 4: sort each group, put the elements back to string     
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            List<Integer> comp = groups[i];
            if (comp == null) continue;
            char[] sorted = new char[comp.size()];
            for (int j = 0; j < comp.size(); j++) {
                sorted[j] = s.charAt(comp.get(j));
            }
            Arrays.sort(sorted);
            for (int j = 0; j < comp.size(); j++) {
                res[comp.get(j)] = sorted[j];
            }
        }
        
        return new String(res);
    }

    private int findFather (int x) {
        if (father[x] != x) {
            father[x] = findFather(father[x]);
        }
        return father[x];
    }

    private void union (int x, int y) {
        x = father[x];
        y = father[y];
        if (x < y) {
            father[y] = findFather(x);
        } else {
            father[x] = findFather(y);
        }
    }
}
__________________________________________________________________General Solution_______________________________________________________
class Solution {
    // union find
    // union relations. parent is key priority record minial char in this union 
    // find from parent get minial char and append
    private int[] parent;
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s == null || s.length() == 0) {
            return null;
        }
        parent = new int[s.length()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        
        for (List<Integer> pair : pairs) {
            union(pair.get(0), pair.get(1));
        }
        
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            int root = find(i);
            map.putIfAbsent(root, new PriorityQueue<>());
            map.get(root).offer(sChar[i]);
        }
        // insert in i with minial char in the unions
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sChar.length; i++) {
            sb.append(map.get(find(i)).poll());
        }
        return sb.toString();
    }
    private int find(int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }
    private void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }
}
