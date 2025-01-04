import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long q1Sum = Arrays.stream(queue1).sum();
        long q2Sum = Arrays.stream(queue2).sum();
        
        var q1 = new ArrayDeque<Long>();
        var q2 = new ArrayDeque<Long>();
        
        for (int i = 0; i < queue1.length; i++) {
            q1.addLast((long)queue1[i]);
            q2.addLast((long)queue2[i]);
        }
        
        var count = 0;
        while (count != queue1.length * 4) {
            if (q1Sum == q2Sum) return count;
            
            if (q1Sum > q2Sum) {
                // q1에서 pop
                var e = q1.removeFirst();
                q2.addLast(e);
                q1Sum -= e;
                q2Sum += e;
            } else {
                // q2에서 pop
                var e = q2.removeFirst();
                q1.addLast(e);
                q1Sum += e;
                q2Sum -= e;
            }
            
            count += 1;
        }
        
        return -1;
    }
}