//My Solution: put knowledge to map and detect the key and replace it in the String
class Solution{
	public String evaluate(String s, List<List<String>> knowledge){
		Map<String, String> knowledgeMap = new HashMap();
		for(List<String> k : knowledge){
	knowledgeMap.put(k.get(0), k.get(1));
}
	StringBuilder ans = new StringBuilder();

char[] chs = s.toCharArray();
	int idx = 0;
while(idx < chs.length){
	if(chs[idx] == '('){
    ++idx;
	StringBuilder key = new StringBuilder();
	while(chs[idx] != ')'){
	key.append(chs[idx++]);
}
++idx;
if(knowledgeMap.containsKey(key.toString())){
	ans.append(knowledgeMap.get(key.toString()));
}else{
	ans.append('?');
}
}else{
	ans.append(chs[idx++]);
}
}
return ans.toString();
}
}
