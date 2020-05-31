________________________________________________________________________My Solution________________________________________________________
class Solution {
    // check and append one by one
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for(char c : address.toCharArray()){
            if(c == '.'){
                sb.append("[");
                sb.append(c);
                sb.append("]");
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
_________________________________________________________________One Line Solution________________________________________________________
slower because operation is using string so every changes would make a new object to proceed
class Solution{
    public String defangIPaddr(String address){
        return address.replaceAll("\\.", "[.]");
    }
}
