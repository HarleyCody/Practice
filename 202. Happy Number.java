class Solution {
    HashSet<Integer> recorder = new HashSet(); 
    public boolean isHappy(int n) {
        // only digit 1 and 7 can be convert to happy num
        if(n == 1 || n == 7) return true;
        // single digital or negative num is not happy num
        if(n <= 0 || n < 10) return false;
        // if circle merge, can not be happy
        if(recorder.contains(n)) return false;
        
        recorder.add(n);
        int newNum = 0, digit;
        while(n > 0){
            digit = n % 10;
            n /= 10;
            newNum += digit * digit;
        }
        return isHappy(newNum);
    }
}
