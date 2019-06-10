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
