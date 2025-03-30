import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] lectures;

    static boolean canPutAllLecturesWith(int cdSize) {
        int cdLeft = M;
        int size = 0;
        for (int i = 0; i < N; i++) {
            int lecture = lectures[i];

            // 강의 하나가 CD 사이즈보다 클 때 불가능
            if (lecture > cdSize) return false;

            if (size + lecture > cdSize || i == 0) {
                // 강의 다 못넣었는데 이미 모든 CD 다 사용
                if (cdLeft == 0) {
                    return false;
                }

                // 새 CD 사용
                cdLeft -= 1;
                size = lecture;
            } else {
                size += lecture;
            }
        }

        return true;
    }

    static int getMinCd() {
        int l = 0;
        int r = 100_000 * 10_000 + 1;

        while (l < r) {
            int m = (l + r) / 2;

            if (canPutAllLecturesWith(m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return r;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lectures = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
        }

        int minCd = getMinCd();

        bw.write(Integer.toString(minCd));
        bw.flush();

        br.close();
        bw.close();
    }
}
