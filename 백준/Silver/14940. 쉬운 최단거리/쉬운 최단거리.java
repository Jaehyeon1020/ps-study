// 14940 쉬운 최단거리

import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] distance;
    static int startX, startY;

    static class Node {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.distance = d;
        }
    }

    static void bfs() {
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        boolean[][] visited = new boolean[n][m];

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.addLast(new Node(startX, startY, 0));
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            Node cur = q.removeFirst();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && map[nx][ny] != 0) {
                    Node next = new Node(nx, ny, cur.distance + 1);
                    visited[nx][ny] = true;
                    distance[nx][ny] = next.distance;

                    q.addLast(next);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        distance = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int ground = Integer.parseInt(st.nextToken());
                map[i][j] = ground;

                if (ground == 2) {
                    distance[i][j] = 0;
                    startX = i;
                    startY = j;
                }
                else if (ground == 1) distance[i][j] = -1;
                else distance[i][j] = 0;
            }
        }

        bfs();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(Integer.toString(distance[i][j]) + " ");
            }
            bw.write("\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }
}