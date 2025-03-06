import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] nums;

    static int getMinimum() {
        int[] dp = new int[N + 1]; // dp[i]: nums[i]를 마지막으로 하는 부분증가수열의 길이
        for (int i = 1; i <= N; i++) dp[i] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i - 1; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLen = 0;
        for (int i = 1; i <= N; i++) maxLen = Math.max(maxLen, dp[i]);

        return N - maxLen;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        int m = getMinimum();

        bw.write(Integer.toString(m));
        bw.flush();

        bw.close();
        br.close();
    }
}
