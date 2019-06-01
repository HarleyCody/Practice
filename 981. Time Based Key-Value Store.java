class TimeMap {
    HashMap<String, HashMap<Integer, String>> record;
    /** Initialize your data structure here. */
    public TimeMap() {
        record = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        // if key never appear before, new a hashmap.
        if(record.get(key) == null){
            record.put(key, new HashMap<Integer, String>()); 
        }
        // put value into hashmap of key
        record.get(key).put(timestamp,value);
    }
    
    public String get(String key, int timestamp) {
        if(record.get(key) != null){
            // select valid timestamp, apper or closest to current timestamp.
            while(record.get(key).get(timestamp) == null){
                -- timestamp;
                if(timestamp < 1)
                    return "";
            }
            return record.get(key).get(timestamp);
        }
        return null;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
