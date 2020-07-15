  private Comparator<String> comp = new Comparator<String>() {
    public int compare(String a, String b) {
      int cnt1 = dict.get(a);
      int cnt2 = dict.get(b);
      if (cnt1 == cnt2) return a.compareTo(b);// if the frequence is same, order lexicographically.
      return cnt2 - cnt1;// descedent order.
    }
  };

____________________________________________________Comparator for HashMap(only sort entry but hashMap)_______________________________________________________________
        //Override comparator, identifer is Map.Entry<TYPE,TYPE>
        Comparator<Map.Entry<String, Integer>> sortByInter = new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2){
                
                // same value, sort by key in alphabetic order
                if(e2.getValue() == e1.getValue())return e1.getKey().compareTo(e2.getKey());
                // sort by value in descending order
                return e2.getValue() - e1.getValue();
            } 
        };
        // Collection sort should use list to sort entry;
        List<Map.Entry<String, Integer>> sorted = new ArrayList(recorder.entrySet());
        // Sort entry
        Collections.sort(sorted, sortByInter);
_______________________________________________________Comparator for Priority Queue_______________________________________________________
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[2] == b[2] ? a[1] - b[1] : b[2] - a[2];
            }
        });
________________________________________________Comparator(lambada)________________________________________________________________
Arrays.sort( arr, new Comparator<String | Integer| ...>(){
    public int compare(String new, String old){
        return old - new;// descending
    }
});
In comparator, 2nd param is old element, 1st is new element, 
if ascending( when new < old, need swap) swap only occurs when return positive, so == return new - old;
if descending( when new > old, need swap) swap only occurs when return positive, so == return old - new;
Key is getting negative result.


