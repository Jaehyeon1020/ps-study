import java.util.*;
import java.io.*;

public class Main {
    static int N, M, X;
    static HashMap<Integer, ArrayList<Node>> graph = new HashMap<>();

    static class Node {
        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static int getMinTime(int src, int dest) {
        PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> e1.dist - e2.dist);
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];

        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (!visited[cur.to]) {
                dist[cur.to] = cur.dist;
                visited[cur.to] = true;
                if (cur.to == dest) return cur.dist;
            }

            for (Node next: graph.get(cur.to)) {
                if (visited[next.to]) continue;
                pq.add(new Node(next.to, next.dist + cur.dist));
            }
        }

        return dist[dest];
    }

    static int getMaxTime() {
        int maxTime = -1;

        // 1 - N까지 X에 갔다 오는 소요시간 측정 (X는 제외)
        // maxTime에 갱신
        for (int city = 1; city <= N; city++) {
            if (city == X) continue;

            // city -> X 최단거리
            int curCityToX = getMinTime(city, X);

            // X -> city 최단거리
            int xToCurCity = getMinTime(X, city);

            // maxTime 갱신
            maxTime = Math.max(curCityToX + xToCurCity, maxTime);
        }

        return maxTime;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            if (!graph.containsKey(from)) graph.put(from, new ArrayList<>());
            graph.get(from).add(new Node(to, dest));
        }

        int maxTime = getMaxTime();

        bw.write(Integer.toString(maxTime));
        bw.flush();

        br.close();
        bw.close();
    }
}
