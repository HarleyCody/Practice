import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static class Group{
        static int parent[];
        static int rank[];
        
        public Group(int n){
            parent = new int[n + 1];
            rank = new int[n + 1];
            
            for(int i = 0; i < parent.length; i++){
                parent[i] = i;
            }
        }
        public void union(int i, int j){
            int I = find(i);
            int J = find(j);
            if(I == J) return;
            if(rank[I] > rank[J]){
                parent[J] = I;
            }else if(rank[I] == rank[J]){
                parent[J] = I; 
                rank[I] += rank[J];
            }else{
                parent[I] = J;
            }
        }
        
        public int find(int i){
            if(i != parent[i]){
                return find(parent[i]);
            }
            return i;
        }
    }
    static HashMap<Integer, HashSet<Integer>> recorder = new HashMap();
    static int[] connectedCities(int n, int g, int[] og, int[] dst) {
        for(int i = g + 1; i <= n; i++){
            HashSet<Integer> adjs = new HashSet();
            for(int j = g + 1; j <= n; j++){
                if(j == i) continue;
                if(gcd(i,j) > g){
                    adjs.add(j);
                }
                recorder.put(i, adjs);
            }
        }
        Group uf = new Group(n);
        for(int i = g + 1; i <= n; i++){
            HashSet<Integer> adjs = recorder.get(i);
            if(adjs == null)continue;
            for(int adj : adjs){
                uf.union(i, adj);
            }
        }
        int[] ans = new int[og.length];
        for(int i = 0; i < og.length; i++){
            if(uf.find(og[i]) == uf.find(dst[i])){
                ans[i] = 1;
            }else{
                ans[i] = 0;
            }
        }
        return ans;
        // Complete this function
    }
    private static int gcd(int i, int j){
        if(j == 0)
            return i;
        return gcd(j, i % j);
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int g = in.nextInt();
        int originCities_cnt = in.nextInt();
        int[] originCities = new int[originCities_cnt];
        for(int originCities_i = 0; originCities_i < originCities_cnt; originCities_i++){
            originCities[originCities_i] = in.nextInt();
        }
        int destinationCities_cnt = in.nextInt();
        int[] destinationCities = new int[destinationCities_cnt];
        for(int destinationCities_i = 0; destinationCities_i < destinationCities_cnt; destinationCities_i++){
            destinationCities[destinationCities_i] = in.nextInt();
        }
        int[] res = connectedCities(n, g, originCities, destinationCities);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + (i != res.length - 1 ? "\n" : ""));
        }
        System.out.println("");


        in.close();
    }
}
