_____________________________________________________________________________Trie_______________________________________________________________________________
//In add use charAt is faster than toCharArray
private void add(TrieNode root, String word) {
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(root.kids[ch - 'a'] == null) {
                root.kids[ch - 'a'] = new TrieNode();
            }
            
            root = root.kids[ch - 'a'];
        }
        
        root.word = word;
    }
    
    private String find(TrieNode node){
        String rlt = node == root ? "" : node.word;
        
        String nRlt;
        for(TrieNode n : node.kids){
            if(n == null || n.word == null) continue;
            nRlt = find(n);
            if(rlt.length() < nRlt.length()){
                rlt = nRlt;
            }
        }
        
        return rlt;
    }
______________________________________________________________________________Binary Index Tree________________________________________________________________
class BinaryIndexTree{
    int[] tree;

    public BinaryIndexTree(int n){
        tree = new int[n];
    }

    private int lowbit(int x){
        return x & (-x);
    }

    public int query(int x){
        int i = x;
        int sum = 0;
        while(0 < i){
            sum += tree[i];
            i -= lowbit(i);
        }
        return sum;
    }

    public void update(int x){
        int i = x + 1;
        while(i < tree.length){
            ++tree[i];
            i += lowbit(i);
        }
    }
}
___________________________________________________________________________________Union Found___________________________________________________________________________________
int getfather(int v)
{
    if (father[v]==v)
      return v;
    else
    {
        father[v]=getfather(father[v]);//路径压缩
        return father[v];
     }
}
//compress by rank
void Union(int x ,int y)

{
     fx = getfather(x);
     fy = getfather(y);

     if (rank[fx]>rank[fy])
        father[fy] = fx;
     else
     {
        father[fx] = fy;
        if(rank[fx]==rank[fy])
           ++rank[fy]; //重要的是祖先的rank，所以只用修改祖先的rank就可以了，子节点的rank不用管
     }
}
_____________________________________________________________Quick Sort___________________________________________________________________
Median Base:
int pivot = arr[(h + l)/2];
int low = l;
int high = h;
while(low < high){
  while(arr[low] < pivot) low++;
  while(arr[hight] > pivot) high--;
  if(low <= high) swap(arr, low++, high--);
}
quickSort(arr, l, high);
quickSort(arr, low , h);

First Base:
int pivot = arr[l];
int low = l;
int high = h;
while(low < high){
  while(arr[low] < pivot) low++;
  while(arr[hight] > pivot) high--;
  if(low < high) swap(arr, low, high);
}
arr[l] = pivot;
quickSort(arr, l, low - 1);
quickSort(arr, low , low + 1);

________________________________________________________________________Merge Sort________________________________________________________________________
Merge sort can do both sort and check relation between left and right part, eg how many numbers in right are less than num[l]
Can only concentrate on half of nums and count the num that num[l] < num[r] and update cnt[l] by += num
(image how to do this in both sorted num array)
private void mergesort(int[] nums, int start, int end){
    if(end <= start){
        return;
    }
    int mid = (start + end) / 2;
    mergesort(nums, start, mid);
    mergesort(nums, mid + 1, end);

    merge(nums, start, end);
}
private void merge(int[] nums, int start, int end){
    int mid = (start + end) / 2;
    int l = start;
    int r = mid+1;
    int rightcount = 0;    	
    int[] new_indexes = new int[end - start + 1];

    // right is in the later part, so if num[r] < num[l], it should add num that l < r;
    int sort_index = 0;
    while(l <= mid && r <= end){
        // calcualte how many nums are less than l;
        if(nums[indexes[r]] < nums[indexes[l]]){
            // place r at sort_index
            new_indexes[sort_index] = indexes[r];
            ++rightcount;
            ++r;
        }else{
            // update count to l, as nums[r] >= nums[l] means the num in right later than r are all larger than nums[l];
            // count is accumlative, count from small range to big range, as only concentrate on the other half,
            new_indexes[sort_index] = indexes[l];
            count[indexes[l]] += rightcount;
            ++l;
        }
        ++sort_index;
    }
    while(l <= mid){
        new_indexes[sort_index] = indexes[l];
        count[indexes[l]] += rightcount;
        ++l;
        ++sort_index;
    }
    while(r <= end){
        new_indexes[++sort_index] = indexes[++r];
    }
    for(int i = start; i <= end; ++i){
        indexes[i] = new_indexes[i - start];
    }
}  
__________________________________________________________________________________TreeMap Sorted by key______________________________________________________________________
Sorted by key both (logN) at get and put
but when check range it can use FloorKey and CielingKey to find closes smaller and larger value, both O(logN)
class MyCalendar {
    TreeMap<Integer, Integer> calendar;

    MyCalendar() {
        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
__________________________________________________________________________________Segment Tree______________________________________________________________________
private static class SegmentTreeNode {
    int l, r;
    int k, lazy;
    SegmentTreeNode left, right;
        
    SegmentTreeNode(int l, int r, int k) {
        this.l = l;
        this.r = r;
        this.k = k;
        this.lazy = 0;
    }
}

private int query(SegmentTreeNode node, int i, int j) {    
    if (i > j || node == null || i > node.r || j < node.l) return 0;
    
    if (i <= node.l && node.r <= j) return node.k;
   
	normalize(node);

    return Math.max(query(node.left, i, j), query(node.right, i, j));
}

private void update(SegmentTreeNode node, int i, int j, int val) {
    if (i > j || node == null || i > node.r || j < node.l) return;
    
    if (i <= node.l && node.r <= j) {
		node.k += val;
        node.lazy += val;
        return;
    }
	
    normalize(node);
	
    update(node.left, i, j, val);
    update(node.right, i, j, val);
    
    node.k = Math.max(node.left.k, node.right.k);
}
//Make node balance by the middle, balance the tree
//push the lazyness down to the child for balancing;
private void normalize(SegmentTreeNode node) {
    if (node.l < node.r) {
        if (node.left == null || node.right == null) {
            int m = node.l + (node.r - node.l) / 2;
            node.left = new SegmentTreeNode(node.l, m, node.k);
            node.right = new SegmentTreeNode(m + 1, node.r, node.k);
        
        } else if (node.lazy > 0) {
            node.left.k += node.lazy;
			node.left.lazy += node.lazy;
			
			node.right.k += node.lazy;
            node.right.lazy += node.lazy;
        }
    }
    
    node.lazy = 0;
}
