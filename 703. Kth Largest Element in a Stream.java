________________________________________________________Best Solution_________________________________________________________
import java.util.Arrays;
class KthLargest {

    private int[] heap;
    private int size;
    
    public KthLargest(int k, int[] nums) {
        heap = new int[k];
        this.size = 0;
        Arrays.fill(heap, Integer.MIN_VALUE);

        for (int number : nums) {
            add(number);
        }
    }
    
    public int add(int value) {
        if (size < heap.length) {
            heap[size] = value;
            buildHeap(size);
            size++;
        } else {
            if(heap[0] < value) {
                heap[0] = value;
                heapify();
            }
        }
        return heap[0];
    }
    
    private void heapify() {
        int start = 0;
        int end = heap.length - 1;
        int leftChild, rightChild;

        while (start <= end) {
            leftChild = 2 * start + 1;
            rightChild = 2 * start + 2;

            if(leftChild > end) {
                break;
            }

            if(rightChild > end) {
                rightChild = leftChild;
            }

            if(heap[start] <= Math.min(heap[leftChild], heap[rightChild])) {
                break;
            }

            if(heap[leftChild] < heap[rightChild]) {
                swap(start, leftChild);
                start = leftChild;
            } else {
                swap(rightChild, start);
                start = rightChild;
            }
        }
    }

    private void buildHeap(int end) {
        int current = end;
        while (current > 0) {
            int parent = (current - 1) / 2;
            if (heap[parent] > heap[current]) {
                swap(parent, current);
                current = parent;
            } else {
                break;
            }
        }
    }


    private void swap(int i, int j) {
        heap[i] = heap[i] ^ heap[j];
        heap[j] = heap[i] ^ heap[j];
        heap[i] = heap[i] ^ heap[j];
    }
}

________________________________________________________My Solution___________________________________________________________
class KthLargest {
    PriorityQueue<Integer> stream = new PriorityQueue();
    int size;
    public KthLargest(int k, int[] nums) {
        size = k;
        for(int data : nums){
            stream.offer(data);
            if(stream.size() > k) stream.poll();
        }
    }
    
    public int add(int val) {
        stream.add(val);
        if(stream.size() > size){
            stream.poll();
        }
        return stream.peek();
    }
}
