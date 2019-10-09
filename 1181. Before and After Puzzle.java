___________________________________________________________Best Solution__________________________________________________________________

class Solution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        List<String> result = new ArrayList<String>();
        
        // record string with same header
        HashMap<String,List<Integer>> arr = new HashMap();
        
        // record all strings with same header, key header, value index of string
        for(int i=0;i < phrases.length; ++i){
            // head
            String word = null;
            
            int f = phrases[i].indexOf(' ');
            
            if(f==-1){
                word = phrases[i];
            }else{
                word = phrases[i].substring(0,f);
            }
            
            List<Integer> val = arr.get(word);
            if(val==null){
                val = new ArrayList<Integer>();
            }
            
            val.add(i);
            arr.put(word,val);
        } 
        
        // 
        for(int i=0;i<phrases.length;++i){
            // cut tail
            int f = phrases[i].lastIndexOf(' ');
            // tail == head of next string
            String word = phrases[i].substring(f + 1);
            String firstHalf = f != -1 ? phrases[i].substring(0,f + 1) : "";
            
            // list start with tail
            List<Integer> val = arr.get(word);
            
            if(val != null){
                for(int j : val){
                    if(j == i){continue;}
                    // first without tail + next string
                    result.add(firstHalf + phrases[j]);
                }
            }
        }
        
        Collections.sort(result);
        
        ArrayList<String> uniq = new ArrayList<String>();
        String prev ="";
        for(String str : result){
            // sorted list, only need to compare with prev
            if(!prev.equals(str)){
                prev=str;
                uniq.add(str);
            }
        }
        return uniq;
    }
}
__________________________________________________________My Solution_____________________________________________________________________
class Solution {
// sort phrases, only reach to phrase with valid head directly, then going to next loop
// find phrases with valid head which is same as tail.
// sort answer
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        Arrays.sort(phrases);
        List<String> ans = new ArrayList();
        for(String cur : phrases){
            String[] words = cur.split(" ");
            String tail = words[words.length - 1];
            
            int idx = 0;
            String head = phrases[idx].split(" ")[0];
            while(idx < phrases.length - 1 && !head.equals(tail)){
                head = phrases[++idx].split(" ")[0];
            }
            while(idx < phrases.length && phrases[idx].split(" ")[0].equals(tail) ){
                if(phrases[idx] == cur){
                    idx++;
                    continue;
                }
                String[] wordsNext = phrases[idx].split(" ");
                StringBuilder med = new StringBuilder(cur);
                med.append(phrases[idx].substring(wordsNext[0].length()));
                if(!ans.contains(med.toString())){
                    ans.add(med.toString());
                }
                idx++;
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
