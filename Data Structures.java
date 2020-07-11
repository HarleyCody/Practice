______________________________________________________________________________Binary Index Tree______________________________________________________________________________
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
