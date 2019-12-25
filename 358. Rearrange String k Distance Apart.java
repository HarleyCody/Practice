__________________________________________________Best Solution record freq[]___________________________________________________________
// do not need to be sorted and relation between char and num can be represented by freq directly
class Solution {

    int max = 0, secLength = 0;
    char[] recorder;
    public String rearrangeString(String s, int k) {
        if(s.length() < 2) return s;
        
        int total = s.length();
        int[] freq = new int[26];
        char[] chs = s.toCharArray();
        
        for(char c : chs){
            ++freq[c - 97];
            max = Math.max(max, freq[c - 97]);
        }
        
        StringBuilder header = new StringBuilder();
        int numOfMax = 0;
        for(int i = 0; i < 26; ++i){
            if(freq[i] == 0) continue;
            if(freq[i] == max){
                --k;
                ++numOfMax;
                header.append((char)(i + 97));
                freq[i] = 0;
            }
        }
        
        int numOfMaxChar = max * numOfMax;
        int rest = total - numOfMaxChar;
        if(rest < (max - 1) * k) return "";
        
        // set break point
        total = max + rest;
        recorder = new char[total];
        int secPos = 0, secLength = rest / (max - 1) + 1, plus1 =  rest % (max - 1);
        for(int i = 0; i < recorder.length; i += secPos <= plus1 ? secLength + 1 : secLength){
            recorder[i] = '#';
            ++secPos;
        }
        // fill rest chars
        secPos = 0; 
        int start = 1, idx = 1;
        for(int i = 0; i < 26; ++i){
            if(freq[i] == 0) continue;
            for(int j = 0; j < freq[i]; ++j){
                if(idx >= total){
                    idx = ++start;
                    secPos = 0;
                }
                recorder[idx] = (char)(i + 97);
                idx += secPos < plus1 ? secLength + 1 : secLength;
                ++secPos;  
            }
        }
        // chars to string and replace # by header
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < total; ++i){
            ans.append(recorder[i] == '#' ? header : recorder[i]);
        }
        return ans.toString();
    }
}
____________________________________________________My Solution record with Pq___________________________________________________________
// record frequnce and char and sort them.
// 1. calculate the break point(chars with max numbers, if there are multiple chars with max num, combine them to header and represented by'#')
// 2. insert rest between break point. ！！！ if rest < least required num of other chars: (k - 1)* (m - 1) return "";
// 3a. given num of Sections( max - 1) calculate length of segment, rest / (max - 1) + 1;
// 3b. caculate how many ( rest % (max - 1)) sections will have to extend 1 for extra char due to rest % (max - 1) != 0;
// 4. fill in chars with number and idx accordingly with segLength and secPos;
// 5. chars to ans; meet # append header, otherwise append recorder[i];
class Solution {
    class CharNode{
        int n;
        char c;
        public CharNode(int num, char ch){
            n = num;
            c = ch;
        }
    }
    PriorityQueue<CharNode> pq = new PriorityQueue<CharNode>((x, y) -> y.n - x.n);
    int max = 0, secLength = 0;
    char[] recorder;
    public String rearrangeString(String s, int k) {
        if(s.length() < 2) return s;
        
        int total = s.length();
        int[] freq = new int[26];
        char[] chs = s.toCharArray();
        
        for(char c : chs){
            ++freq[c - 'a'];
            max = Math.max(max, freq[c - 'a']);
        }
        
        StringBuilder header = new StringBuilder();
        int numOfMax = 0;
        for(int i = 0; i < 26; ++i){
            if(freq[i] == 0) continue;
            if(freq[i] == max){
                --k;
                ++numOfMax;
                header.append((char)(i + 'a'));
            }else{
                pq.offer(new CharNode(freq[i], (char)(i + 'a')));
            }
        }
        
        int numOfMaxChar = max * numOfMax;
        int rest = total - numOfMaxChar;;
        if(rest < (max - 1) * k) return "";
        
        total = max + rest;
        recorder = new char[total];
        int secPos = 0, secLength = rest / (max - 1) + 1, plus1 =  rest % (max - 1);
        for(int i = 0; i < recorder.length; i += secPos <= plus1 ? secLength + 1 : secLength){
            recorder[i] = '#';
            ++secPos;
        }
        secPos = 0; 
        int start = 1, idx = 1;
        while(!pq.isEmpty()){
            CharNode cur = pq.poll();
            for(int i = 0; i < cur.n; ++i){
                if(idx >= total){
                    idx = ++start;
                    secPos = 0;
                }
                recorder[idx] = cur.c;
                idx += secPos < plus1 ? secLength + 1 : secLength;
                ++secPos;
                
            }
        }
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < total; ++i){
            ans.append(recorder[i] == '#' ? header : recorder[i]);
        }
        return ans.toString();
    }
}
