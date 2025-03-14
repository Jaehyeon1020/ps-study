import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static Plan[] plans;

    static class Plan {
        int start;
        int end;

        public Plan (int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    static int getMaxPlans() {
        Arrays.sort(plans, (p1, p2) -> {
            if (p1.end < p2.end) return -1;
            else if (p1.end > p2.end) return 1;

            if (p1.start < p2.start) return -1;
            else return 1;
        });

        int maxPlans = 0;

        int lastEnd = -1;
        for (Plan p: plans) {
            if (p.start < lastEnd) continue;

            maxPlans += 1;
            lastEnd = p.end;
        }

        return maxPlans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        plans = new Plan[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            plans[i] = new Plan(s, e);
        }

        int maxPlans = getMaxPlans();
        bw.write(Integer.toString(maxPlans));
        bw.flush();

        bw.close();
        br.close();
    }
}
