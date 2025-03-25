import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] map;
    static boolean[][] visited;

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean isValidPos(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static void bfs(int x, int y) {
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        ArrayDeque<Pos> q = new ArrayDeque<>();
        q.addLast(new Pos(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Pos cur = q.removeFirst();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isValidPos(nx, ny) && !visited[nx][ny] && map[nx][ny] == map[cur.x][cur.y]) {
                    q.addLast(new Pos(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static int getAreas() {
        int cnt = 0;
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    cnt += 1;
                }
            }
        }

        return cnt;
    }

    static int getRgAreas() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'G') map[i][j] = 'R';
            }
        }

        return getAreas();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String col = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = col.charAt(j);
            }
        }

        int nonRgArea = getAreas();
        int rgArea = getRgAreas();

        bw.write("" + nonRgArea + " " + rgArea);
        bw.flush();

        br.close();
        bw.close();
    }
}
