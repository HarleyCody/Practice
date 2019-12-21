_____________________________________________________My Solution(BFS)____________________________________________________________
// get urls for every url in queue
class Solution {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        HashSet<String> ans = new HashSet();
        Queue<String> q = new LinkedList<>();
        String hostname;
        q.offer(startUrl);
        ans.add(startUrl);
        while(!q.isEmpty()){
            String cur = q.poll();
            hostname = getDomain(startUrl);
            for(String url : htmlParser.getUrls(cur)){
                if(!ans.contains(url) && hostname.equals(getDomain(url))){
                    q.offer(url);
                    ans.add(url);
                }
            }
        }
        return new ArrayList(ans);
    }
    private String getDomain(String s){
        return s.split("\\/")[2];
    }
}
_____________________________________________________My Solution(DFS)____________________________________________________________
class Solution {
    HashSet<String> ans = new HashSet();
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String host = getDomain(startUrl);
        List<String> urls = htmlParser.getUrls(startUrl);
        ans.add(startUrl);
        for(String url : urls){
            if(!ans.contains(url) && host.equals(getDomain(url))){
                crawl(url, htmlParser);
            }
        }
        return new ArrayList(ans);
    }
    private String getDomain(String s){
        return s.split("\\/")[2];
    }
}
