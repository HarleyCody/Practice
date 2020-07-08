________________________________________________________________________________My Solution___________________________________________________________________________________
// record product at i in list(i);
// last k = recorder[cur] / recorder[cur - k - 1]
// num == 0 reset to initial
// size < k return 0;
class ProductOfNumbers {
    List<Integer> recorder;
    int pro = 1;
    int size = 0;
    public ProductOfNumbers() {
        recorder = new ArrayList();
    }
    
    public void add(int num) {
        if(num == 0){
            pro = 1;
            size = 0;
            recorder = new ArrayList();
            return;
        }
        pro *= num;
        recorder.add(pro);
        ++size;
    }
    
    public int getProduct(int k) {
        if(k > size){
            return 0;
        }
        if(k == size){
            return pro;
        }
        int preIdx = size - k - 1;
        return pro / recorder.get(preIdx);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
