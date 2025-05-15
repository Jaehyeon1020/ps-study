import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int N;
    static ArrayList<Integer> apartments = new ArrayList<Integer>();
    static boolean[][] visited;

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

    static void bfs(int x, int y) {
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        ArrayDeque<Pos> q = new ArrayDeque<>();
        q.addLast(new Pos(x, y));
        visited[x][y] = true;
        int count = 1;

        while (!q.isEmpty()) {
            Pos cur = q.removeFirst();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (valid(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
                    q.addLast(new Pos(nx, ny));
                    visited[nx][ny] = true;
                    count += 1;
                }
            }
        }

        apartments.add(count);
    }

    static void bfsAll() {
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        bfsAll();

        Collections.sort(apartments);
        bw.write(apartments.size() + "\n");
        for (int house: apartments) {
            bw.write(house + "\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
