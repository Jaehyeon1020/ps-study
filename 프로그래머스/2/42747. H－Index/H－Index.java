import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int citationMax = citations[citations.length-1];
        
        while (citationMax > 0) {
            int count = 0;
            for (int c: citations) {
                if (c >= citationMax) {
                    count += 1;
                }
            }
            
            if (count >= citationMax) {
                break;
            }

            citationMax -= 1;
        }
        
        return citationMax;
    }
}