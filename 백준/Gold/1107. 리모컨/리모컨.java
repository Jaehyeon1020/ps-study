import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static HashSet<Integer> brokenButtons = new HashSet<>();

    static boolean canMakeNum(int num) {
        for (char nc: Integer.toString(num).toCharArray()) {
            if (brokenButtons.contains(Integer.parseInt(Character.toString(nc)))) return false;
        }
        return true;
    }

    static int getMinPush() {
        if (N == 100) return 0;

        // lower 찾기
        int lower = N;
        boolean foundLower = false;
        for (int i = N; i >= 0; i--) {
            if (canMakeNum(i)) {
                foundLower = true;
                lower = i;
                break;
            }
        }
        int lowerLen = Integer.toString(lower).length();

//        System.out.println(lower);

        // upper 찾기
        int upper = N;
        boolean foundUpper = false;
        for (int i = N; i < 1_000_000; i++) {
            if (canMakeNum(i)) {
                foundUpper = true;
                upper = i;
                break;
            }
        }
        int upperLen = Integer.toString(upper).length();

//        System.out.println(upper);

        return Math.min(foundLower ? N - lower + lowerLen : Integer.MAX_VALUE, Math.min(foundUpper ? upper - N + upperLen : Integer.MAX_VALUE, Math.abs(100 - N)));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                brokenButtons.add(Integer.parseInt(st.nextToken()));
            }
        }

        int push = getMinPush();
        bw.write(Integer.toString(push));

        bw.close();
        br.close();
    }
}
