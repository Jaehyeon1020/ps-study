import java.util.*;
import java.io.*;

public class Main {
    static long getCaseCount(int[] coins, int k) {
        // dp[i][j]: index 0 ~ i의 동전까지 사용했을 때 금액 k를 만드는 경우의 수
        long[][] dp = new long[coins.length][k + 1];

        // dp[i][0] == 1
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1L;
        }

        // 0번째 동전만 사용하는 경우 그 동전의 배수의 가치만 만들 수 있음
        for (int j = 1; j <= k; j++) {
            dp[0][j] = j % coins[0] == 0 ? 1 : 0;
        }

        // dp 테이블 채우기 (index 1번째 동전까지 사용하는 경우부터)
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= k; j++) {
                int mul = 0;

                // i번째 동전을 mul개 사용하는 경우의 수 누적
                while (j - coins[i] * mul >= 0) {
                    dp[i][j] += dp[i - 1][j - coins[i] * mul];
                    mul += 1;
                }
            }
        }

        return dp[coins.length - 1][k];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        long caseCount = getCaseCount(coins, k);
        bw.write(Long.toString(caseCount));
        bw.flush();

        bw.close();
        br.close();
    }

}
