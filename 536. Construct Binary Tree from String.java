________________________________________________________________________________My Solution______________________________________________________________________
class Solution {
    // read one by one
    // when idx < len only ( and ) need to handle after read the num;
    // if its ( move to next;
    // if its ) need return node.
    // if idx > len just return null;
    char[] chs;
    int idx = 0, len = 0;
    public TreeNode str2tree(String s) {
        chs = s.toCharArray();
        len = chs.length;
        return build();
    }
    
    private TreeNode build(){
        if(idx >= chs.length){
            return null;
        }
        
        int sign = 1;
        if(chs[idx] == '-'){
            sign = -1;
            ++idx;
        }
        
        int num = 0;
        while(idx < len && '0' <= chs[idx] && chs[idx] <= '9'){
            num = num * 10 + chs[idx++] - '0';
        }
        TreeNode cur = new TreeNode(num * sign);
        // move to next part
        ++idx;
        if(idx >= len || chs[idx - 1] == ')'){
            return cur;
        }
        
        cur.left = build();
        
        if(idx >= len || chs[idx] == ')'){
            ++idx;
            return cur;
        }
        
        idx = chs[idx] == '(' ? ++idx : idx;
        cur.right = build();
        ++idx;
        return cur;
    }
}
