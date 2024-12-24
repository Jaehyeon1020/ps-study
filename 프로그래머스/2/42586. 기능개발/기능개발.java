import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        Deque<Integer> progressQueue = new ArrayDeque<>();
        Deque<Integer> speedQueue = new ArrayDeque<>();
        
        for (int i = 0; i < progresses.length; i++) {
            progressQueue.addLast(progresses[i]);
            speedQueue.addLast(speeds[i]);
        }
        
        int idx = 0;
        while (progressQueue.size() != 0) {
            for (int i = idx; i < progresses.length; i++) {
                progresses[i] += speeds[i];
            }
            
            int count = 0;
            while (idx < progresses.length && progresses[idx] >= 100) {
                count += 1;
                idx += 1;
                progressQueue.removeFirst();
                speedQueue.removeFirst();
            }
            
            if (count > 0) answer.add(count);
        }
        
        return answer.stream().mapToInt(n -> n).toArray();
    }
}