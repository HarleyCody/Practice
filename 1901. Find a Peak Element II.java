//Best Solution: find max by following the max direction
class Solution{
    int[][] neighbours = {{-1,0},{0,1},{1,0},{-1,-1}};
    public int[] findPeakGrid(int[][] mat) {
        
        int i = 0;
        int j = 0;
        
        while(i >= 0 && i < mat.length && j >= 0 && j < mat[0].length){
            int[] result = findMaxNeighbour(i,j, mat);
            if(result[0] == i && result[1] == j){
                return new int[]{i,j};
            }else{
                i = result[0];
                j = result[1];
            }
        }
        
        return null;
    }
    
    public int[] findMaxNeighbour(int x, int y, int[][] mat){
        
        int max = mat[x][y];
        int[] result = {x,y};
        
        for(int i = 0; i < neighbours.length; i++){
            int a = x + neighbours[i][0];
            int b = y + neighbours[i][1];
            if(a >= 0 && a < mat.length && b >= 0 && b < mat[0].length){
                
                if(mat[a][b] > mat[x][y] && mat[a][b] > max){
                    max = mat[a][b];
                    result[0] = a;
                    result[1] = b;
                }
            }
        }
        return result;
    }
}

//My Solution: quick select to get the highest number for every row
class Solution{
	public int[] findPeakGrid(int[][] mat){
        int maxVal = 0;
        int maxRow = -1;
        int maxCol = -1;
        int row = mat.length;
        int col = mat[0].length;
        for(int r = 0; r < row; ++r){
            int[] ogIdx = new int[col];
            int c = quickSelect(mat[r], col - 1, ogIdx);
            if(mat[r][c] > maxVal){
                maxVal = mat[r][c];
                maxCol = ogIdx[c] - 1;
                maxRow = r;
            }
        }

        return new int[]{maxRow, maxCol};
    }

    private int quickSelect(int[] arr, int tar, int[] ogIdx){
        int l = 0;
        int r = arr.length - 1;
        //System.out.println(l + " " + r);
        while(l <= r){
            int mid = partition(l, r, arr, ogIdx);
            if(mid == tar){
                return mid;
            }else if(mid < tar){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }

        return -1;
    }

    private int partition(int l, int r, int[] arr, int[] ogIdx){
        int pivot = arr[l];
        int idx = ogIdx[l] == 0 ? l + 1 : ogIdx[l];
        while(l < r){
            while(l < r && arr[r] >= pivot){
                if(ogIdx[r] == 0){
                    ogIdx[r] = r + 1;
                }
                --r;
            }
            if(ogIdx[r] == 0){
                ogIdx[r] = r + 1;
            }
            arr[l] = arr[r];
            ogIdx[l] = ogIdx[r];
            while(l < r && arr[l] < pivot){
                if(ogIdx[l] == 0){
                    ogIdx[l] = l + 1;
                }
                ++l;
            }
            if(ogIdx[l] == 0){
                ogIdx[l] = l + 1; 
            }
            arr[r] = arr[l];
            ogIdx[r] = ogIdx[l];
        }
        arr[l] = pivot;
        ogIdx[l] = idx;
        return l;
    }
}
