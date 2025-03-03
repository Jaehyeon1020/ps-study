import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int maxDist = -1;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static HashSet<Character> passedChars = new HashSet<>();

//    static int[][] dp; // dp[x][y]: (x,y) 위치까지 도달했을 때 최대 몇 칸을 지나서 올 수 있는지

    static void calculateMaxDist(int x, int y) {
        // 이미 다른 경로에서 현재 위치로 오는 방법이 더 많은 알파벳을 지나 온다면 종료
//        if (dp[x][y] > passedChars.size()) {
//            return;
//        } else if (dp[x][y] < passedChars.size()) {
//            dp[x][y] = passedChars.size();
//            System.out.println(x);
//            System.out.println(y);
//            System.out.println("===");
//        }

        if (passedChars.size() > maxDist) {
            maxDist = passedChars.size();
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < R && 0 <= ny && ny < C && !visited[nx][ny] && !passedChars.contains(map[nx][ny])) {
                visited[nx][ny] = true;
                passedChars.add(map[nx][ny]);

                calculateMaxDist(nx, ny);

                visited[nx][ny] = false;
                passedChars.remove(map[nx][ny]);
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
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        visited = new boolean[R][C];
        passedChars.add(map[0][0]);
//        dp = new int[R][C];
//        dp[0][0] = 1;

        calculateMaxDist(0, 0);

        bw.write(Integer.toString(maxDist));
        bw.flush();

        bw.close();
        br.close();
    }
}
