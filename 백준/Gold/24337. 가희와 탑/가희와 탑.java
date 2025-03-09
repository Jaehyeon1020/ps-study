import java.util.*;
import java.io.*;

public class Main {
    static int N, a, b;

    static String calculateHeights() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        if (a == 0 || b == 0) return "-1";

        // a, b 중 큰 것부터 시작
        if (a >= b) {
            for (int curH = 1; curH <= a; curH++) {
                dq.addLast(curH);
            }

            int leftNum = b - 1; // a쪽에서 채운 마지막 기둥을 포함해서 b개가 보여야 함
            for (int curH = b - 1; curH >= 1; curH--) {
                dq.addLast(curH);
            }

            if (dq.size() > N) return "-1";

            while (dq.size() < N) dq.addFirst(1); // N개 될때까지 앞에 1 채우기
        } else if (a < b && a == 1) {
            dq.addLast(b); // b 높이 기둥 세우기
            while (N - dq.size() != b - 1) {
                dq.addLast(1);
            }
            for (int curH = b - 1; curH >= 1; curH--) {
                dq.addLast(curH);
            }
        } else {
            for (int curH = 1; curH <= b; curH++) {
                dq.addFirst(curH);
            }

            int leftNum = a - 1;
            for (int curH = a - 1; curH >= 1; curH--) {
                dq.addFirst(curH);
            }

            if (dq.size() > N) return "-1";

            while (dq.size() < N) dq.addFirst(1);
        }

        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.append(dq.removeFirst()).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        String heights = calculateHeights();

        bw.write(heights);
        bw.flush();

        bw.close();
        br.close();
    }

}
