//sort by pivot
/*The difference between quick sort and quick select is in quickSort function.
As quick select only proceed with one of two halves. So need if else to determine which half to proceed;
*/

// right boundary should be the idx of last element(arr.length - 1);
private void quickSort(int[] arr, int l, int r){
    if(l < r){
        int mid = partition(arr,left, end);
        quickSort(l, mid - 1);
        quickSort(mid + 1, l);
        
        // for quick select
        if(target == arr[mid]){
            return mid;
        }else if(target < mid){
            return quickSort(l, mid - 1)
        }else{
            return quickSort(mid + 1, r)
        }
    }
}

private void partition(int[] arr, int l, int r){
    int pivot = arr[l];
    while(l < r){
        while(l < r && arr[r] >= pivot) --r;
        arr[l] = arr[r];
        while(l < r && arr[l] <= pivot) ++l;
        arr[r] = arr[l]
    }
    arr[l] = pivot;
    return l;
}
