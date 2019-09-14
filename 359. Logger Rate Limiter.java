_____________________________________________________________Best Solution__________________________________________________________________
class Logger {
    HashMap<String, Integer> recorder;
    /** Initialize your data structure here. */
    public Logger() {
        recorder = new HashMap();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        // if it is out of 10s range, true
        if(timestamp >= recorder.getOrDefault(message, 0)){
            recorder.put(message, timestamp + 10);
            return true;
        }
        return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
 ____________________________________________________________My Solution___________________________________________________________________
 class Logger {
    HashMap<String, Integer> recorder;
    /** Initialize your data structure here. */
    public Logger() {
        recorder = new HashMap();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        // it is new message or it is out of 10 s range, true
        if(!recorder.containsKey(message) || (timestamp >= recorder.get(message))){
            recorder.put(message, timestamp + 10);
            return true;
        }
        return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
