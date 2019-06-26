________________________________________________________Best Solution___________________________________________________________
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // use to point to approach answer
        int start = 0;
        int end = numbers.length - 1;
        while(start < end){
            int s = numbers[start];
            int e = numbers[end];
            if(s + e > target){
                --end;
            }else if (s + e < target){
                ++start;
            }
            else{
                return new int[]{start + 1, end + 1};
            }
        }
        return new int[]{};
    }
}
_________________________________________________________My Solution___________________________________________________________
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        List<Integer> recorder = new ArrayList(numbers.length);
        for(int i = 0; i < numbers.length; ++i){
            int rest = target - numbers[i];
            if(recorder.contains(rest)) return new int[]{ recorder.indexOf(rest) + 1, i + 1};
            recorder.add(numbers[i]);
        }
        return new int[]{};
    }
}
