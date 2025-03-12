import java.util.*;
import java.io.*;

public class Main {
    static boolean isPossible(char c1, char c2) {
        if (c1 == '0') return false;

        int num = Integer.parseInt("" + c1 + c2);
        return 1 <= num && num <= 26;
    }

    static int[] makeDpTable(String s) {
        int mod = 1000000;
        int[] dp = new int[s.length()];

        if (s.charAt(0) == '0') return dp;

        dp[0] = 1;

        if (s.length() == 1) return dp;
        if (isPossible(s.charAt(0), s.charAt(1))) {
            dp[1] = s.charAt(1) == '0' ? 1 : 2;
        } else if (s.charAt(1) != '0') dp[1] = 1;
        else return dp;

        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                dp[i] += dp[i - 1] % mod;
            }
            if (isPossible(s.charAt(i - 1), s.charAt(i))) {
                dp[i] += dp[i - 2] % mod;
            }
            dp[i] %= mod;

            if (dp[i] == 0) break;
        }

        return dp;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();

        int[] dp = makeDpTable(input);
        bw.write(Integer.toString(dp[input.length() - 1]));
        bw.flush();

        bw.close();
        br.close();
    }
}
