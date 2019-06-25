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
