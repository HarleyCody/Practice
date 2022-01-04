//Best SolutionL Same idea just break if to different lines so it will return directly. Only works for return after if and donot use the variable to record size. just do list.size()
public class ZigzagIterator {
    int p1;
    int p2;
    List<Integer> v1;
    List<Integer> v2;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        p1 = 0;
        p2 = 0;
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {
        if(p2 == v2.size()){
            return v1.get(p1++);
        }
        if(p1 == v1.size()){
            return v2.get(p2++);
        }
        
        if(p1 == p2){
            return v1.get(p1++);
        }else{
            return v2.get(p2++);
        }
    }

    public boolean hasNext() {
        return p1 < v1.size() || p2 < v2.size();
    }
}

//My Solution: record position and return one by one 
public class ZigzagIterator {
    int p1;
    List<Integer> v1;
    int p2;
    List<Integer> v2;
    boolean isV1 = true;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        p1 = 0;
        this.v1 = v1;
        p2 = 0;
        this.v2 = v2;
    }

    public int next() {
        if(isV1 && p1 < v1.size() || p2 == v2.size()){
            isV1 = false;
            return v1.get(p1++);
        }else{
            isV1 = true;
            return v2.get(p2++);
        }
    }

    public boolean hasNext() {
        return p1 < v1.size() || p2 < v2.size();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
