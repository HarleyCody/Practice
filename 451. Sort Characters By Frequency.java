//My Solution: Record letter and freq together and sort by freq and construct new String
class Solution{
	int[][] freq;
	public String frequencySort(String s){
		char[] chs = s.toCharArray();
		freq = new int[128][2];
		for(char c : chs){
	freq[c][0] = c;
	++freq[c][1];
}
Arrays.sort(freq, (a, b) -> b[1] == a[1] ? a[0] - b[0] : b[1] - a[1]);
StringBuilder sb = new StringBuilder();
for(int i = 0; i < 128 && freq[i][1] != 0; ++i){
	char c = (char) freq[i][0];
	while(freq[i][1]-- > 0){
	sb.append(c);
}
}

return sb.toString();
}
}
