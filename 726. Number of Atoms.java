____________________________________________________________My Solution_________________________________________________________________
class Solution {
    //use stack to store freq(ie hashmap)
    //combine prev map with cur map
    //      curVal * times + prevVal = newVal;
    //          only(also have to) traverse curMap due to times, put new val to prev, so do not need to traverse prevMap
    // cur map = prev map and continue read;
    // sort map serlize to string(ie ans)
    int idx, len;
    public String countOfAtoms(String formula) {
        idx = 0;
        
        char[] elements = formula.toCharArray();
        len = elements.length;
        
        HashMap<String, Integer> freq = new HashMap<String, Integer>();
        
        Stack<HashMap<String, Integer>> st = new Stack<HashMap<String, Integer>>();
        String name = "";
        int num = 0;
        while(idx < len){
            if(elements[idx] == '('){
                st.push((HashMap<String, Integer>)freq.clone());
                freq = new HashMap<String, Integer>();
                ++idx;
            }else if(elements[idx] == ')'){
                ++idx;
                int times = getNum(elements);
                HashMap<String, Integer> prev = st.pop();
                freq = updateMap(freq, prev, times);
            }else if('A' <= elements[idx] && elements[idx] <= 'Z'){
                name = getName(elements);
                num = getNum(elements) + freq.getOrDefault(name, 0);
                freq.put(name, num);
            }
        }

        StringBuilder ans = new StringBuilder();
        TreeMap<String, Integer> ansMap = new TreeMap(freq);
        for(Map.Entry<String, Integer> e : ansMap.entrySet()){
            ans.append(e.getKey());
            int val = e.getValue();
            if(val > 1){
                ans.append(val);
            }
        }
        return ans.toString();
    }
    
    private int getNum(char[] chs){
        int times = 0;
        while(idx < len && '0' <= chs[idx] && chs[idx] <= '9'){
            times *= 10;
            times += chs[idx++] - '0';
        }
        
        return times == 0 ? 1 : times;
    }
    
    private String getName(char[] chs){
        StringBuilder sb = new StringBuilder();
        sb.append(chs[idx++]);
        while(idx < len && 'a' <= chs[idx] && chs[idx] <= 'z'){
            sb.append(chs[idx++]);
        }
        
        return sb.toString(); 
    }
    
    private HashMap<String, Integer> updateMap(HashMap<String, Integer> freq, HashMap<String, Integer> prev, int times){
        for(Map.Entry<String,Integer> e : freq.entrySet()){
            String k = e.getKey();
            int val = times * e.getValue() + prev.getOrDefault(k, 0);
            prev.put(k, val);
        }
        return prev;
    }
}
