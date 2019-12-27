class Solution {
    // every time go through all the list and find two string with max overlap area, combine them to gether 
    
    
    // pair record content of string and length
    class Pair{
        String str;
        int len;
        
        public Pair(String str, int len){
            this.str = str;
            this.len = len;
        }
    }
    
    public String shortestSuperstring(String[] A) {
        
        if (A == null || A.length == 0){
            return "";
        }
        
        List<String> list = new ArrayList<>(Arrays.asList(A));
        // combine two strings with max overlapp untill there is only one string left;
        while (list.size() > 1){
            int maxOverLap = 0;
            int first = 0;
            int second = 1;
            // initial: didnot calculate overlap
            String minString = list.get(first) + list.get(second);
            // start from 0 as initial did not calcualte overlap;
            for (int i = 0; i < list.size() - 1; i++){
                for (int j = i + 1; j < list.size(); j++){
                    Pair pair = findMaxOverlap(list.get(i), list.get(j));
                    if (pair.len > maxOverLap){
                        maxOverLap = pair.len;
                        minString = pair.str;
                        first = i;
                        second = j;
                    }
                }
            }
            list.remove(second);
            list.remove(first);
            list.add(minString);
        }
        return list.get(0);
    }
    
    // find max overlap of s1 + s2 or s2 + s1; return pair
    private Pair findMaxOverlap(String s1, String s2){
        int max = 0;
        String str = "";
        
        for (int i = 0; i < s1.length(); i++){
            if (s2.startsWith(s1.substring(i))){
                max = s1.length() - i;
                str = s1.substring(0, i) + s2;
                break;
            }
        }
        
        for (int i = 0; i < s2.length(); i++){
            String sub = s2.substring(i);
            if (s1.startsWith(sub) && sub.length() > max){
                max = sub.length();
                str = s2.substring(0, i) + s1;
                break;
            }
        }
        
        return new Pair(str, max);
        
    }
    
}
