//Best Solution: travel through recorder, from middle to two sides. substract 2 * a by a
// check frequency of large half of numbers. As the small ones have been eliminate by large number
// O(n)
class Solution {
    public boolean canReorderDoubled(int[] arr) {
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int num : arr) {
            if (min > num) min = num;
            if (max < num) max = num;
        }
        
        if (min%2 !=0 && max%2 != 0)return false;
      
        int absMax = -min > max ? -min : max;
        int N = absMax*2+1;
        
        int[] count = new int[N];
        
        for (int num : arr) count[num+absMax]++;
        
        if (count[absMax]%2 != 0) return false;
        
        for (int i=1; i<=absMax/2; i++) {
            // negative
            if (count[-i+absMax] > 0) {
                if (count[-i+absMax] <= count[-i*2+absMax]) {
                    count[-i*2+absMax] -= count[-i+absMax];
                } else return false;
            }
            
            // positive
            if (count[i+absMax] > 0) {
                if (count[i+absMax] <= count[i*2+absMax]) {
                    count[i*2+absMax] -= count[i+absMax];
                } else return false;
            }
        }
        
        for (int i=absMax/2+1; i<= absMax; i++) {
            if (count[-i+absMax] != 0 || count[i+absMax] != 0) {
                return false;
            }
        }
        return true;
    }
}
//My Solution: from small to large, try to match a with a / 2, if not, then match a * 2. If not return false O(nlogn)
class Solution {
    public boolean canReorderDoubled(int[] arr) {
        int[] recorder = new int[500000];
        int min = -100000;
        Arrays.sort(arr);

        for(int a : arr){
            a -= min;
            ++recorder[a];
        }
        for(int a : arr){ 
            if(recorder[a - min] <= 0) continue;
            
            int key = 0;
            if(a % 2 == 0 && recorder[a / 2 - min] > 0){
                key = a / 2 - min;
            }else if(a * 2 - min > 0 && recorder[a * 2 - min] > 0){
                key = a * 2 - min;
            }else return false;
            
            --recorder[key];
            
            if(recorder[a - min]-- <= 0) return false;
        }
        return true;
    }
}
