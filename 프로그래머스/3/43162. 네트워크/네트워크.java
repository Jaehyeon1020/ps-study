import java.util.*;

class Solution {
    boolean[] visited;
    
    void bfs(int[][] computers, int startComputerNum) {
        var q = new ArrayDeque<int[]>();
        q.addLast(computers[startComputerNum]);
        
        while (q.size() != 0) {
            var connections = q.removeFirst();
            for (int i = 0; i < connections.length; i++) {
                if (!visited[i] && connections[i] == 1) {
                    visited[i] = true;
                    q.addLast(computers[i]);
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[computers.length];
        for (int i = 0; i < computers.length; i++) {
            if (!visited[i]) {
                bfs(computers, i);
                answer += 1;
            }
        }
        
        return answer;
    }
}