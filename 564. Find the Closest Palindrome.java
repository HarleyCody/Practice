_____________________________________________________________________________Best Solution____________________________________________________________________________
//Guess palindrom one by one, increase one unit and try to make valid palindrome;
class Solution {
    public String nearestPalindromic(String n) {
        Long num = Long.parseLong(n);
        Long temp = num;
        Long largeBound = num-1;
        Long smallBound = num+1;
        long unit = 1;
        for(int i = 0; i < n.length() / 2 - 1; ++i){
            unit = unit * 10;
        }
        System.out.println(unit);
        while(largeBound <= num){
            largeBound = makePalidromic(temp += unit);
        }
        while(smallBound >= num){
            smallBound = makePalidromic(temp -= unit);
        }
        return num - smallBound <= largeBound - num ? String.valueOf(smallBound) : String.valueOf(largeBound);
    }
    
    private long makePalidromic(long num){
        char[] arr = String.valueOf(num).toCharArray();
        int left = 0;
        int right = arr.length-1;
        while(left < right){
            arr[right--] = arr[left++];
        }
        return Long.parseLong(new String(arr));
    }
}
_______________________________________________________________________________My Solution____________________________________________________________________________
//find upper and lower compare then return;
//find upper, +1 from center to sides to check if its ':' which leads overflow
//find lower, if input is all zero, return all 9, -1from center to sides to check if its '/', which leads overflow
//            in the end if l = 0 and chs[l] = 0 which means all 9, so change chs[r] to 9;
class Solution {
    public String nearestPalindromic(String n) {
        long og = Long.parseLong(n);
        int len = n.length();
        if(len == 0){
            return n;
        }
        if(len == 1){
            return "" + (char)(n.charAt(0) - 1);
        }
        long[] vals = getVal(n);
        long lower = vals[0];
        long upper = vals[1];
        return og - lower > upper - og ? String.valueOf(upper) : String.valueOf(lower);
    }
    private long[] getVal(String str){
        char[] chs = str.toCharArray();
        boolean isChanged = false, isLarger = false;
        
        int l = 0, r = chs.length - 1;
        while(l < r){
            while(l < r && chs[l] == chs[r]){
                ++l;
                --r;
            }
            if(l >= r){
                break;
            }
            isChanged = true;
            isLarger = chs[l] > chs[r] ? true : false;
            chs[r] = chs[l];
        }
        long large, small;
        char cl = str.charAt(r), cr = str.charAt(l);
        if(isLarger){
            large = Long.parseLong(new String(chs));
            if(cl == cr){
                small = getSmall(chs, r, l);
            }else{
                chs[l] = chs[r] = (char)(cl - 1);
                small = chs[0] == '0' ? getSmall(chs, r, l): Long.parseLong(new String(chs));
            }
        }else{
            if(isChanged){
                small = Long.parseLong(new String(chs));
                chs[r] = chs[l] = (char)(cl + 1);
                large = Long.parseLong(new String(chs));
            }else{
                small = getSmall(chs.clone(), r, l);
                large = getLarge(chs, r, l);
            }
        }
        
        return new long[]{small, large};
    }
    
    private long getLarge(char[] chs, int l, int r){
        String ans = "";
        while(0 <= l){
            ++chs[l];
            chs[r] = chs[l];
            if(chs[l] != ':'){
                break;
            }
            chs[l] = chs[r] = '0';
            --l;
            ++r;
        }
        ans = new String(chs);
        if(l < 0){
            chs[0] = '1';
            StringBuilder sb = new StringBuilder(new String(chs));
            sb.append(1);
            ans = sb.toString();
        }
        return Long.valueOf(ans);
    }
    
    private long getSmall(char[] chs, int l, int r){
        StringBuilder sb = new StringBuilder(new String(chs));
        if(Long.valueOf(sb.toString()) == 0L){
            sb = new StringBuilder();
            for(int i = 1; i < chs.length; ++i){
                sb.append(9);
            }
        }else{
            while(0 <= l){
                --chs[l];
                chs[r] = chs[l];
                if(chs[l] != '/'){
                    break;
                }
                
                chs[l] = chs[r] = '9';
                --l;
                ++r;
            }
            if(chs[l] == '0' && l == 0){
                chs[r] = '9';
            }
            sb = new StringBuilder(new String(chs));
        }
        return Long.valueOf(sb.toString());
    }
}
