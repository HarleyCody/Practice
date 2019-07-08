_________________________________________________________Best Solution_________________________________________________________
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) return new ArrayList<Integer>();
        int[] target = new int[26];
        int size = p.length();
        for (int i = 0; i < size; i++) {
            target[p.charAt(i) - 'a']++;
        }
         
        List<Integer> result = new ArrayList<>();
        char[] arr = s.toCharArray();
        // l is start r is end
        int l = 0, r = 0;
        while (r < arr.length) {
            System.out.print(target();
            if (target[arr[r] - 'a'] > 0) {
                --target[arr[r] - 'a'];
                ++r;
            } else {// arr[r] - 'a' is not in P : recover recorder and choose a new start
                ++target[arr[l] - 'a'];
                ++l;
            }
            if (r - l == size) result.add(l);
        }
        return result;
    }
}
_________________________________________________________My Solution___________________________________________________________
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList();
        int lenS = s.length(), lenP = p.length();
        // record frequency of characters
        int[] recorder = new int[26];
        for(int i = 0; i < lenP; ++i){
            ++recorder[p.charAt(i) - 'a'];
        }
        // last character is last lenP , lenS - lenP points to previous one, so add 1
        for(int i = 0; i < lenS - lenP + 1; ++i){

            int[] rec = new int[26];
            rec = recorder.clone();
            int j = 0;
            int flag = 1;
            while(j < lenP){
                // update frequency
                if(rec[s.charAt(i + j) - 'a'] == 0){
                    flag = 0;
                    break;
                }
                --rec[s.charAt(i + j) - 'a'];
                ++j;
            }
            if(flag == 1){
                ans.add(i);
            }
        }
        return ans;
    }
}
