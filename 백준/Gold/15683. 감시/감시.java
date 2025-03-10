import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static ArrayList<Cctv> cctvs = new ArrayList<>();
    static int minDeadArea = Integer.MAX_VALUE;

    static class Cctv {
        int x;
        int y;
        int type;

        public Cctv(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    static int[][] getCopiedMap() {
        int[][] copiedMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            copiedMap[i] = map[i].clone();
        }

        return copiedMap;
    }

    static void up(int[][] map, Cctv cctv) {
        for (int x = cctv.x - 1; x >= 0; x--) {
            int cur = map[x][cctv.y];

            if (cur == 0) map[x][cctv.y] = -1;
            else if (cur == 6) break;
            else if (1 <= cur && cur <= 5) continue;
        }
    }

    static void down(int[][] map, Cctv cctv) {
        for (int x = cctv.x + 1; x < N; x++) {
            int cur = map[x][cctv.y];

            if (cur == 0) map[x][cctv.y] = -1;
            else if (cur == 6) break;
            else if (1 <= cur && cur <= 5) continue;
        }
    }

    static void left(int[][] map, Cctv cctv) {
        for (int y = cctv.y - 1; y >= 0; y--) {
            int cur = map[cctv.x][y];

            if (cur == 0) map[cctv.x][y] = -1;
            else if (cur == 6) break;
            else if (1 <= cur && cur <= 5) continue;
        }
    }

    static void right(int[][] map, Cctv cctv) {
        for (int y = cctv.y + 1; y < M; y++) {
            int cur = map[cctv.x][y];

            if (cur == 0) map[cctv.x][y] = -1;
            else if (cur == 6) break;
            else if (1 <= cur && cur <= 5) continue;
        }
    }

    static void putType1(int[][] map, Cctv cctv, int direction) {
        // 상 하 좌 우
        switch (direction) {
            case 0:
                up(map, cctv);
                break;
            case 1:
                down(map, cctv);
                break;
            case 2:
                left(map, cctv);
                break;
            case 3:
                right(map, cctv);
                break;
        }
    }

    static void putType2(int[][] map, Cctv cctv, int direction) {
        // 상/하, 좌/우
        switch (direction) {
            case 0:
            case 1:
                up(map, cctv);
                down(map, cctv);
                break;
            case 2:
            case 3:
                left(map, cctv);
                right(map, cctv);
                break;
        }
    }

    static void putType3(int[][] map, Cctv cctv, int direction) {
        // 상 하 좌 우
        switch (direction) {
            case 0:
                up(map, cctv);
                right(map, cctv);
                break;
            case 1:
                down(map, cctv);
                right(map, cctv);
                break;
            case 2:
                left(map, cctv);
                down(map, cctv);
                break;
            case 3:
                left(map, cctv);
                up(map, cctv);
                break;
        }
    }

    static void putType4(int[][] map, Cctv cctv, int direction) {
        // 상 하 좌 우
        switch (direction) {
            case 0:
                up(map, cctv);
                left(map, cctv);
                right(map, cctv);
                break;
            case 1:
                up(map, cctv);
                right(map, cctv);
                down(map, cctv);
                break;
            case 2:
                left(map, cctv);
                down(map, cctv);
                right(map, cctv);
                break;
            case 3:
                up(map, cctv);
                left(map, cctv);
                down(map, cctv);
                break;
        }
    }

    static void putType5(int[][] map, Cctv cctv) {
        // 방향 구분 없음
        up(map, cctv);
        down(map, cctv);
        left(map, cctv);
        right(map, cctv);
    }

    static int getDeadAreaByDirection(int[][] map, ArrayList<Integer> cctvDirections) {
        // CCTV가 보고 있는 영역 표시 (-1)
        // 0: 상, 1: 하, 2: 좌, 3: 우
        for (int i = 0; i < cctvDirections.size(); i++) {
            Cctv cctv = cctvs.get(i);
            int direction = cctvDirections.get(i);

            switch (cctv.type) {
                case 1:
                    putType1(map, cctv, direction);
                    break;
                case 2:
                    putType2(map, cctv, direction);
                    break;
                case 3:
                    putType3(map, cctv, direction);
                    break;
                case 4:
                    putType4(map, cctv, direction);
                    break;
                case 5:
                    putType5(map, cctv);
                    break;
            }
        }

        // Dead Area 넓이 계산
        int dead = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) dead += 1;
            }
        }

        return dead;
    }

    static void checkDeadAreas(ArrayList<Integer> cctvDirections) {
        if (cctvDirections.size() == cctvs.size()) {
            // cctv 방향 결정되었으면 방향 전환 및 Dead Area 크기 확인
            int[][] copiedMap = getCopiedMap();
            int deadArea = getDeadAreaByDirection(copiedMap, cctvDirections);
//            if (minDeadArea > deadArea) {
//                System.out.println(deadArea);
//                System.out.println(cctvDirections);
//            }
            minDeadArea = Math.min(minDeadArea, deadArea);

            return;
        }

        // cctv 방향은 0-3
        for (int i = 0; i < 4; i++) {
            cctvDirections.add(i);
            checkDeadAreas(cctvDirections);
            cctvDirections.remove(cctvDirections.size() - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int e = Integer.parseInt(st.nextToken());
                map[i][j] = e;

                if (1 <= e && e <= 5) {
                    cctvs.add(new Cctv(i, j, e));
                }
            }
        }

        checkDeadAreas(new ArrayList<>());

        bw.write(Integer.toString(minDeadArea));
        bw.flush();

        bw.close();
        br.close();
    }
}
