_________________________________________________________My Solution(logN)___________________________________________________________
class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        //search from bot to top
        LinkedList<Integer> ans = new LinkedList();
        int level = (int)(Math.log(label)/Math.log(2));
        //level is even l - r level is odd r - l;
        while(level >= 0){
            // min value in curlevel;
            int max = (int)Math.pow(2, level);
            // max value in curlevel
            max += max - 1;
            // addFirst makes sure the label in next level is behind lable in cur level
            ans.addFirst(label);
            // root = (max - curLabel)/2 + start of root level;
            // (max-curLabel) / 2 == index of root node in root level
            // index + start == root node
            int root = (int)Math.pow(2,--level) + (max - label) / 2;
            label = root;
        }
        return ans;
    }
}
