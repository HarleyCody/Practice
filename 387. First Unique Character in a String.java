_______________________________________________________________Better Solution_____________________________________________________________
class Solution {
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0){
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for(char c = 'a'; c <= 'z'; c++){// travel through alphabetics and try to find char in String
            if(s.indexOf(c) == -1){// char is not in String
                continue;
            }
            
            if(s.indexOf(c) == s.lastIndexOf(c)){// first index == last index -> appears once;
                res = Math.min(res, s.indexOf(c));// Compare with index of last char appearing once, choose smaller one -> approching tofirst one 
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
_______________________________________________________________My Solution_________________________________________________________________
class Solution {
    public int firstUniqChar(String s) {
        HashMap< Character, Integer> recorder = new LinkedHashMap();// record frequency of every char
        for(Character c : s.toCharArray()){
            recorder.put(c, recorder.getOrDefault(c, 0) + 1);
        }
        for(Character c : recorder.keySet()){// find first char with freqyency 1
            if(recorder.get(c) == 1) return s.indexOf(c);
        }
        return -1;
    }
}
