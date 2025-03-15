import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static Plan[] plans;

    static class Plan {
        int start;
        int end;

        public Plan(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        plans = new Plan[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            plans[i] = new Plan(start, end);
        }
        Arrays.sort(plans, (p1, p2) -> {
            if (p1.start == p2.start) return p1.end - p2.end;
            return p1.start - p2.start;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 끝나는 시각 최소 힙
        pq.add(plans[0].end);

        for (int i = 1; i < N; i++) {
            if (pq.peek() <= plans[i].start) pq.poll();
            pq.add(plans[i].end);
        }

        bw.write(Integer.toString(pq.size()));
        bw.flush();

        bw.close();
        br.close();
    }
}
