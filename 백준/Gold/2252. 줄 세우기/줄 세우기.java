// 2252 줄 세우기

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] inDegree;
    static HashMap<Integer, ArrayList<Integer>> graph;

    static String getTopoSortResult() {
        StringBuilder sb = new StringBuilder();

        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) q.addLast(i);
        }

        while (!q.isEmpty()) {
            int cur = q.removeFirst();
            sb.append(cur).append(" ");

            for (int newNode: graph.get(cur)) {
                inDegree[newNode] -= 1;
                if (inDegree[newNode] == 0) {
                    q.addLast(newNode);
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new HashMap<>();
        inDegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            inDegree[B] += 1;
        }

        bw.write(getTopoSortResult());

        br.close();
        bw.close();
    }
}
