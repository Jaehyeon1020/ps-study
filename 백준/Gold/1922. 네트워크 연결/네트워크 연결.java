// 1922 네트워크 연결

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<int[]> lines; // {from, to, weight}
    static int[] parent;

    static void init() {
        parent = new int[N + 1];
        for (int i = 1; i <=N; i++) parent[i] = i;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        parent[aRoot] = bRoot;
    }

    static int getMinCost() {
        int cost = 0;
        int usedLines = 0;

        for (int[] line: lines) {
            int from = line[0];
            int to = line[1];
            int weight = line[2];

            if (find(from) != find(to)) {
                union(from, to);
                cost += weight;
                usedLines += 1;
            }
            if (usedLines == N - 1) break;
        }

        return cost;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        lines = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            lines.add(new int[]{from, to, w});
        }

        lines.sort((e1, e2) -> e1[2] - e2[2]);
        init();
        
        bw.write(Integer.toString(getMinCost()));

        br.close();
        bw.close();
    }
}
