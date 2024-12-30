import java.util.*;

public class Solution {
    public int[] solution(int[] prices) {
        var answer = new int[prices.length];
        var deque = new ArrayDeque<int[]>();
        
        deque.push(new int[]{0, prices[0]});
        
        int time = 1;
        while (time < prices.length) {
            while (!deque.isEmpty() && prices[time] < deque.peek()[1]) {
                int[] cur = deque.pop();
                answer[cur[0]] = time - cur[0];
            }
            deque.push(new int[]{time, prices[time]});
            time++;
        }
        
        while (!deque.isEmpty()) {
            int[] cur = deque.pop();
            answer[cur[0]] = (prices.length - 1) - cur[0];
        }
        
        return answer;
    }
}
