  private Comparator<String> comp = new Comparator<String>() {
    public int compare(String a, String b) {
      int cnt1 = dict.get(a);
      int cnt2 = dict.get(b);
      if (cnt1 == cnt2) return a.compareTo(b);// if the frequence is same, order lexicographically.
      return cnt2 - cnt1;// descedent order.
    }
  };
