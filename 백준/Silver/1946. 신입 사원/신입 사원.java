import java.util.*;
import java.io.*;

public class Main {
    static class Score {
        int paper;
        int interview;

        public Score(int p, int i) {
            this.paper = p;
            this.interview = i;
        }
    }

    static int getCanSelect(Score[] scores) {
        if (scores.length == 1) return 1;

        Arrays.sort(scores, (s1, s2) -> s1.paper - s2.paper);

        int minInterviewScore = scores[0].interview;
        int count = 1;

        for (int i = 1; i < scores.length; i++) {
            Score cur = scores[i];

            if (cur.interview < minInterviewScore) {
                count += 1;
                minInterviewScore = cur.interview;
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            Score[] scores = new Score[N];

            for (int i = 0 ; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int paper = Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());

                scores[i] = new Score(paper, interview);
            }
            int canSelectCount = getCanSelect(scores);
            bw.write(canSelectCount + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
