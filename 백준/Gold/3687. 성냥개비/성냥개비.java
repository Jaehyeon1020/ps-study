import java.util.*;
import java.io.*;

public class Main {
    static Long[] minDp = new Long[101];

    static void makeMinDpTable() {
        // min
        Arrays.fill(minDp, Long.MAX_VALUE);
        minDp[2] = 1L;
        minDp[3] = 7L;
        minDp[4] = 4L;
        minDp[5] = 2L;
        minDp[6] = 6L;
        minDp[7] = 8L;
        minDp[8] = 10L;

        String[] minNums = new String[] {"1", "7", "4", "2", "0", "8"};

        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String cur = "" + minDp[i - j] + minNums[j - 2];
                minDp[i] = Math.min(Long.parseLong(cur), minDp[i]);
            }
        }
    }

    static String getMax(int n) {
        StringBuilder sb = new StringBuilder();

        if (n % 2 == 0) {
            for (int i = 0; i < n / 2; i++) sb.append(1);
        } else {
            sb.append(7);
            for (int i = 0; i < (n - 3) / 2; i++) sb.append(1);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        makeMinDpTable();

        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            StringBuilder sb = new StringBuilder();

            int n = Integer.parseInt(br.readLine());
            sb.append(minDp[n]).append(" ").append(getMax(n)).append("\n");
            bw.write(sb.toString());
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
