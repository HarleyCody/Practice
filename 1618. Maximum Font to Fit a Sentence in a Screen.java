//Best Solution: Resolved 1 2 TLE case by mid = (l + r + 1) / 2 return left if possible
//               Calcualte width with freqMap so donot need to go over every char, same char inqury once;
class Solution {
    public int maxFont(String text, int w, int h, int[] fonts, FontInfo fontInfo) {
        int l=0,hi=fonts.length-1;
        int[] map=new int[26];
        
        for(char c:text.toCharArray()) map[c-'a']++;
            
        while(l<hi){
            int m=(l+hi+1)/2;
            int height = fontInfo.getHeight(fonts[m]);
            int width=getWidth(fonts[m], map, fontInfo);
            if(height<=h && width<=w)
                l=m;
            else
                hi=m-1;
        }
        return getWidth(fonts[l], map, fontInfo)<=w?fonts[l]:-1;
    }
    
    private int getWidth(int f, int[] map, FontInfo fi){
        int res=0;
        for(int i=0;i<26;i++){
            if(map[i]>0){
                res+=map[i]*fi.getWidth(f,(char)(i+'a'));
            }
        }
        return res;
    }
}
//My Solution: Binary search while(l < r - 1) in case  for 1 2 pass and left = mid = 1 this cause TLE as still left = 1 right = 2.
//              try right return right if possible otherwise return left
class Solution {
    public int maxFont(String text, int w, int h, int[] fonts, FontInfo fontInfo) {
        int l = 0;
        int r = fonts.length - 1;
        char[] chs = text.toCharArray();
        while(l < r - 1){
            int mid = (l + r) / 2;
            if(canDisplay(chs, w, h, fonts[mid], fontInfo)){
                l = mid;
            }else{
                r = mid - 1;
            }
            //System.out.println(l + " " + r + " " + mid); 
        }
        return canDisplay(chs, w, h, fonts[r], fontInfo) ? fonts[r] : !canDisplay(chs, w, h, fonts[l], fontInfo) ? -1 : fonts[l];
    }
    
    private boolean canDisplay(char[] chs, int w, int h, int fontSize, FontInfo fontInfo){
        if(fontInfo.getHeight(fontSize) > h) return false;
        int width = 0;
        for(char c : chs){
            width += fontInfo.getWidth(fontSize, c);
            if(width > w){
                 return false;
            }
        }
        return true;
    }
}
