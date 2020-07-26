class Solution {
    // read command and do the operation, different level have its own map to store variable
    // corner case: when command end, there might be )) and ") "
    // for ") ", move pointer to next expression, ie ++idx and ++idx
    // seperate ++idx as )) should return one by one
    int idx = 0, len;
    public int evaluate(String expression) {
        char[] input = expression.toCharArray();
        len = input.length;
        return cal(input, new HashMap<String, Integer>());
    }
    
    private int cal(char[] input, HashMap<String, Integer> map){
        ++idx;
        int rlt = 0;
        while(idx < len){
            String command = readInput(input, map);
            if("let".equals(command)){
                rlt += let(input, map);
            }else{
                rlt += opr(input, map, command);
            }
            
            if(idx >= len){
                return rlt;
            }
            
            if(input[idx] == ')'){
                ++idx;
                if(idx < len && input[idx] == ' '){
                    ++idx;
                }
                return rlt;
            }
        }
        return rlt;
    }
    
    private String readInput(char[] input, HashMap<String, Integer> map){
        StringBuffer sb = new StringBuffer();
        if(input[idx] == '('){
            return String.valueOf(cal(input, new HashMap(map)));
        }
        while(input[idx] != ' ' && input[idx] != ')'){
            sb.append(input[idx++]);
        }
        //skip to next expression
        if(input[idx] == ' '){
            ++idx;
        }
        return sb.toString();
    }
    
    private int let(char[] input, HashMap<String, Integer>map){
        String key = "", valStr = "";
        int val = 0;
        while(idx < len){
            if(input[idx] == '('){
                return cal(input, new HashMap(map));
            }
            key = readInput(input, map);
            if(idx >= len || input[idx] == ')'){
                return map.containsKey(key) ? map.get(key) : Integer.parseInt(key);
            }
            valStr = readInput(input, map);
            val = map.containsKey(valStr) ? map.get(valStr) : Integer.parseInt(valStr);
            map.put(key, val);
        }
        return 0;
    }
    
    private int opr(char[] input, HashMap<String, Integer>map, String command){
        int val1 = -1, val2 = -1;
        
        String valStr = readInput(input, map);
        val1 = map.containsKey(valStr) ? map.get(valStr) : Integer.parseInt(valStr);
        
        valStr = readInput(input, map);
        val2 = map.containsKey(valStr) ? map.get(valStr) : Integer.parseInt(valStr);

        return "add".equals(command) ? val1 + val2 : val1 * val2;
    }
}
