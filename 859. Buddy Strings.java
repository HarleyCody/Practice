______________________________________________________________________________Best Solution___________________________________________________________________________
// instead of record freq of char, use hashset to find duplicate chars when A and B are same
// If A and B not same, just record first pair that is not same and find the second pair. must exactly have two pairs being different
// once three pairs return false or at 2nd pair its not matching with first pair return false;
// Unnecessary to go through all the String and record freq
class Solution {
    public boolean buddyStrings(String A, String B) {
        int A_length = A.length();
        char Achar[] = A.toCharArray();
        if(A_length != B.length())
            return false;
        if(A.equals(B)){
            Set set1 = new HashSet();
            for(int i = 0 ; i<A_length;i++){
                if(set1.contains(Achar[i])){
                    return true;
                }else{
                    set1.add(Achar[i]);
                }
            }
            return false;
        }
        char Bchar[] = B.toCharArray();
        char A1 = 0;
        char B1 = 0;
        int j = 0;
        for(int i = 0; i < A_length && j < 3;i++){
            if(Achar[i] != Bchar[i]){
                j++;
                if(j == 1){
                    A1 = Achar[i];
                    B1 = Bchar[i];
                }
                if(j == 2 && (Achar[i] != B1 || Bchar[i] != A1)){
                    return false;
                }
            }
        }
        return j == 2;
    }
}
________________________________________________________________________________My Solution___________________________________________________________________________
// Same freq and only diff at one or zero(must be some char appears at least twice)
class Solution {
    public boolean buddyStrings(String A, String B) {
        char[] chs1 = A.toCharArray();
        char[] chs2 = B.toCharArray();
        if(chs1.length != chs2.length){
            return false;
        }
        
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        
        int diff = 0;
        for(int i = 0; i < chs1.length; ++i){
            ++freq1[chs1[i] - 'a'];
            ++freq2[chs2[i] - 'a'];
            
            if(chs1[i] != chs2[i]){
                ++diff;
            }
        }
        
        for(int i = 0; i < 26; ++i){
            if(freq1[i] != freq2[i]){
                return false;
            }
            if(freq1[i] > 1 && diff == 0){
                diff = 2;
            }
        }
        
        return diff == 2;
    }
}
