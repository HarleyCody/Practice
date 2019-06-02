_____________________________________________________Succint Solution______________________________________________________________________
class Solution {
public String[] reorderLogFiles(String[] logs) {
        // Override comparator is way faster than lambda expression in Arrays.sort()
        Comparator<String> myComp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int s1si = s1.indexOf(' ');
                int s2si = s2.indexOf(' ');
                char s1fc = s1.charAt(s1si+1);
                char s2fc = s2.charAt(s2si+1);
                
                if (s1fc <= '9') {
                    if (s2fc <= '9') return 0;
                    else return 1;
                }
                if (s2fc <= '9') return -1;
                
                // futher compare for Letter log. 
                int preCompute = s1.substring(++s1si).compareTo(s2.substring(++s2si)); // comparing the later part of string
                if (preCompute == 0) return s1.substring(0,s1si).compareTo(s2.substring(0,s2si));
                return preCompute;
            }
        };
        Arrays.sort(logs, myComp);
        return logs;
    }
}
_____________________________________________________________ Comdensed Solution_________________________________________________________
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> com = new Comparator<String>(){
            @Override
            public int compare(String log1, String log2){
                int space1 = log1.indexOf(" ");
                int space2 = log2.indexOf(" ");
                if(log1.charAt(++space1) <='9'){
                    if(log2.charAt(++space2) <='9')return 0;
                    else return 1;
                }
                if(log2.charAt(++space2) <='9')return -1;
                int reorderLet = log1.substring(space1).compareTo(log2.substring(space2));
                if (reorderLet == 0) return log1.substring(0,space1).compareTo(log2.substring(0,space2));
                return reorderLet;
            }
        };
        Arrays.sort(logs, com);
        return logs;
    }
}
