import java.util.*;

class Solution {
    public String solution(String s) {
        String[] numStrings = s.split(" ");
        int[] nums = Arrays.stream(numStrings).mapToInt(numString -> Integer.parseInt(numString)).toArray();
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        
        return Integer.toString(min) + " " + Integer.toString(max);
    }
}