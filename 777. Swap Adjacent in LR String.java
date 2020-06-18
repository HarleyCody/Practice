______________________________________________________________Short Solution_____________________________________________________________
class Solution {
    //only compare two char when they are both not 'X', s=l i >= j s = r i <= j in order to be valid
    public boolean canTransform(String start, String end) {
        int n = start.length(), i = 0, j = 0;
        char s[] = start.toCharArray();
        char e[] = end.toCharArray();
        while (i < n || j < n) {
            while (i < n && s[i] == 'X') i++;
            while (j < n && e[j] == 'X') j++;
            if ((i < n) ^ (j < n)) return false; //one of the string is exhausted
            if(i==n && j==n) return true;
            if (s[i] != e[j] || (s[i] == 'L' && i < j) || (s[i] == 'R' && i > j)) return false;
            i++;
            j++;
        }
        return i==n && j==n;
    }
}
______________________________________________________________My Solution(Fastest)_____________________________________________________________
class Solution {
    //Compare from left and right, two pointers, always make sure left and right are same, compare mid recursivly
    public boolean canTransform(String start, String end) {
        char[] sChars = start.toCharArray();
        char[] eChars = end.toCharArray();
        int len = sChars.length;
        return compare(sChars, eChars, 0, len - 1);
    }
    private boolean compare(char[] start, char[] end, int s, int e){
        int l = s, r = e;
        char sl = start[l];
        char el = end[l];
        
        if(sl != el){
            if(sl == 'L' || sl * el == 6232){
                return false;
            }
            char tar = sl == 'R' ? 'R' : 'L';
            if(tar == 'R'){
                while(l <= e && end[l]!= 'R'){
                    if(end[l] == 'L' || start[l] == 'L'){
                        return false;
                    }
                    ++l;
                }
                if(l > e){
                    return false;
                }
                swap(end, s, l);
            }else{
                while(l <= e && start[l] != 'L'){
                    if(end[l] == 'R' || start[l] == 'R'){
                        return false;
                    }
                    ++l;
                }
                if(l > e){
                    return false;
                }
                swap(start, s, l);
            }
        }
        char sr = start[r];
        char er = end[r];
        if(sr != er){
            if(sr == 'R' || sr * er == 6232){
                return false;
            }
            char tar = sr == 'L' ? 'L' : 'R';
            if(tar == 'R'){
                while(s <= r && start[r] != 'R'){
                    if(end[r] == 'L' || start[r] == 'L'){
                        return false;
                    }
                    --r;
                }
                if(s > r){
                    return false;
                }
                swap(start, e, r);
            }else{
                while(s <= r && end[r] != 'L'){
                    if(end[r] == 'R' || start[r] == 'R'){
                        return false;
                    }
                    --r;
                }
                if(s > r){
                    return false;
                }
                swap(end, e, r);
            }
        }
        ++l;
        --r;
        return l >= r ? true : compare(start, end, s + 1, e - 1);
    }
    
    private void swap(char[] arr1, int start, int end){
        char tmp = arr1[start];
        arr1[start] = arr1[end];
        arr1[end] = tmp;
    }
}
