_______________________________________________________________My Solution_________________________________________________________________
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
