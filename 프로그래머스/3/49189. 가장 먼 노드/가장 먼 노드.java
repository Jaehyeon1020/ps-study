import java.util.*;

class Solution {
    HashMap<Integer, ArrayList<Integer>> graph;
    boolean[] visited;
    
    int maxDistance = -1;
    int maxCount = 0;

    void bfs() {
        var q = new ArrayDeque<int[]>(); // nodeNum, node1로부터의 거리
        q.addLast(new int[]{1, 0});
        visited[1] = true;
        
        while (q.size() != 0) {
            var cur = q.removeFirst();
            var curNodeNum = cur[0];
            var curDistance = cur[1];
            
            if (curDistance > maxDistance) {
                maxDistance = curDistance;
                maxCount = 1;
            } else if (curDistance == maxDistance) {
                maxCount += 1;
            }
            
            for (var connectedNode: graph.get(curNodeNum)) {
                if (!visited[connectedNode]) {
                    q.addLast(new int[]{connectedNode, curDistance + 1});
                    visited[connectedNode] = true;
                }
            }
        }
    }
    
    public int solution(int n, int[][] edge) {
        visited = new boolean[n + 1];
        graph = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] conn: edge) {
            int src = conn[0];
            int dest = conn[1];
            
            if (!graph.containsKey(src)) {
                graph.put(src, new ArrayList<Integer>());
            }
            if (!graph.containsKey(dest)) {
                graph.put(dest, new ArrayList<Integer>());
            }
            graph.get(src).add(dest);
            graph.get(dest).add(src);
        }
        
        bfs();
        
        return maxCount;
    }
}