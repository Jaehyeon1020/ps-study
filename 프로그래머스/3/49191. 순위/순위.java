import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int[][] graph = new int[n + 1][n + 1]; // graph[i][j]: i가 j에게 {{이김: 1, 짐: -1, 결과 없음: 0}}
        
        for (var result: results) {
            graph[result[0]][result[1]] = 1;
            graph[result[1]][result[0]] = -1;
        }
        
        for (int m = 1; m <= n; m++) { // 경유
            for (int s = 1; s <= n; s++) { // 시작지점
                for (int e = 1; e <= n; e++) { // 도착지점
                    if (m != s && s != e && m != e) { // 같은 선수끼리 계산 X
                        // s가 m을 이기고 m이 e를 이긴 경우 => s가 e를 이김
                        if (graph[s][m] == 1 && graph[m][e] == 1) {
                            graph[s][e] = 1;
                            graph[e][s] = -1;
                        }
                            
                        // s가 m에게 지고 m이 e에게 진 경우 => s가 e에게 짐
                        if (graph[s][m] == -1 && graph[m][e] == -1) {
                            graph[s][e] = -1;
                            graph[e][s] = 1;
                        }
                    }
                }
            }
        }
        
        int answer = 0;
        // N - 1 명과의 결과가 남아있다면 순위 확정 가능
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int k = 1; k <= n; k++) {
                if (graph[i][k] != 0) count += 1;
            }
            if (count == n -1) answer += 1;
        }
        
        return answer;
    }
}