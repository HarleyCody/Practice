________________________________________________________Best Solution__________________________________________________________
class Solution {
// record freq by array as all lowercase english letter
    int[] recorder = new int[26];
    public int numKLenSubstrNoRepeats(String S, int K) {
        int ans = 0;
        char[] chs = S.toCharArray();
        int i = 0, j = 0, size = 0;
        while(j < chs.length){
            if(recorder[chs[j] - 'a'] == 0){
                ++size;
            }
            ++recorder[chs[j] - 'a'];
            if(size == K && j - i == K - 1){
                ++ans;
                if(recorder[chs[i] - 'a'] == 1){
                    --size;
                }
                --recorder[chs[i]- 'a'];
                ++i;
            }
            if(j - i == K - 1){
                if(recorder[chs[i] - 'a'] == 1){
                    --size;
                }
                --recorder[chs[i] - 'a'];
                ++i;
            }
            ++j;
        }
        return ans;
    }
}
__________________________________________________________My Solution__________________________________________________________
// record with hashmap maintain sliding window with size K;
class Solution {
    //int[] recorder = new int[256];
    HashMap<Character, Integer> recorder = new HashMap();
    public int numKLenSubstrNoRepeats(String S, int K) {
        int ans = 0;
        char[] chs = S.toCharArray();
        int i = 0, j = 0;
        while(recorder.size() < K && j < chs.length){
            int num = recorder.getOrDefault(chs[j], 0);
            recorder.put(chs[j], num + 1);
            if(recorder.size() == K && j - i == K - 1){
                ++ans;
                num = recorder.get(chs[i]);
                if(num == 1){
                    recorder.remove(chs[i]);
                }else{
                    recorder.put(chs[i], num - 1);
                }
                ++i;
            }
            if(j - i == K - 1){
                num = recorder.get(chs[i]);
                if(num == 1){
                    recorder.remove(chs[i]);
                }else{
                    recorder.put(chs[i], num - 1);
                }
                ++i;
            }
            ++j;
        }
        return ans;
    }
}
