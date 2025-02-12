import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, D;
    static final int INF = Integer.MAX_VALUE;
    static HashMap<Integer, ArrayList<int[]>> graph;
    static ArrayList<Integer>[] parent;

    static int[] dijkstra(int start, boolean recordParent) {
        int[] dist = new int[N];
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        dist[start] = 0;
        pq.offer(new int[] { start, 0 });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            int d = cur[1];
            if (d > dist[u]) continue;

            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int w = edge[1];

                if (w == -1) continue;

                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[] { v, dist[v] });
                    if (recordParent) {
                        parent[v].clear();
                        parent[v].add(u);
                    }
                } else if (recordParent && dist[v] == dist[u] + w) {
                    parent[v].add(u);
                }
            }
        }
        return dist;
    }

    static void removePaths(int dest) {
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(dest);
        visited[dest] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u : parent[v]) {
                for (int[] edge : graph.get(u)) {
                    if (edge[0] == v && edge[1] != -1) {
                        edge[1] = -1;
                    }
                }
                if (!visited[u]) {
                    visited[u] = true;
                    queue.offer(u);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            graph = new HashMap<>();
            for (int i = 0; i < N; i++) {
                graph.put(i, new ArrayList<>());
            }

            parent = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                parent[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph.get(u).add(new int[] {v, w});
            }

            int[] dist = dijkstra(S, true);

            if (dist[D] == INF) {
                bw.write("-1\n");
            } else {
                removePaths(D);
                int[] almost = dijkstra(S, false);
                bw.write((almost[D] == INF ? -1 : almost[D]) + "\n");
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
