//My Solution: Singly linked list
class MyLinkedList {
    class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }
    Node head;
    Node tail;
    int size = 0;
    public MyLinkedList() {
    }
    
    public int get(int index) {
        Node findNode = getNode(index);
        return findNode == null? -1 : findNode.val;
    }
    private Node getNode(int index){
        Node findNode = head;
        while(index-- > 0 && findNode != null){
            findNode = findNode.next;
        }
        return findNode;
    }
    
    public void addAtHead(int val) {
        Node nNode = new Node(val);
        if(size == 0){
            head = nNode;
            tail = nNode;
        }else{
            nNode.next = head;
            head = nNode;
        }
        ++size;
    }
    
    public void addAtTail(int val) {
        if(size == 0) {
            addAtHead(val);
            return;
        }
        Node nNode = new Node(val);
        tail.next = nNode;
        tail = nNode;
        ++size;
    }
    
    public void addAtIndex(int index, int val) {
        if(index < 0 || index > size) return;
        if(index == size){
            addAtTail(val);
            return;
        }
        if(index == 0){
            addAtHead(val);
            return;
        }
        Node prevNode = getNode(index - 1);
        Node nNode = new Node(val);
        nNode.next = prevNode.next;
        prevNode.next = nNode;
        ++size;
    }
    
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size || size == 0) return;
        if(index == 0){
            head = head.next;
        }else{
            Node prevNode = getNode(index - 1);
            if(prevNode.next == tail){
                tail = prevNode;
                tail.next = null;
            }else{
                prevNode.next = prevNode.next.next;
            }
        }
        --size;
    }
}
