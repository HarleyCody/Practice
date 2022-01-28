//Best Solution: [1,2,3,4,5,6] -> [1,3,5] & [2,4,6] (beautiful condition satisfied for any i from first half and j from second half)
//  [1,3,5] & [2,4,6] -> [1,5] & [3] & [2,6] & [4] ** (now (1+3)/2 was not 5 and same for (2+4)/2)**
class Solution {
    public int[] beautifulArray(int n) {
        int[] res= new int[n];
        for(int i=0;i<n;i++)
            res[i]=i+1;
        recur(0,n-1,res);
        return res;
    }
    
    void recur(int i, int j, int[] res){
        if(j-i<2) return;
        int even=(j-i+1)/2;
        int odd=even;
        if ((j-i+1)%2==1) odd++;
        int[] odarr= new int[odd];
        int[] evarr= new int[even];
        int ic=i,jc=j,vi=0;
        while(ic<=jc){
            odarr[vi]=res[ic];
            if(ic+1<=jc) evarr[vi]=res[ic+1];
            ic+=2;vi++;
        }
        for(int x=0;x<odd;x++)
            res[i+x]=odarr[x];
        for(int x=0;x<even;x++)
            res[i+odd+x]=evarr[x];
        recur(i, i+odd-1, res);
        recur(i+odd, j, res);
        return;
    }
}
