class Solution {
    // Idea: large digit can always be push back
    // finding the correct digits in s to match t
    // if there is no more chars available return false;
    // during the find if there is any smaller char comes before, return false;
    // use current char as tarChar, increase use freq of current char
    public boolean isTransformable(String s, String t) {
        List<Integer> []a = new ArrayList[10];
        for(int i=0;i<10;i++){
            a[i] = new ArrayList();
        }
        //store positions of all characters
        for(int i=0;i<s.length();i++){
            a[s.charAt(i) - '0'].add(i);
        }
        //first position of each character in list 'a' that has not been used for String 't'
        int []pos = new int[10];
        for(int i=0;i<t.length();i++){
            int val = t.charAt(i) - '0';
            if(a[val].size() <= pos[val]){  //no more occurrence of 'val' character available
                return false;
            }
            for(int j=0;j<val;j++){     //for every character less than 'val' 
                if(a[j].size() > pos[j]     //there exists more occurrence of character 'j'
                   && a[j].get(pos[j]) < a[val].get(pos[val])){     //character 'j' comes before 'val' in String 's'
                    return false;
                }
            }
            pos[val] ++;    //character 'val' at pos[val] in list 'a' is used for position 'i' in String 't'
        }
        return true;
    }
}
