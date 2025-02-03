import java.io.*;

public class Main {
    static int N;
    static int count = 0;
    static int[] board; // board[x] = y => (x, y)에 퀸이 있음. 없으면 -1

    static boolean canPutQueen(int x, int y) {
        for (int i = 0; i < x; i++) {
            if (board[i] == y || Math.abs(x - i) == Math.abs(y - board[i])) {
                return false;
            }
        }
        return true;
    }

    static void dfs(int nthQueen) {
        if (nthQueen == N) {
            count += 1;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (canPutQueen(nthQueen, i)) {
                board[nthQueen] = i;
                dfs(nthQueen + 1);
                board[nthQueen] = -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        br.close();

        board = new int[N];
        for (int i = 0; i < N; i++) {
            board[i] = -1;
        }

        dfs(0);

        System.out.print(count);
    }

}
