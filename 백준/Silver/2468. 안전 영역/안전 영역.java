import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] loc;

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean valid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static void bfs(int x, int y, boolean[][] flood, boolean[][] visited) {
        ArrayDeque<Pos> q = new ArrayDeque<>();
        q.addLast(new Pos(x, y));
        visited[x][y] = true;

        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        while (!q.isEmpty()) {
            Pos cur = q.removeFirst();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (valid(nx, ny) && !visited[nx][ny] && !flood[nx][ny]) {
                    q.addLast(new Pos(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    // 높이 h까지 잠길 때 안전지대 개수
    static int getSafeAreas(int h) {
        boolean[][] flood = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (loc[i][j] <= h) {
                    flood[i][j] = true;
                }
            }
        }

        int safe = 0;
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!flood[i][j] && !visited[i][j]) {
                    safe += 1;
                    bfs(i, j, flood, visited);
                }
            }
        }

        return safe;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        loc = new int[N][N];
        int maxHeight = -1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int h = Integer.parseInt(st.nextToken());
                loc[i][j] = h;
                maxHeight = Math.max(maxHeight, h);
            }
        }


        int maxArea = 0;
        for (int i = 0; i <= maxHeight; i++) {
            // i: 잠기는 높이
            maxArea = Math.max(maxArea, getSafeAreas(i));
        }

        bw.write(Integer.toString(maxArea));
        bw.flush();

        bw.close();
        br.close();
    }
}
