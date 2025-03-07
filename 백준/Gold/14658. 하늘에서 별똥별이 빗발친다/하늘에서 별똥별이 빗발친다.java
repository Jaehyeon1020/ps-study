import java.util.*;
import java.io.*;

public class Main {
    static int N, M, L, K;
    static ArrayList<Pos> stars = new ArrayList<>();

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int calculateStarOnPos(int x, int y) {
        if (x < 0 || x > N || y < 0 || y > M) return -1;

        int count = 0;

        // x, y 위치는 왼쪽 위 기준
        for (Pos star: stars) {
            if (x <= star.x && star.x <= x + L && y <= star.y && star.y <= y + L) {
                count += 1;
            }
        }

        return count;
    }

    static int calculateMissedStars() {
        int caught = -1;

        // A, B 두 점을 받은 뒤, (A.x, B.y)를 왼쪽 꼭짓점으로 하는 트렘펄린 만들기
        for (Pos A: stars) {
            for (Pos B: stars) {
                caught = Math.max(caught, calculateStarOnPos(A.x, B.y));
            }
        }

        return K - caught;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars.add(new Pos(x, y));
        }

        int starCount = calculateMissedStars();

        bw.write(Integer.toString(starCount));
        bw.flush();

        bw.close();
        br.close();
    }
}
