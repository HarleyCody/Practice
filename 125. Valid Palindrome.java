class Solution {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        char head = ' ', tail =' ';
        while(i <= j){
            head = Character.toLowerCase(s.charAt(i));
            //find valid head, if head is not in 0 - 9 || 'a' - 'z';
            while( '0' > head ||('9' < head && 'a' > head) || head > 'z' ){
                ++i;
                // if i > j, s is palindrome.
                if(i <= j)
                    head = Character.toLowerCase(s.charAt(i));
                else return true;
            }
            
            tail = Character.toLowerCase(s.charAt(j));
            //find valid tail, if tail is not in 0 - 9 || 'a' - 'z';
            while( '0' > tail ||('9' < tail && 'a' > tail) || tail > 'z' ){
                --j;
                // if i > j, s is palindrome.
                if(i <= j)
                    tail = Character.toLowerCase(s.charAt(j));
                else return true;
            }
            // find any unmactched pair, palindrome is not valid
            if(tail != head) return false;
            
            //comparing next pair
            ++i;
            --j;
        }
        return true;
    }
}
