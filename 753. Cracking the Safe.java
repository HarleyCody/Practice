________________________________________________________________________Best Solution(DFS)______________________________________________________________________
class Solution {
    // faster as it use array instead of hashset to prun
    // set use rolling hash to record the string
    // the hash value will be the Integer.parseInt(curString);
    private int n,k,t,d;
    private boolean[] set;
    private boolean dfs(StringBuilder sb,int s){
        // t is num of perm
        if(1==t){
            return true;
        }
        set[s]=true;
        t--;
        int suf=s%d;
        for(int i=0;i<k;i++){
            int c=suf*10+i;
            if(!set[c]){
                sb.append(i);
                if(dfs(sb,c)){
                    return true;
                }
                sb.deleteCharAt(sb.length()-1);
            }
        }
        t++;
        return set[s]=false;
    }
    public String crackSafe(int n, int k) {
        this.n=n;
        this.k=k;
        d=(int)Math.pow(10,n-1);
        this.t=(int)Math.pow(k,n);
        set=new boolean[d*10];
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++)
            sb.append('0');
        dfs(sb,0);
        return sb.toString();
    }
}
_____________________________________________________________________Naive Solution(DFS)______________________________________________________________________
// do not generate perm, append char one by one with backtrack to detect if pwd is validl
// if it is then minimal has been found
class Solution {
    public String crackSafe(int n, int k) {
        // Initialize pwd to n repeated 0's as the start node of DFS.
        String strPwd = String.join("", Collections.nCopies(n, "0"));
        StringBuilder sbPwd = new StringBuilder(strPwd);
        
        Set<String> visitedComb = new HashSet<>();
        visitedComb.add(strPwd);
    
        int targetNumVisited = (int) Math.pow(k, n);
        
        crackSafeAfter(sbPwd, visitedComb, targetNumVisited, n, k);
        
        return sbPwd.toString();
    }
    
    private boolean crackSafeAfter(StringBuilder pwd, Set<String> visitedComb, int targetNumVisited, int n, int k) {
        // Base case: all n-length combinations among digits 0..k-1 are visited. 
        if (visitedComb.size() == targetNumVisited) {
            return true;
        }
        
        String lastDigits = pwd.substring(pwd.length() - n + 1); // Last n-1 digits of pwd.
        for (char ch = '0'; ch < '0' + k; ch++) {
            String newComb = lastDigits + ch;
            if (!visitedComb.contains(newComb))  {
                visitedComb.add(newComb);
                pwd.append(ch);
                if (crackSafeAfter(pwd, visitedComb, targetNumVisited, n, k)) {
                    return true;
                }
                visitedComb.remove(newComb);
                pwd.deleteCharAt(pwd.length() - 1);
            }
        }
        
        return false;
    }
}
__________________________________________________________________________My Solution(TLE)______________________________________________________________________
// generate all permuation and use one by one
class Solution {
    String[] perms;
    int permNum = 0, len = 0, range;
    String ans = null;
    
    boolean[] used;
    public String crackSafe(int n, int k) {
        if(k == 0 || n == 0){
            return "";
        }
        len = n;
        range = k + '0';
        int pLen = (int)Math.pow(k, n);
        perms = new String[pLen];
        used = new boolean[pLen];
        genPerm(0, new char[n]);
        
        for(int i = 0; i < permNum; ++i){
            used[i] = true;
            combine(1, perms[i]);
            used[i] = false;
        }
        
        return ans;
    }
    
    private void genPerm(int idx, char[] permChar){
        if(idx == len){
            //System.out.println("adding perm " + new String(permChar));
            perms[permNum++] = new String(permChar);
            return;
        }
        
        for(char c = '0'; c < range; ++c){
            //System.out.println("Generating perm " + new String(permChar));
            permChar[idx] = c;
            genPerm(idx + 1, permChar);
        }
    }
    
    private void combine(int num, String cur){
        if(num == permNum){
            //System.out.println(num + " " + permNum + " " + cur);
            if(ans == null){
                ans = cur;
            }else if(cur.length() < ans.length()){
                ans = cur;
            }
        }
        for(int i = 0; i < permNum; ++i){
            if(used[i]){
                continue;
            }
            used[i] = true;
            combine(num + 1, concate(cur, perms[i]));
            used[i] = false;
        }
    }
    
    private String concate(String og, String src){
        if(og.contains(src)){
            return og;
        }
        int com = 0;
        int oLen = og.length(), sLen = src.length();
        while(com < sLen){
            if(!og.endsWith(src.substring(0, com))){
                break;
            }
            ++com;
        }
        
        StringBuilder ans = new StringBuilder(og);
        ans.append(src.substring(com - 1));
        //System.out.println(og + " " + src + " overlaps on " + ans.toString());
        return ans.toString();
    }
    private void print(String[] arr){
        for(String a : arr){
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
