_______________________________________________________Best Solution___________________________________________________________
class Solution {
// use 2D array to store status
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length() != s3.length()) return false;
        
        int[][] block = new int[s1.length()][s2.length()];
        return isInterleave(s1, 0, s2, 0, s3, 0, block);
    }
    
    boolean isInterleave(String s1, int a, String s2, int b, 
                        String s3, int c, int[][] block){
        
        if(a==s1.length() && b==s2.length()){
            return true;
        }
        else if(a==s1.length()){
            return s2.substring(b).equals(s3.substring(c));
        }else if(b==s2.length()){
            return s1.substring(a).equals(s3.substring(c));
        }
         if(block[a][b]==1) return false;
        char c1 = s1.charAt(a);
        char c2 = s2.charAt(b);
        char c3 = s3.charAt(c);
        if(c1 != c3 && c2 != c3) return false;
        if(c1 != c3){
            b++; c++;
            boolean res= isInterleave(s1, a, s2, b, s3, c, block);
            if(!res){
                if(a<s1.length() && b<s2.length())block[a][b]=1; 
                return false;
            }else return true;
        }else if(c2 != c3){
            a++; c++;
            boolean res= isInterleave(s1, a, s2, b, s3, c, block);
            if(!res){
                if(a<s1.length() && b<s2.length())block[a][b]=1; 
                return false;
            }else return true;
        }else {
            c++;
             boolean res= isInterleave(s1, a+1, s2, b, s3, c, block);
             if(!res ) {
                 if(a+1<s1.length())
                 block[a+1][b]=1;
             } 
             else return true; 
              
              res =isInterleave(s1, a, s2, b+1, s3, c, block);
             if(!res) {
                 if(b+1<s2.length())
                   block[a][b+1]=1;
                 return false;
             }else return true;
              
        }
    }
}
_________________________________________________________My Solution___________________________________________________________
// recursion find solutions, if s1.char = s2.char split and record status(i + j + j)*len3 + k to be faster
class Solution {
    HashSet<Integer> visited = new HashSet();
    int len1, len2, len3;
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length())return false;
        len1 = s1.length();
        len2 = s2.length();
        len3 = s3.length();
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        return subInter(c1,c2,c3,0,0,0);
    }
    private boolean subInter(char[] s1, char[] s2, char[] s3, int i, int j, int k){
        while(k < len3 && ((i < len1 && s1[i] == s3[k]) || 
                           (j < len2 && s2[j] == s3[k]))){
            // s1 = s2;
            if(i < len1 && j < len2 && s1[i] == s2[j]){
                if(!visited.add((i + j + j) * len3 + k)){
                    return false;
                }
                return subInter(s1, s2, s3, i + 1, j, k + 1) || subInter(s1, s2, s3, i, j + 1, k + 1);
            // s1 != s2
            }else{
                if(i < len1 && s1[i] == s3[k]){
                    ++i;
                    ++k;
                }else{
                    ++j;
                    ++k;
                }
            }
        }
        if(k == len3) return true;
        return false;
    }
}
