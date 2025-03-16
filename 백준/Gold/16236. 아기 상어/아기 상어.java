import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;

    static int sharkSize = 2; // 아기 상어 크기
    static Pos sharkPos;
    static int leftToGrow = 2; // 커지기 위해 먹어야 하는 남은 물고기 수
    static HashSet<String> fish = new HashSet<>();

    static class Pos {
        int x;
        int y;
        int dist;

        public Pos(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public static String toKey(Pos p) {
            StringBuilder sb = new StringBuilder();
            sb.append(p.x).append(":").append(p.y);
            return sb.toString();
        }

        public static Pos keyToPos(String key) {
            StringTokenizer st = new StringTokenizer(key, ":");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            return new Pos(x, y, 0);
        }
    }

    static boolean isValidPos(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static boolean isFish(int x, int y) {
        int e = map[x][y];
        return 1 <= e && e <= 6;
    }

    static int bfs(Pos target) {
        // 현재 상어 위치에서 target까지의 최단거리
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        boolean[][] visited = new boolean[N][N];
        ArrayDeque<Pos> q = new ArrayDeque<>();
        q.addLast(sharkPos);
        visited[sharkPos.x][sharkPos.y] = true;

        while (!q.isEmpty()) {
            Pos cur = q.removeFirst();

            if (cur.x == target.x && cur.y == target.y) return cur.dist;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!isValidPos(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (isFish(nx, ny) && map[nx][ny] > sharkSize) continue;

                q.addLast(new Pos(nx, ny, cur.dist + 1));
                visited[nx][ny] = true;
            }
        }

        return -1;
    }

    static Pos getNearestFish() {
        // 현재 상어 위치와 가장 가까운 물고기 위치 반환
        Pos nearestFish = new Pos(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

        for (String fishKey: fish) {
            Pos fishPos = Pos.keyToPos(fishKey);

            if (map[fishPos.x][fishPos.y] >= sharkSize) continue;

            int sharkToFishDist = bfs(fishPos);

            if (sharkToFishDist == -1) continue;

            if (sharkToFishDist < nearestFish.dist) {
                nearestFish.x = fishPos.x;
                nearestFish.y = fishPos.y;
                nearestFish.dist = sharkToFishDist;
            } else if (sharkToFishDist == nearestFish.dist) {
                // 새로운 위치가 더 위인지 확인
                if (fishPos.x < nearestFish.x) {
                    nearestFish.x = fishPos.x;
                    nearestFish.y = fishPos.y;
                    nearestFish.dist = sharkToFishDist;
                } else if (fishPos.x > nearestFish.x) continue;

                // 높이 같으면, 새로운 위치가 더 왼쪽인지 확인
                if (fishPos.y < nearestFish.y) {
                    nearestFish.x = fishPos.x;
                    nearestFish.y = fishPos.y;
                    nearestFish.dist = sharkToFishDist;
                }
            }
        }

        return nearestFish;
    }

    static int getTimeToEatFish() {
        int t = 0;

        while (true) {
            Pos nearFish = getNearestFish();

//            System.out.println("-------");
//            System.out.println(nearFish.x);
//            System.out.println(nearFish.y);
//            System.out.println(nearFish.dist);
//            System.out.println("-----");

            if (nearFish.x == Integer.MAX_VALUE) break;

            // 상어 위치 이동
            sharkPos.x = nearFish.x;
            sharkPos.y = nearFish.y;

            // 물고기 먹기:
            // 1. 해당 칸 빈 칸으로 만들기
            // 2. fish에서 그 물고기 제거
            // 3. leftToGrow 차감 + 커질 수 있는지 확인
            map[nearFish.x][nearFish.y] = 0;
            fish.remove(Pos.toKey(nearFish));
            leftToGrow -= 1;
            if (leftToGrow == 0) {
                sharkSize += 1;
                leftToGrow = sharkSize;
            }

            // 물고기 거리만큼 시간 증가
            t += nearFish.dist;
        }


        return t;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cur = Integer.parseInt(st.nextToken());
                map[i][j] = cur;

                if (1 <= cur && cur <= 6) fish.add(Pos.toKey(new Pos(i, j, 0)));
                else if (cur == 9) sharkPos = new Pos(i, j, 0);
            }
        }

        int t = getTimeToEatFish();
        bw.write(Integer.toString(t));
        bw.flush();

        bw.close();
        br.close();
    }
}
