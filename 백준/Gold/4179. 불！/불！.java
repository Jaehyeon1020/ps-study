import java.util.*;
import java.io.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int minEscapeTime = Integer.MAX_VALUE;
    static int startX, startY;

    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    static ArrayDeque<Pos> firePosition = new ArrayDeque<>();

    static class Pos {
        int x;
        int y;
        int dist;

        public Pos(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static boolean isPosOnEdge(Pos p) {
        return p.x == 0 || p.y == 0 || p.x == R - 1 || p.y == C - 1;
    }

    static void moveFire() {
        int fires = firePosition.size();

        for (int i = 0; i < fires; i++) {
            Pos curFire = firePosition.removeFirst();

            for (int j = 0; j < 4; j++) {
                int nx = curFire.x + dx[j];
                int ny = curFire.y + dy[j];

                if (0 <= nx && nx < R && 0 <= ny && ny < C) {
                    if (map[nx][ny] == 'F' || map[nx][ny] == '#') continue;
                    map[nx][ny] = 'F';
                    firePosition.addLast(new Pos(nx, ny, 0));
                }
            }
        }
    }

    static void calculateMinTime() {
        boolean[][] visited = new boolean[R][C];
        ArrayDeque<Pos> q = new ArrayDeque<>();
        q.addLast(new Pos(startX, startY, 0));
        visited[startX][startY] = true;

        int curDist = -1;
        while (!q.isEmpty()) {
            Pos cur = q.removeFirst();

            // 가장자리까지 이동했다면 그 다음 타임에 탈출 가능 (+1)
            if (isPosOnEdge(cur)) {
//                System.out.println(cur.x);
//                System.out.println(cur.y);
                minEscapeTime = cur.dist + 1;
                return;
            }

            // dist(==분)이 바뀌었는지 확인
            // dist 바뀌었다면 불 확산
            if (curDist != cur.dist) {
                curDist = cur.dist;
                moveFire();
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < R && 0 <= ny && ny < C && !visited[nx][ny]) {
                    if (map[nx][ny] == 'F' || map[nx][ny] == '#') continue;
                    q.addLast(new Pos(nx, ny, cur.dist + 1));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String r = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = r.charAt(j);
                map[i][j] = c;

                if (c == 'J') {
                    startX = i;
                    startY = j;
                } else if (c == 'F') firePosition.addLast(new Pos(i, j, 0));
            }
        }

        calculateMinTime();

        bw.write(minEscapeTime == Integer.MAX_VALUE ? "IMPOSSIBLE" : Integer.toString(minEscapeTime));
        bw.flush();

        bw.close();
        br.close();
    }
}
