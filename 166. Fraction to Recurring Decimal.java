class Solution {
    // store if same dive and reminder occurs twice in decimal part. 
    // circle starts at first occur. end at last division of first occur
    // e.g 1 333 = 0...1 ,(decimal part) 0...1, 0...1, 3...1, 0...1, 0...1, 3...1 circle found
    public String fractionToDecimal(int n, int d) {
        
        StringBuilder ans = new StringBuilder();
        // result of "div,reminder", index.
        HashMap<String, Integer> recorder = new HashMap();
        long flag = (long) n * d;
        if(flag < 0){
            ans.append('-');
        }
        // long: in case of loss when converting Integer.MIN_VALUE to Integer.MAX_VALUE;
        long rlt = Math.abs((long) n / d);
        long reminder = Math.abs((long)n % d);
        long denominator = Math.abs((long)d);
        
        ans.append(Long.toString(rlt));
        if(reminder == 0) return ans.toString();
        // record postion of 'rlt,reminder';
        int index = 1;
        StringBuilder decimal = new StringBuilder();
        decimal.append('.');
        
        boolean repeat = false;
        // concatanation of rlt + reminder
        String med = "";
        while(reminder != 0){
            rlt = reminder * 10 / denominator;
            reminder = reminder * 10 % denominator;
            med = Long.toString(rlt) + "," + Long.toString(reminder);
            // circle found.
            if(recorder.containsKey(med)){
                repeat = true;
                break;
            }
            recorder.put(med, index++);
            decimal.append(rlt);
        }
        // found repeat, add '(' and ')' to right place
        if(repeat){
            int start = recorder.get(med);
            // +1 because of '.' in decimal part, so start from 1
            decimal.insert(start + 1, '(');
            decimal.append(')');
        }
        ans.append(decimal);
        return ans.toString();
    }
}
