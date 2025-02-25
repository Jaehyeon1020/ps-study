import java.io.*;

public class Main {
    static int getCombi(int n) {
        int[] start3 = new int[n + 1]; // 3으로 시작하는 조합
        int[] start2 = new int[n + 1]; // 2로 시작하는 조합

        if (n == 1) return 1;
        else if (n == 2) return 2;
        else if (n == 3) return 3;

        start3[1] = 0;
        start2[1] = 0;

        start3[2] = 0;
        start2[2] = 1;

        start3[3] = 1;
        start2[3] = 1;

        for (int i = 4; i <= n; i++) {
            start3[i] = start3[i - 3] + start2[i - 3] + 1;
            start2[i] = start2[i - 2] + 1;
        }

        return start3[n] + start2[n] + 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            bw.write(Integer.toString(getCombi(n)) + "\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
