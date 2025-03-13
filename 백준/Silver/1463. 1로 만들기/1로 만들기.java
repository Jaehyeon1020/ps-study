import java.util.*;
import java.io.*;

public class Main {
    static int getMin(int N) {
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        if (N == 1) return 0;
        else if (N == 2) return 1;
        else if (N == 3) return 1;

        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i <= N; i++) {
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
        }

        return dp[N];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int minVal = getMin(N);
        bw.write(Integer.toString(minVal));
        bw.flush();

        bw.close();
        br.close();
    }
}
