import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        
        if (n % 2 != 0) {
            answer += 1;
            n -= 1;
        }
        
        while (n != 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
                answer += 1;
            }
        }
        
        return answer;
    }
}