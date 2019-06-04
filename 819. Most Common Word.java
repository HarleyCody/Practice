class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String ans = "";
        StringBuilder sb = new StringBuilder();
        HashSet<String> banSet = new HashSet();
        HashMap<String, Integer> find = new HashMap();
        for(String s : banned)banSet.add(s);
        
        for(int i = 0; i < paragraph.length(); ++i){
            
            //get word
            while(i < paragraph.length() && ((paragraph.charAt(i) >= 'a' && paragraph.charAt(i) <= 'z') 
                                             || (paragraph.charAt(i) >= 'A' && paragraph.charAt(i) <= 'Z'))){
                if((paragraph.charAt(i) >= 'A' && paragraph.charAt(i) <= 'Z'))
                    sb.append((char)( paragraph.charAt(i) -'A'+'a'));
                else
                    sb.append(paragraph.charAt(i));
                ++i;
            }
            if(sb.length() == 0) continue;// in case of '.'' follow by space, so "" will be record;
            
            String s = sb.toString();
            sb.setLength(0);
            if(!banSet.contains(s)){
                find.put(s,find.getOrDefault(s, 0) + 1);
                if(find.get(s) > find.getOrDefault(ans , 0)){
                    ans = s;//renew ans if its time is smaller than valid word
                }
            }
        }
        return ans;
    }
}
