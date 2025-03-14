import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static ArrayDeque<Pos> tomatos = new ArrayDeque<>();

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean isValidPos(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static void after1Day() {
        ArrayDeque<Pos> nextDayTomatos = new ArrayDeque<>();
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        while (!tomatos.isEmpty()) {
            Pos cur = tomatos.removeFirst();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isValidPos(nx, ny) && map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    nextDayTomatos.addLast(new Pos(nx, ny));
                }
            }
        }

        while (!nextDayTomatos.isEmpty()) {
            tomatos.addLast(nextDayTomatos.removeFirst());
        }
    }

    static int getDate() {
        int date = -1; // 처음 토마토가 들어있는 상태에서부터 시작이므로, 계산될 일수에서 1일을 빼야 됨

        boolean isAllTomatoDone = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) isAllTomatoDone = false;
            }
        }
        if (isAllTomatoDone) return 0;

        while (!tomatos.isEmpty()) {
            after1Day();
            date += 1;
        }

        boolean isNotAllTomatoDone = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) isNotAllTomatoDone = true;
            }
        }
        if (isNotAllTomatoDone) return -1;

        return date;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int state = Integer.parseInt(st.nextToken());
                map[i][j] = state;

                if (state == 1) {
                    tomatos.addLast(new Pos(i, j));
                }
            }
        }

        int date = getDate();
        bw.write(Integer.toString(date));
        bw.flush();

        bw.close();
        br.close();
    }
}
