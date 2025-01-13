import java.util.*;

class Solution {
    int[][] gMaps;
    boolean[][] visited;
    int n;
    int m;
    
    public int bfs() {
        var targetX = n - 1;
        var targetY = m - 1;
        
        var q = new ArrayDeque<int[]>(); // x, y, distance
        q.addLast(new int[]{0, 0, 1});
        
        var dx = new int[]{0, 0, 1, -1};
        var dy = new int[]{1, -1, 0, 0};
        
        while (q.size() != 0) {
            var curPos = q.removeFirst();
            
            var curX = curPos[0];
            var curY = curPos[1];
            var curDistance = curPos[2];
            
            if (curX == targetX && curY == targetY) {
                return curDistance;
            }
            
            for (int i = 0; i < 4; i++) {
                var newX = curX + dx[i];
                var newY = curY + dy[i];
                
                if (newX < 0 || newX > targetX || newY < 0 || newY > targetY) continue;
                
                if (gMaps[newX][newY] == 1
                   && visited[newX][newY] == false) {
                    q.addLast(new int[]{newX, newY, curDistance + 1});
                    visited[newX][newY] = true;
                }
            }
        }
        
        return -1;
    }
    
    public int solution(int[][] maps) {        
        n = maps.length;
        m = maps[0].length;
        
        gMaps = maps;
        visited = new boolean[n][m];
        
        if (
            n >= 2
            && m >= 2 
            && maps[n - 2][m - 1] == 0 
            && maps[n - 1][m - 2] == 0
        ) {
            return -1;
        }
        
        return bfs();
    }
}