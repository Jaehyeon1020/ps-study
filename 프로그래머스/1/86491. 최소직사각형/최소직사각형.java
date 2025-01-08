import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        var widthList = new ArrayList<Integer>();
        var heightList = new ArrayList<Integer>();
        
        for (var s: sizes) {
            if (s[0] > s[1]) {
                widthList.add(s[0]);
                heightList.add(s[1]);
            } else {
                widthList.add(s[1]);
                heightList.add(s[0]);
            }
        }
        
        return Collections.max(widthList) * Collections.max(heightList);
    }
}