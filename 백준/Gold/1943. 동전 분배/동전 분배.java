import java.util.*;
import java.io.*;

public class Main {
    static boolean[] dp;

    static void makePrice(int coin, int amount) {
        for (int target = 100000; target >= 0; target--) {
            if (!dp[target]) continue;

            // target 금액이 이전까지 나온 동전들로 만들 수 있는 금액일 때
            // 현재 동전 금액을 추가하면 해당 금액도 만들 수 있음
            for (int count = 1; count <= amount; count++) {
                int newPrice = coin * count + target;
                if (newPrice > 100000) break;

                dp[newPrice] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int tc = 0; tc < 3; tc++) {
            int N = Integer.parseInt(br.readLine());

            int total = 0;
            dp = new boolean[100001];
            dp[0] = true;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int coin = Integer.parseInt(st.nextToken());
                int amount = Integer.parseInt(st.nextToken());

                total += coin * amount;

                makePrice(coin, amount);
            }

            if (total % 2 == 1) bw.write("0\n");
            else if (dp[total / 2]) bw.write("1\n");
            else bw.write("0\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
