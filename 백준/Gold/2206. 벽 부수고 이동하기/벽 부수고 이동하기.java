import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;

    static class Pos {
        int x;
        int y;
        int dist;
        int isWallDestroyed; // 0: 벽 부수지 않음, 1: 벽 부숨

        public Pos(int x, int y, int d, int isWall) {
            this.x = x;
            this.y = y;
            this.dist = d;
            this.isWallDestroyed = isWall;
        }
    }

    static int bfs() {
        // visited[x][y][m]: (x,y) 위치에 벽을 부순 채로 or 벽을 부수지 않은 채로 방문
        // visited 검사할 때 m까지 검사
        // m -> 0: 벽 부수지 않음, 1: 벽 부숨
        boolean[][][] visited = new boolean[N][M][2];

        ArrayDeque<Pos> q = new ArrayDeque<>();
        q.addLast(new Pos(0, 0, 1, 0));
        visited[0][0][0] = true;

        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        int minDistance = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Pos cur = q.removeFirst();

            if (cur.x == N - 1 && cur.y == M - 1) {
                minDistance = Math.min(minDistance, cur.dist);
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny][cur.isWallDestroyed]) {
                    if (map[nx][ny] == 1) {
                        if (cur.isWallDestroyed == 1) continue; // 이미 벽을 부순 적 있으면 건너뛰기

                        q.addLast(new Pos(nx, ny, cur.dist + 1, 1));
                        visited[nx][ny][1] = true;
                    } else {
                        q.addLast(new Pos(nx, ny, cur.dist + 1, cur.isWallDestroyed));
                        visited[nx][ny][cur.isWallDestroyed] = true;
                    }
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String col = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(Character.toString(col.charAt(j)));
            }
        }

        int minVal = bfs();

        bw.write(Integer.toString(minVal));
        bw.flush();

        br.close();
        bw.close();
    }
}
