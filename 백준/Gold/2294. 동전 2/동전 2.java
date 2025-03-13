import java.util.*;
import java.io.*;

public class Main {
    static int getCaseOfMinCoin(int k, int[] coins) {
        int[][] dp = new int[coins.length][k + 1];
        for (int[] r: dp) Arrays.fill(r, -1);
        for (int i = 0; i < coins.length; i++) dp[i][0] = 0;
        for (int j = 1; j <= k; j++) {
            if (j % coins[0] == 0) dp[0][j] = j / coins[0];
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= k; j++) {
                int curCoinConsume = 0;

                while (j - coins[i] * curCoinConsume >= 0) {
                    if (dp[i - 1][j - coins[i] * curCoinConsume] != -1) {
                        if (dp[i][j] != -1)
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - coins[i] * curCoinConsume] + curCoinConsume);
                        else
                            dp[i][j] = dp[i - 1][j - coins[i] * curCoinConsume] + curCoinConsume;
                    }
                    curCoinConsume += 1;
                }
            }
        }

//        for (int[] r: dp) {
//            System.out.println(Arrays.toString(r));
//        }

        return dp[coins.length - 1][k];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        HashSet<Integer> coinSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            coinSet.add(Integer.parseInt(br.readLine()));
        }

        int[] coins = new int[coinSet.size()];
        int i = 0;
        for (int value: coinSet) coins[i++] = value;
        Arrays.sort(coins);

        int minCoins = getCaseOfMinCoin(k, coins);
        bw.write(Integer.toString(minCoins));

        bw.close();
        br.close();
    }
}
