/*
1671. 玩游戏
N 个人在玩游戏，每局游戏有一个裁判和 N-1 个平民玩家。给出一个数组 A, A[i] 代表玩家 i 至少需要成为平民 A[i] 次，返回最少进行游戏的次数。
Example
样例 1:

输入：A = [2, 2, 2, 2]
输出：3
解析：
A[0] = 2表示玩家0至少需要成为2次平民
第一局：玩家0担任裁判，此时 A[0] = 0, A[1] = 1, A[2] = 1, A[3] =1
第二局：玩家1担任裁判，此时 A[0] = 1, A[1] = 1, A[2] = 2, A[3] = 2
第三局：玩家2担任裁判，此时 A[0] = 2, A[1] = 2, A[2] = 2, A[3] = 3
此时每个玩家都达到了要求，所以进行三局游戏即可
样例 2:

输入：A = [84,53]
输出：137
解析：
第一局：玩家1担任裁判 ，此时 A[0] = 1, A[1] = 0
......
第三十一局：玩家1担任裁判，此时 A[0] = 31, A[1] = 0 
第三十二局：玩家0担任裁判，此时  A[1] = 31, A[1] = 1
第三十三局：玩家1担任裁判，此时  A[1] = 32, A[1] = 1
第三十四局：玩家0担任裁判，此时  A[1] = 32, A[1] = 2
......
第一百三十七局：玩家1担任裁判，此时  A[1] = 84, A[1] = 53
此时每个玩家都达到了要求，所以进行一百三十七局游戏即可
*/

Key: 当裁判次数 == 总局数，用二分逼近裁判次数 == 总局数
public class Solution {
    public long playGames(int[] A) {
        int max = 0;
        //寻找终点
        for(int a : A){
           max = Math.max(a, max);
        }
        long l = 0, r = max * 2;
        // cnt 表示裁判次数;
        while(l < r){
           long m = (l + r) / 2;
           long cnt = 0;
           // m - a == 这个人当裁判的次数（总局数 - 平民局数）
           for(int a : A){
               cnt += Math.max(m - a,0);
           }
           // 总局数大于裁判的局数，要让裁判局数增大，就要增大m
           if(m > cnt){
                l = m + 1;       
            }else{
                //同理
                r = m;      
            }
        }
        //由于l一直往小逼近 l = r. 存在 l < max的情况 1， 2， 3， 10 应该选max; 其他则选l, l >= max;
        return Math.max(l, max);
    }
}
