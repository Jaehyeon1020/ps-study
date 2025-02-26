import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] heights;
    static int[] matchedTowers;

    static class Tower {
        int height;
        int towerNum;

        public Tower(int height, int loc) {
            this.height = height;
            this.towerNum = loc;
        }
    }

    static void matchTower() {
        matchedTowers = new int[N];
        ArrayDeque<Tower> stack = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int height = heights[i];
            int towerNum = i + 1;

            if (stack.isEmpty()) {
                stack.addLast(new Tower(height, towerNum));
            } else {
                while (!stack.isEmpty() && stack.peekLast().height < height) {
                    Tower removedTower = stack.removeLast();

                    if (stack.isEmpty()) {
                        matchedTowers[removedTower.towerNum - 1] = 0;
                    } else {
                        matchedTowers[removedTower.towerNum - 1] = stack.peekLast().towerNum;
                    }
                }

                stack.addLast(new Tower(height, towerNum));
            }
        }

        while (!stack.isEmpty()) {
            Tower removedTower = stack.removeLast();

            if (stack.isEmpty()) {
                matchedTowers[removedTower.towerNum - 1] = 0;
            } else {
                matchedTowers[removedTower.towerNum - 1] = stack.peekLast().towerNum;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        heights = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        matchTower();

        StringBuilder sb = new StringBuilder();
        for (int matched: matchedTowers) {
            sb.append(matched).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}
