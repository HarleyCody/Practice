____________________________________________________________________Best Solution_______________________________________________________
// BFS construct the graph from both side, start and end depends on who has less node to traverse;
// DFS from end to start
class Solution {    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(endWord))
            return res;
        
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        
        begin.add(beginWord);
        end.add(endWord);
        
        Map<String, List<String>> map = new HashMap<>();
        bfs(begin, end, dict, map, false);
        
        List<String> list = new ArrayList<>();
        list.add(beginWord);
        dfs(beginWord, endWord, res, list, map);
        
        return res;
    }
    
    private void bfs(Set<String> begin, Set<String> end, Set<String> dict, Map<String, List<String>> map, boolean reverse) {
        if(begin.isEmpty()) return;
        if(begin.size() > end.size()) {
            bfs(end, begin, dict, map, !reverse);
            return;
        }
        
        boolean finish = false;
        Set<String> temp = new HashSet();
        dict.removeAll(begin);
        for(String w : begin) {
            char[] ch = w.toCharArray();
            
            for(int i = 0; i < ch.length; i++) {
                char old = ch[i];
                for(char c = 'a'; c <= 'z'; c++) {
                    ch[i] = c;
                    String newWord = new String(ch);
                    
                    if(dict.contains(newWord)) {
                        if(end.contains(newWord))
                            finish = true;
                        else
                            temp.add(newWord);
                        String key = reverse ? newWord : w;
                        String val = reverse ? w : newWord;
                        if(!map.containsKey(key)) 
                            map.put(key,new ArrayList());
                        map.get(key).add(val);
                    }
                }
                ch[i] = old;
            }
        }
        if(!finish)
            bfs(temp, end, dict, map, reverse);
    }
    
    private void dfs(String begin, String end, List<List<String>>res, List<String> list, Map< String, List<String>> map ) {
        if(begin.equals(end)) {
            res.add(new ArrayList(list));
            return;
        }
        
        if(!map.containsKey(begin))
            return;
        for(String str : map.get(begin)) {
            list.add(str);
            dfs(str, end, res, list, map);
            list.remove(list.size() - 1);
        }
    }
}
____________________________________________________________________BFS + DFS Solution_______________________________________________________
class Solution {
    // construct from back to start is faster because the graph could be A - B and C, B - D 
    // find from A to D it will check C, but find from D to A it will not check C at all
    // ArrayList for storing
    List<List<String>> res = new ArrayList<>();
    // LinkedList for manipulating
    List<String> list = new LinkedList<>();
    //Substring of word in wordList is key, value is WordList with common substring(key) 
    Map<String, List<String>> map = new HashMap<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0) return res;

        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();// if newString( composed word) is in queue;
        Set<String> unvisited = new HashSet<>(wordList);
        q.add(beginWord);
        unvisited.remove(beginWord);
        boolean found = false;

        // bfs
        while(!q.isEmpty()) {
            int size = q.size();
            for (int k = size - 1; k >= 0; --k) { // for each string in the queue
                String word = q.poll();
                for (int i = 0; i < word.length(); ++i) {// choose a position to alter string
                    char chs[] = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; ++c) {
                        chs[i] = c;// try every letter to alter string
                        String newStr = new String(chs);
                        if (unvisited.contains(newStr)) {// altered string is in wordList(candidates)
                            if (!visited.contains(newStr)) {// not in queue new string before
                                visited.add(newStr);// update string in queue, so will not duplicate use word, in case of bad circle. 
                                q.add(newStr);
                            }
                            // build adjacent graph,put substring of word into map; substring is key, value is word set has common substring(key)
                            if (map.containsKey(newStr)) map.get(newStr).add(word);
                            else {
                                List<String> l = new ArrayList<>();
                                l.add(word);
                                map.put(newStr, l);
                            }
                            if (newStr.equals(endWord)) found = true;
                        }
                    }//a-z
                }//first index-last index
            }//for each string
            if (found) break;
            unvisited.removeAll(visited);// did not found, remove visited string in unvisited set, will not check these word because this path will fail;
            visited.clear();// try to store altered string by new word in queue;
        }

        // back trace based on the adjacent graph that we have built
        backTrace(endWord, beginWord);
        return res;
    }

    private void backTrace(String cur, String start) {
        if (cur.equals(start)) {
            list.add(0, start);
            res.add(new ArrayList<String>(list));
            list.remove(0); // backtrace by one step, search for next neighbor in last layer
            return;
        }
        list.add(0, cur);//add in reverse order;
        if (map.get(cur) != null) {
            for (String s:map.get(cur)) { // for each neighbors
                backTrace(s,start);
            }
        }
        list.remove(0);
    }
}
