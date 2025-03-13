import java.util.*;
import java.io.*;

public class Main {
    static final int MOD = 1_000_000_000;

    static int getNumOfCases(int N, int K) {
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 1; i <= K; i++) dp[i][0] = 1;
        for (int j = 0; j <= N; j++) dp[1][j] = 1;

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                for (int cur = 0; cur <= j; cur++) {
                    dp[i][j] += (dp[i - 1][j - cur]) % MOD;
                    dp[i][j] %= MOD;
                }
            }
        }

//        for (int[] r: dp) {
//            System.out.println(Arrays.toString(r));
//        }

        return dp[K][N];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numOfCases = getNumOfCases(N, K);
        bw.write(Integer.toString(numOfCases));

        bw.close();
        br.close();
    }
}
