import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] liqs;
    static int liq1, liq2;
    static int minValue = Integer.MAX_VALUE;

    static int upperbound(int idx, int targetLiq) {
        // idx + 1 용액을 left로, 마지막 용액을 right로
        int left = idx + 1;
        int right = N - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (liqs[mid] > targetLiq) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        if (right != idx + 1) {
            int curValue = liqs[right];
            int leftValue = liqs[right - 1];

            if (Math.abs(targetLiq - curValue) > Math.abs(targetLiq - leftValue)) right -= 1;
        }

        return right;
    }

    static void calculateMinValue() {
        for (int i = 0; i < N - 1; i++) {
            int curLiq = liqs[i];
            int targetLiq = -1 * curLiq; // 더해서 0 되는 값이 target

            int lb = upperbound(i, targetLiq);
            int pairLiq = liqs[lb];

            if (Math.abs(curLiq + pairLiq) < minValue) {
                liq1 = curLiq;
                liq2 = pairLiq;
                minValue = Math.abs(curLiq + pairLiq);

                if (minValue == 0) return;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        liqs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liqs[i] = Integer.parseInt(st.nextToken());
        }

        calculateMinValue();

        bw.write(Integer.toString(liq1) + " " + Integer.toString(liq2));
        bw.flush();

        bw.close();
        br.close();
    }
}
