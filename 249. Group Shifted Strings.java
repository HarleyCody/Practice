class Solution {
// transfer the string to the base type(first start with 'a')
// add string with same base type in a list
    HashMap<String, List<String>> recorder = new HashMap<String, List<String>>();
    public List<List<String>> groupStrings(String[] strings) {
        for(String s : strings){
            String shifted = s;
            if(shifted.charAt(0) != 'a'){
                shifted = shift(s);
            }
            List<String> med = recorder.getOrDefault(shifted, new ArrayList<String>());
            med.add(s);
            recorder.put(shifted, med);
        }
        List<List<String>> ans = new ArrayList();
        for(Map.Entry<String, List<String>> e : recorder.entrySet()){
            ans.add(e.getValue());
        }
        return ans;
    }
    private String shift(String str){
        int diff = str.charAt(0) - 'a';
        char[] chs = str.toCharArray();
        int len = chs.length;
        chs[0] = 'a';
        for(int i = 1; i < len; ++i){
            if(chs[i] - diff < 'a'){
                chs[i] =(char)('z' - 'a' + chs[i] - diff + 1);
            }else{
                chs[i] -= diff;
            }
        }
        return new String(chs);
    }
}
