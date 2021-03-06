class Solution {
    public int[][] kClosest(int[][] points, int K) {
    int len =  points.length, l = 0, r = len - 1;
    while(l <= r) {//K可能为r
        int mid = helper(points, l, r);//把终点给pivot
        if (mid == K) break;//如果位置为K，找到前K个点
        if (mid < K) {//太小，扩大范围，把后一个点（必定大于pivot）作为新的pivot
            l = mid + 1;
        } else {
            r = mid - 1;//太大，缩小范围，把前一个点（必定小于pivot作为新的pivot
        }
    }
    return Arrays.copyOfRange(points, 0, K);//返回前K个点
}

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l]; //第一个作为pivot
        while (l < r) { //循环执行，保证左边都小于pivot 右边都大于pivot
            while (l < r && compare(A[r], pivot) >= 0) r--;//从后往前找，找到一个小于pivot的点A[r];
            A[l] = A[r]; // 把A[r]换到前面；
            while (l < r && compare(A[l], pivot) <= 0) l++;//从后往前找，找到一个大于pivot的点A[l];
            A[r] = A[l];// 把A[l]换到后面；
        }
        A[l] = pivot;//把pivot放到l上，保证没有数据损失；
        return l;//返回pivot位置
    }
    //比较点的大小
    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
