//My Solution: Create file with cache to recorde the start of current num
//Compose new name if it is duplicate at system, otherwise, put it into system.
class Solution {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> cache = new HashMap();
        Set<String> recorder = new HashSet();
        for(int i = 0; i < names.length; ++i){
            String name = names[i];
            int num = cache.getOrDefault(name, 0);
            while(!recorder.add(name)){
                ++num;
                StringBuilder nName = new StringBuilder(names[i]);
                nName.append("(");
                nName.append(num);
                nName.append(")");
                name = nName.toString();
            }
            cache.put(names[i], num);
            names[i] = name;
        }
        
        return names;
    }
}
