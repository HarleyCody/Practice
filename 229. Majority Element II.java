________________________________________________________________________Best Solution_________________________________________________________________
class Solution {
    // Boyer Moore algorithm(majority == number with freq > len / 2), count majority
    // count number only in two pointers as there will be two number for answer at Maximal
    // note the majority here should be number whose freq is > n / 3 times
    // if we trade off all the can1 and can2, it requires an other 2 * n / 3 numbers which is impossible
    // impossible cause majority num > 2 * len / 3, the rest of number must be less than len / 3
    // so cannot find the other 2 * n / 3 numbers to trade off majority
    public List<Integer> majorityElement(int[] nums) {
        int len = nums.length;
        
        int can1 = 0, can2 = 1, n1 = 0, n2 = 0;
        for(int n : nums){

            if(n == can1){
                ++n1;
            }else if(n == can2){
                ++n2;
            }else if(n1 == 0){
                can1 = n;
                n1 = 1;
            }else if(n2 == 0){
                can2 = n;
                n2 = 1;
            }else{
                --n1;
                --n2;
            }
            System.out.println(can1 + " " + can2 + " " + n1 + " " + n2);
        }
        n1 = 0;
        n2 = 0;
  
        for(int n : nums){
            if(n == can1){
                ++n1;
            }else if(n == can2){
                ++n2;
            }
        }
        List<Integer> ans = new ArrayList();
        if(n1 > len / 3){
            ans.add(can1);
        }
        if(n2 > len / 3){
            ans.add(can2);
        }
        return ans;
    }
}
__________________________________________________________________________My Solution_________________________________________________________________
class Solution {
    //record freq and add ans
    HashMap<Integer, Integer> recorder = new HashMap();
    HashSet<Integer> ansSet = new HashSet();
    public List<Integer> majorityElement(int[] nums) {
        int len = nums.length;
        for(int n : nums){
            int f = recorder.getOrDefault(n, 0);
            recorder.put(n, f + 1);
            if(f + 1 > len / 3){
                ansSet.add(n);
            }
        } 
        return new ArrayList(ansSet);
    }
}
