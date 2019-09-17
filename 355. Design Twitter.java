______________________________________________________Best Solution____________________________________________________________
class Twitter {   
    HashMap<Integer, Set<Integer>> friend;
    List<Integer> messageTimeLine;
    // who post last message, <UserId>, index indicates message length;
    List<Integer> recorder;
    
    /** Initialize your data structure here. */
    public Twitter() {
        friend = new HashMap();
        messageTimeLine = new ArrayList();
        recorder = new ArrayList();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        messageTimeLine.add(tweetId);
        recorder.add(userId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ans = new ArrayList();
        int n = messageTimeLine.size() - 1;
        while(ans.size() < 10 && 0 <= n) {
            int poster = recorder.get(n);
            if(userId == poster || friend.getOrDefault(userId, new HashSet()).contains(poster)){
                ans.add(messageTimeLine.get(n));
            }
            n--;
        }
        return ans;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> f = friend.getOrDefault(followerId, new HashSet());
        f.add(followeeId);
        friend.put(followerId, f);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> f = friend.getOrDefault(followerId, new HashSet());
        f.remove((Integer)followeeId);
        friend.put(followerId, f);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
________________________________________________________My Solution____________________________________________________________
class Twitter {   
    HashMap<Integer, Set<Integer>> friend;
    // store all messages
    List<Integer> messageTimeLine;
    // who post last message, <UserId>, index indicates message length;
    List<Integer> recorder;
    
    /** Initialize your data structure here. */
    public Twitter() {
        friend = new HashMap();
        messageTimeLine = new ArrayList();
        recorder = new ArrayList();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        messageTimeLine.add(tweetId);
        recorder.add(userId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ans = new ArrayList();
        int n = messageTimeLine.size() - 1;
        while(ans.size() < 10 && 0 <= n) {
            int poster = recorder.get(n);
            if(userId == poster || friend.getOrDefault(userId, new HashSet()).contains(poster)){
                ans.add(messageTimeLine.get(n));
            }
            n--;
        }
        return ans;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> f = friend.getOrDefault(followerId, new HashSet());
        f.add(followeeId);
        friend.put(followerId, f);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> f = friend.getOrDefault(followerId, new HashSet());
        f.remove((Integer)followeeId);
        friend.put(followerId, f);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
