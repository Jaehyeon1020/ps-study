import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] nums;

    static boolean[][] makeDpTable() {
        // dp[i][j]: i번째부터 j번째 수가 팰린드롬?
        boolean[][] dp = new boolean[N + 1][N + 1];

        // 홀수개의 수로 이루어진 팰린드롬 체크
        for (int mid = 1; mid <= N; mid++) {
            int leftMove = 0;
            int rightMove = 0;

            while (mid - leftMove >= 1 && mid + rightMove <= N) {
                int leftNum = nums[mid - leftMove];
                int rightNum = nums[mid + rightMove];

                if (leftNum == rightNum) {
                    dp[mid - leftMove][mid + rightMove] = true;
                    leftMove += 1;
                    rightMove += 1;
                } else break;
            }
        }

        // 짝수개의 수로 이루어진 팰린드롬 체크
        for (int left = 1; left <= N - 1; left++) {
            int right = left + 1;

            int leftMove = 0;
            int rightMove = 0;
            while (left - leftMove >= 1 && right + rightMove <= N) {
                int leftNum = nums[left - leftMove];
                int rightNum = nums[right + rightMove];

                if (leftNum == rightNum) {
                    dp[left - leftMove][right + rightMove] = true;
                    leftMove += 1;
                    rightMove += 1;
                } else break;
            }
        }

        return dp;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = makeDpTable();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            bw.write(dp[S][E] ? "1\n" : "0\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
