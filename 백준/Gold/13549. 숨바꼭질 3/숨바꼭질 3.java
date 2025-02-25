import java.io.*;
import java.util.*;

public class Main {
    static int N, K;

    static int getMinTime() {
        int[] dp = new int[N >= K ? N + 2 : K + 2];
        Arrays.fill(dp, 100_001);

        // 동생이 더 뒤에 있는 경우
        if (N >= K) {
            return N - K;
        }

        for (int i = 0; i <= N; i++) {
            dp[i] = N - i; // 뒤로 돌아갈 때는 순간이동 불가
            if (i * 2 <= K + 1) {
                dp[i * 2] = Math.min(dp[i * 2], N - i);
            }
        }

        for (int i = N + 1; i <= K; i++) {
            dp[i] = Math.min(dp[i], Math.min(dp[i - 1] + 1, dp[i + 1] + 1));
            if (i * 2 <= K + 1) dp[i * 2] = Math.min(dp[i * 2], dp[i]);
        }

        return dp[K];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bw.write(Integer.toString(getMinTime()));
        bw.flush();

        bw.close();
        br.close();
    }
}
