________________________________________________________________________________Best Solution (1ms faster)________________________________________________________________________________
// treat ans chars[] as stack, modify chars directly
// donot need for loop to compose every time
class CombinationIterator {
    char[] cur, dic;
    boolean hasNext;
    int n;
    public CombinationIterator(String characters, int L) {
        cur = characters.substring(0, L).toCharArray();
        dic = characters.toCharArray();
        n = dic.length;
        hasNext = true;
    }
    
    public String next() {
        if(!hasNext) return "";
        String res = new String(cur);
        int L = cur.length;
        int i = cur.length - 1, j = dic.length - 1;
        while(i >= 0 && cur[i] == dic[j]){
            i--;
            j--;
        }
        if(i == -1){
            hasNext = false;
        }else{
            int index = String.valueOf(dic).indexOf(cur[i]);
            for( int k = i; k < L; k++) {
                cur[k] = dic[++index];
            }
        }
        return res;
    }
    
    public boolean hasNext() {
        return hasNext;
    }
}
________________________________________________________________________________My Best Solution________________________________________________________________________________
// use array and index pointer instead of stack to record character postions of ans string
class CombinationIterator {
    char[] chs;
    int len, tltLen;
    int[] pointers;
    public CombinationIterator(String characters, int combinationLength) {
        chs = characters.toCharArray();
        len = combinationLength;
        tltLen = chs.length;
        
        pointers = new int[len];
        for(int i = 0; i < len; ++i){
            pointers[i] = i;
        }
    }
    
    public String next() {
        String ans = compose();
        update();
        
        return ans;
    }
    
    public boolean hasNext() {
        return pointers[len - 1] < tltLen;
    }
    
    private String compose(){ 
        StringBuilder sb = new StringBuilder();
        for(int s : pointers){
            sb.append(chs[s]);
        }
        return sb.toString();
    }
    
    private void update(){
        int last = 0, idx = len - 1;
        int offset = 1;
        while(0 <= idx && pointers[idx] == tltLen - offset){
            ++offset;
            --idx;
        }
        if(idx == -1){
            pointers[len - 1] = tltLen;
            return;
        }
        last = pointers[idx];
        while(idx < len){
            pointers[idx++] = ++last;
        }
    }
}
________________________________________________________________________________My Solution________________________________________________________________________________
// use stack to record the position of every char in a ans string, update when invoke next();
class CombinationIterator {
    char[] chs;
    int len, tltLen;
    Stack<Integer> pointers;
    public CombinationIterator(String characters, int combinationLength) {
        chs = characters.toCharArray();
        len = combinationLength;
        tltLen = chs.length;
        
        pointers = new Stack<Integer>();
        for(int i = 0; i < len; ++i){
            pointers.push(i);
        }
    }
    
    public String next() {
        String ans = compose();
        update();
        
        return ans;
    }
    
    public boolean hasNext() {
        return pointers.size() == len;
    }
    
    private String compose(){ 
        StringBuilder sb = new StringBuilder();
        for(int s : pointers){
            sb.append(chs[s]);
        }
        return sb.toString();
    }
    
    private void update(){
        int last = 0;
        int offset = 1;
        while(!pointers.isEmpty() && pointers.peek() == tltLen - offset){
            pointers.pop();
            ++offset;
        }
        if(pointers.isEmpty()){
            return;
        }
        last = pointers.pop();
        while(pointers.size() < len){
            pointers.push(++last);
        }
    }
}
