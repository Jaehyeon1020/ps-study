import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static HashMap<Integer, ArrayList<Edge>> tree = new HashMap<>();
    static int farNode;
    static int maxDist;

    static class Edge {
        int from;
        int to;
        int dist;

        public Edge(int f, int t, int d) {
            this.from = f;
            this.to = t;
            this.dist = d;
        }
    }

    static void dfs(int cur, int distSum, HashSet<Integer> visited) {
        if (distSum > maxDist) {
            farNode = cur;
            maxDist = distSum;
        }

        if (!tree.containsKey(cur)) return;

        for (Edge child: tree.get(cur)) {
            if (visited.contains(child.to)) continue;

            visited.add(child.to);
            dfs(child.to, distSum + child.dist, visited);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if (!tree.containsKey(parent)) tree.put(parent, new ArrayList<>());
            if (!tree.containsKey(child)) tree.put(child, new ArrayList<>());
            tree.get(parent).add(new Edge(parent, child, dist));
            tree.get(child).add(new Edge(child, parent, dist));
        }

        // 첫 번재 dfs: 루트에서 가장 먼 노드 찾기
        maxDist = -1;
        HashSet<Integer> visited = new HashSet<>();
        visited.add(1); // root node
        dfs(1, 0, visited);

        // 두 번째 dfs: 루트에서 가장 먼 노드에서부터 가장 먼 노드 찾기
        maxDist = -1;
        visited = new HashSet<>();
        visited.add(farNode);
        dfs(farNode, 0, visited);

        bw.write(Integer.toString(maxDist));
        bw.flush();

        bw.close();
        br.close();
    }
}
