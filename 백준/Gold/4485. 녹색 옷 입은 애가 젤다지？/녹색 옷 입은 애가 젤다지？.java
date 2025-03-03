import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;

    static class Node {
        int x;
        int y;
        int rupee;

        public Node(int x, int y, int rupee) {
            this.x = x;
            this.y = y;
            this.rupee = rupee;
        }
    }

    static int getMinimumRupee() {
        PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> e1.rupee - e2.rupee);

        boolean[][] visited = new boolean[N][N];

        int[][] values = new int[N][N];
        for (int[] col: values) {
            Arrays.fill(col, Integer.MAX_VALUE);
        }

        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        pq.add(new Node(0, 0, map[0][0]));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            values[cur.x][cur.y] = Math.min(values[cur.x][cur.y], cur.rupee);
            visited[cur.x][cur.y] = true;
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    Node nNode = new Node(nx, ny, cur.rupee + map[nx][ny]);
                    pq.add(nNode);
                }
            }
        }

        return values[N - 1][N - 1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int problem = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int r = getMinimumRupee();

            StringBuilder sb = (new StringBuilder())
                    .append("Problem ")
                    .append(problem)
                    .append(": ")
                    .append(r)
                    .append("\n");

            bw.write(sb.toString());
            problem += 1;
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
