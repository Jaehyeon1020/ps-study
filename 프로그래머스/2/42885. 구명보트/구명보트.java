import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int first = 0;
        for (int last = people.length - 1; last >= first; last--) {
            if (people[last] + people[first] <= limit) {
                first += 1;
                answer += 1;
            } else {
                answer += 1;
            }
        }
        
        return answer;
    }
}