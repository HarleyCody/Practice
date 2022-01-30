//Best Solution: find the max and construct with char that has max frequency now
class Solution {
    public String frequencySort(String str) {
                if (str == null || str.isEmpty())
            return str;

        int charCount[] = new int[256];
        for (char c : str.toCharArray())
        	charCount[(int) c]++;

        char result[] = new char[str.length()];
        int i = 0;
        int max;
        int index;
        
        while (i < str.length())
        {
            max = 0;
            index = -1;
            for (int j = 0; j < charCount.length; j++)
            {
                if (max < charCount[j]) {
                    max = charCount[j];
                    index = j;
                }
            }
            //all elements are over
            if (max == 0) {
                return new String(result);
            }
            int temp = max;
            while (temp-- > 0)
            	result[i++] = (char) index;

            charCount[index] = 0; //nullify this character as it used.
        }

        return new String(result);
    }
}
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
