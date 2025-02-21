import java.util.*;
import java.io.*;

public class Main {
    static class Shortcut {
        int end;
        int dist;

        public Shortcut(int e, int d) {
            this.end = e;
            this.dist = d;
        }
    }

    static int N, D;
    static HashMap<Integer, ArrayList<Shortcut>> shortcuts;
    static int[] distance;

    static void dijkstra() {
        boolean[] visited = new boolean[D + 1];
        distance = new int[D + 1];

        PriorityQueue<Shortcut> pq = new PriorityQueue<>((e1, e2) -> e1.dist - e2.dist);
        pq.add(new Shortcut(0, 0));

        while (!pq.isEmpty()) {
            Shortcut cur = pq.poll();

            distance[cur.end] = cur.dist;
            visited[cur.end] = true;
            if (cur.end == D) return;

            for (Shortcut sc: shortcuts.get(cur.end)) {
                if (!visited[sc.end]) {
                    pq.add(new Shortcut(sc.end, cur.dist + sc.dist));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 일반 길 만들기
        shortcuts = new HashMap<>();
        for (int i = 0; i < D; i++) {
            shortcuts.put(i, new ArrayList<>());
            shortcuts.get(i).add(new Shortcut(i + 1, 1));
        }

        // 지름길 추가
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if (end <= D) shortcuts.get(start).add(new Shortcut(end, dist));
        }

        dijkstra();

        bw.write(Integer.toString(distance[D]));
        bw.flush();

        bw.close();
        br.close();
    }
}
