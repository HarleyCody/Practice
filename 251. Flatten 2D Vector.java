class Vector2D {
    // recorder every valid element into list
    List<Integer> recorder = new ArrayList();
    // point to idx of next element
    private int cur = 0;
    public Vector2D(int[][] v) {
        for(int i = 0; i < v.length; ++i){
            for(int j = 0; j < v[i].length; ++j){
                    recorder.add(v[i][j]);
            }
        }
    }
    
    public int next() {
        //return next element and update pointer
        return recorder.get(cur++);
    }
    
    public boolean hasNext() {
        // idx of next element is larger than size, then its flase;
        if(cur >= recorder.size())return false;
        return true;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(v);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
