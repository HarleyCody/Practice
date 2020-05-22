________________________________________________________________Calculate________________________________________________________________
class Solution {
// start from start date year, resolve same year problen eg 1971-02-01 1971-02-02
// date2 - date1 
    int[] daysMonth ={31,28,31,30,31,30,31,31,30,31,30,31};
    public int daysBetweenDates(String date1, String date2) {
        if(date1.compareTo(date2) > 0){
            String tmp = date1;
            date1 = date2;
            date2 = tmp;
        }
        int[] d1 = parseDate(date1);
        int[] d2 = parseDate(date2);
        
        int days = 0;
        for(int y = d1[0]; y < d2[0]; ++y){
            days += isLeapYear(y) ? 366 : 365;
        }
        days += prevDays(d2);
        days -= prevDays(d1);
        
        return days;
    }
    private int[] parseDate(String date){
        String[] dStrs = date.split("-");
        int[] ans = new int[dStrs.length];
        for(int i = 0; i < dStrs.length; ++i){
            ans[i] = Integer.valueOf(dStrs[i]);
        }
        return ans;
    }
    private boolean isLeapYear(int year){
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }
    
    private int prevDays(int[] date){
        int ans = 0;
        for(int i = 0; i < date[1] - 1; ++i){
            ans += daysMonth[i];
        }
        ans += date[2];
        if(isLeapYear(date[0]) && date[1] > 2){
            ++ans;
        }
        return ans;
    }
}
____________________________________________________________________API____________________________________________________________________
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
class Solution {
    public int daysBetweenDates(String date1, String date2) {
        LocalDate d1 = LocalDate.parse(date1);
        LocalDate d2 = LocalDate.parse(date2);
        return Math.abs((int)ChronoUnit.DAYS.between(d1, d2));
    }
}
