import java.util.*;
import java.io.*;

public class Main {
    static int N, L, R;
    static int[][] map;
    static int days = 0;

    static boolean[][] visited;
    static ArrayList<int[]> points;
    static int unions;
    static int populationSum;

    static void init() {
        points = new ArrayList<>();
        unions = 0;
        populationSum = 0;
    }

    static void bfs(int x, int y) {
        init();

        ArrayDeque<int[]> q = new ArrayDeque<>(); // x, y

        q.addLast(new int[]{x, y});
        unions += 1;
        populationSum += map[x][y];
        points.add(new int[]{x, y});
        visited[x][y] = true;

        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        while (!q.isEmpty()) {
            int[] cur = q.removeFirst();
            int curX = cur[0];
            int curY = cur[1];
            int curPopulation = map[curX][curY];

            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N && !visited[nextX][nextY]) {
                    int diff = Math.abs(curPopulation - map[nextX][nextY]);
                    if (L <= diff && diff <= R) {
                        q.addLast(new int[]{nextX, nextY});
                        unions += 1;
                        populationSum += map[nextX][nextY];
                        points.add(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }
    }

    static void movePopulation() {
        while (true) {
            boolean isPopulationMoveOccured = false;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;

                    // 두 개 이상의 나라가 연합이 되었을 때만 인구 이동 trigger
                    bfs(i, j);
                    if (unions <= 1) continue;

                    isPopulationMoveOccured = true;

                    // 인구 이동
                    int avg = populationSum / unions;
                    for (int[] pos: points) {
                        map[pos[0]][pos[1]] = avg;
                    }
                }
            }

            if (!isPopulationMoveOccured) break;
            days += 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        movePopulation();

        bw.write(Integer.toString(days));
        bw.flush();

        bw.close();
        br.close();
    }
}
