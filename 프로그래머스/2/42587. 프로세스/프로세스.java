import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        var q = new LinkedList<int[]>(); // priority, index
        
        for (var i = 0; i < priorities.length; i++) {
            q.addLast(new int[]{priorities[i], i});
        }
        
        while (q.size() > 0) {
            var e = q.removeFirst();
            var priority = e[0];
            var index = e[1];
            
            var reAppend = false;
            for (var remain: q) {
                if (remain[0] > priority) {
                    reAppend = true;
                    break;
                }
            }
            
            if (reAppend) {
                q.addLast(e);
            }
            else {
                if (index == location) return answer;
                answer += 1;
            }
        }
        
        return answer;
    }
}