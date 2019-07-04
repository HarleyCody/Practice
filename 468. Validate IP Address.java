class Solution {
    public String validIPAddress(String IP) {
        int v4 = IP.indexOf('.');
        int v6 = IP.indexOf(':');
        if(v4 * v6 > 0) return "Neither";
        int v = 4;
        if(v4 < 0 && v6 > 0) v = 6;
        int len = -1; // -1 trade off last singal add into len.
        if( v == 4){
            String[] ips = IP.split("\\.");
            if(ips.length != 4) return "Neither";
            
            for(int i = 0; i < ips.length; ++i){
                if(ips[i].length() == 0) return "Neither";
                    
                char head = ips[i].charAt(0);
                // special case for begining with 0 like 01
                // special case for other invalid chars can be read by parseInt like '-'
                if( (head == '0' && ips[i].length() != 1) 
                   || head > '2' || head > '9' || head < '0') return "Neither";
                
                len += ips[i].length() + 1;
                try{
                    int val = Integer.parseInt(ips[i]);
                    if( val < 0 || val > 255) return "Neither";
                    
                }catch(NumberFormatException e){
                    return "Neither";
                }
            }
            // record len incase of there is redundant signal ensuing IP 192.1.1.1...... invalid
            if(len != IP.length()) return "Neither";
            return "IPv4";
            
        }else{// IPv6
            // contains alphabetics from 'a' to 'f'(or 'A' to 'F') numbers from '0' - '9'
            String ip = IP.toLowerCase();
            String[] ips = ip.split(":");
            
            // not for consectutive zero simplicity
            if(ips.length != 8) return "Neither";

            for(int i = 0 ; i < ips.length; ++i){
                String curSec = ips[i];
                // not consecutive zero, so it cannot be empty. it, at maximal, can has 4 chars.
                if(curSec.length() == 0 || curSec.length() > 4 ) return "Neither";
                
                len += curSec.length() + 1;
                
                for(int j = 0; j < curSec.length(); ++j){
                    char cur = curSec.charAt(j);
                    if(cur < '0' || (cur > '9' && cur < 'a')
                       || cur > 'f') 
                        return "Neither";
                }
            }
            // in case of 2001:0db8:85a3:0000:0000:8a2e:0370:7334::::::::
            if(len != IP.length()) return "Neither";
            return "IPv6";
        }
    }
}
            
            
            //for consecutive zero simplicity
//             if(ips.length != 8){
//                 int valid = 0;
//                 while(v6 > 0 ){
//                     System.out.print("V6 " + v6);
//                     int V6 = ip.indexOf(':', v6 + 1);
//                     if(V6 - v6 == 1){
//                         valid = 1;
//                         break;
//                     }
//                     v6 = V6;
//                 }
                
//                 if(valid == 0) return "Neither";
//             }
