import java.util.*;
import java.io.*;

public class Main {
    static int N; // 지점 수
    static ArrayList<Edge> roads; // size: M + W (도로 + 웜홀)

    static class Edge {
        int from;
        int to;
        int price;

        public Edge(int f, int t, int p) {
            this.from = f;
            this.to = t;
            this.price = p;
        }
    }

    static String bellmanFord() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 10001);
        dist[1] = 0;

        // 최소거리 계산
        for (int i = 0; i < N - 1; i++) {
            for (Edge road: roads) {
                dist[road.to] = Math.min(dist[road.to], dist[road.from] + road.price);
            }
        }

        // 음의 사이클 탐지
        for (Edge road: roads) {
            if (dist[road.to] > dist[road.from] + road.price) return "YES\n";
        }

        return "NO\n";
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            roads = new ArrayList<>();

            // 도로 정보
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int price = Integer.parseInt(st.nextToken());

                roads.add(new Edge(from, to, price));
                roads.add(new Edge(to, from, price)); // 양방향 간선
            }
            // 웜홀 정보
            for (int k = 0; k < w; k++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int price = (-1) * Integer.parseInt(st.nextToken());

                roads.add(new Edge(from, to, price)); // 단방향 간선
            }

            String judgeResult = bellmanFord();
            bw.write(judgeResult);
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
