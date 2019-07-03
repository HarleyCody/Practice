______________________________________________________________Best Solution______________________________________________________________
class Solution {
    // 3 dots, 4 num; 0<=num<=255 with no leading zeros
    public List<String> restoreIpAddresses(String s) {
        List<String> validAddresses = new ArrayList<>();

        // use for to split string into 4 substring, check each one is valid or not, not start with 0 and < 255
        // if every substring is valid, record answer
        for (int i=1;i<s.length() && i<4;i++){ 
            if (isValid(s.substring(0,i))){
                for (int j=i+1;j<s.length() && j<i+4;j++){ 
                    if (isValid(s.substring(i,j))){
                        for (int k=j+1;k<s.length() && k<j+4;k++){ //bug
                            if (s.length()-k>3) continue; // bug String may overflow maximal value of Integer
                            if (isValid(s.substring(j,k)) && isValid(s.substring(k))){
                                StringBuilder sol=new StringBuilder();
                                sol.append(s.substring(0,i)).append(".").append(s.substring(i,j)).append(".")
                                .append(s.substring(j,k)).append(".").append(s.substring(k));
                                validAddresses.add(sol.toString());
                            }
                        }   
                    }
                }  
            }
        }
        return validAddresses;
    }
    private boolean isValid(String s){
        if (s.charAt(0)=='0' && s.length()>1) return false;
        if (Integer.parseInt(s) > 255) return false;
        return true;
    }
}
_______________________________________________________________My Solution_______________________________________________________________
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList();
        collectIP(ans, s, 0, 0, new StringBuilder(), new StringBuilder());
        return ans;
    }
    //backtrack to find ip address
    public void collectIP(List<String> ans, String s, int sec, int cur, StringBuilder ip, StringBuilder pre){
        // has constructed 3 sections
        if(sec > 3) return;
        // 
        if(cur > s.length()) return;
        
        for(int i = cur; i < s.length(); ++i){
            // append ip area in current section
            pre.append(s.charAt(i));
            int p = Integer.parseInt(pre.toString());
            // check ip in current section is valid( < 255 && cannot start with 0)
            if(p > 255 || (pre.length() > 1 && pre.charAt(0) == '0')) return;
            
            //save status before change
            StringBuilder tem = new StringBuilder(pre);
            // complete ip in current section
            if(sec < 3) pre.append(".");
            // save status before change
            StringBuilder temIP = new StringBuilder(ip);
            ip.append(pre);
            // add to answer if current ip is completed and valid
            if(sec == 3 && ip.length() == s.length() + 3){
                ans.add(ip.toString());
                return;
            }
            // pre is ip for next section
            pre = new StringBuilder();
            // construct ip in next section
            collectIP(ans, s, sec + 1, i + 1, ip, pre);
            // backtrace
            pre = tem;
            ip = temIP;
        }
        return;
    }
}
