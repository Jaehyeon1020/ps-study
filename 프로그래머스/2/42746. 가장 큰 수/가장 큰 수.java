import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        var strNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strNumbers[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(strNumbers, (s1, s2) -> {
            if (s1.equals(s2)) return 0;
            int case1 = Integer.parseInt(s1 + s2);
            int case2 = Integer.parseInt(s2 + s1);
            
            if (case1 > case2) return -1;
            else return 1;
        });
        
        for (var s: strNumbers) answer += s;
        
        if (answer.charAt(0) == '0') return "0";
            
        return answer;
    }
}