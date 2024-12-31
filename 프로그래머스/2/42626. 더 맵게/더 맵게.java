import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        var pq = new PriorityQueue<Integer>();
        for (int s: scoville) {
            pq.add(s);
        }
        
        while (pq.size() != 1) {
            if (pq.peek() >= K) {
                return answer;
            }
            
            pq.add(pq.poll() + (pq.poll() * 2));
            answer += 1;
        }
        
        if (pq.peek() >= K) {
            return answer;
        }
        return -1;
    }
}