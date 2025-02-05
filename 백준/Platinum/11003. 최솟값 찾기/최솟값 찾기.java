// 11003 최솟값 찾기

import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[] A;
    static int[] D;
    static ArrayDeque<int[]> q; // index, value

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        // D1 = A(1-3+1) ~ A(1) 중 최소
        D = new int[N + 1];
        q = new ArrayDeque<>(); // value 기준 정렬

        findD();
//		System.out.println(Arrays.toString(D));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= N; i++) {
            bw.write(Integer.toString(D[i]) + " ");
        }
        bw.flush();
        bw.close();
    }

    static void findD() {
        for (int i = 1; i <= N; i++) {
            int left = i - L + 1;
            int right = i;

            int cur = A[right];
            while (!q.isEmpty() && q.peekLast()[1] > cur) {
                q.removeLast();
            }
            q.addLast(new int[]{right, cur}); // index, value

            if (!q.isEmpty() && q.peekFirst()[0] < left) q.removeFirst();

            D[i] = q.peekFirst()[1];
        }
    }
}
