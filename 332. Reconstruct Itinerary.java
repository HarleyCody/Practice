class Solution {
      
    HashMap<String, PriorityQueue<String>> flights; // pq FIFO, rank strings lexically.
    LinkedList<String> path;
    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        
        // 'from' is key 'to' is value stored in pq.
        for (List<String> ticket : tickets) {
            if (!flights.containsKey(ticket.get(0))) {
                flights.put(ticket.get(0), new PriorityQueue<String>());
                flights.get(ticket.get(0)).add(ticket.get(1));
            }else {
                flights.get(ticket.get(0)).add(ticket.get(1));
            }
        }
        dfs("JFK");
        return path;
    }
    
    public void dfs(String from) {
        PriorityQueue<String> to = flights.get(from); // destinations of 'from'
        
        // A - B B - C flights.get(C) will be null A - B, B - A, B - C, C - B inner circle to will be empty()
        while (to != null && !to.isEmpty()) {
            System.out.print(from);
            dfs(to.poll());
        }
        
        // add from to path only all the destionation in the from is polled. so to speak, no where to go. addFirst order is reversed as add.
        path.addFirst(from);// if path is ArrayList, it can be used as path.add(0,from)
    }
}
