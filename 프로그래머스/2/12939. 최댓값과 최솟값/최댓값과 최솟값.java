import java.util.*;

class Solution {
    public String solution(String s) {
        String[] numStrings = s.split(" ");
        ArrayList<Integer> nums = new ArrayList<>();
        
        for (String numStr: numStrings) {
            nums.add(Integer.parseInt(numStr));
        }
        
        int min = Collections.min(nums);
        int max = Collections.max(nums);
        
        return Integer.toString(min) + " " + Integer.toString(max);
    }
}