class Solution {
    // record pos and votes, compare based on vote pools
    // rewrite comparator to compare
    int[][] recorder;
    public String rankTeams(String[] votes) {
        int len = votes[0].length();
        recorder  = new int[26][len];
        
        Character[] can = new Character[len];
        int idx = 0;
        for(char c : votes[0].toCharArray()){
            can[idx++] = (Character)c;
        }
        
        for(String vote : votes){
            for(int i = 0; i < len; ++i){
                ++recorder[vote.charAt(i) - 'A'][i];
            }
        }
        
        Comparator<Character> com = new Comparator<>(){
            @Override
            public int compare(Character c1, Character c2){
                for(int i = 0; i < len; ++i){
                    if(recorder[c1 - 'A'][i] < recorder[c2 - 'A'][i]){
                        return 1;
                    }else if(recorder[c1 - 'A'][i] > recorder[c2 - 'A'][i]){
                        return -1;
                    }
                }
                
                return c1 - c2;
            }
        };
        
        Arrays.sort(can, com);
        StringBuilder ans = new StringBuilder();
        
        for(Character c : can){
            ans.append((char)c);
        }
        
        return ans.toString();
    }
}
