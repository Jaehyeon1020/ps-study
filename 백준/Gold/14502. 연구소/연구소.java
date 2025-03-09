import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int[][] originalMap;
    static ArrayList<Pos> emptySpace = new ArrayList<>();
    static ArrayList<Pos> virus = new ArrayList<>();

    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    static class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] getCopiedMap() {
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = originalMap[i].clone();
        }

        return map;
    }


    static int getMaxSafetyZone() {
        int maxSafetyZone = 0;

        int canPutWallSize = emptySpace.size();
        for (int i = 0; i < canPutWallSize - 2; i++) {
            for (int j = i + 1; j < canPutWallSize - 1; j++) {
                for (int k = j + 1; k < canPutWallSize; k++) {
                    Pos firstWall = emptySpace.get(i);
                    Pos secondWall = emptySpace.get(j);
                    Pos thirdWall = emptySpace.get(k);

                    int[][] map = getCopiedMap();

                    map[firstWall.x][firstWall.y] = 1;
                    map[secondWall.x][secondWall.y] = 1;
                    map[thirdWall.x][thirdWall.y] = 1;

                    for (Pos v: virus) {
                        bfs(map, v);
                    }

                    int safe = 0;
                    for (int x = 0; x < N; x++) {
                        for (int y = 0; y < M; y++) {
                            if (map[x][y] == 0) safe += 1;
                        }
                    }

                    maxSafetyZone = Math.max(maxSafetyZone, safe);
                }
            }
        }
        return maxSafetyZone;
    }

    static void bfs(int[][] map, Pos start) {
        ArrayDeque<Pos> q = new ArrayDeque<>();
        q.addLast(start);

        while (!q.isEmpty()) {
            Pos cur = q.removeFirst();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] == 0) {
                    map[nx][ny] = 2;
                    q.addLast(new Pos(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        originalMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int val = Integer.parseInt(st.nextToken());
                originalMap[i][j] = val;

                if (val == 2) {
                    virus.add(new Pos(i, j));
                } else if (val == 0) {
                    emptySpace.add(new Pos(i, j));
                }
            }
        }

        int maxSafetyZone = getMaxSafetyZone();

        bw.write(Integer.toString(maxSafetyZone));
        bw.flush();

        bw.close();
        br.close();
    }
}
