import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static Building[] buildings;
    static int[] count;
    static int[] closestBuilding;

    static class Building {
        int order;
        int height;

        public Building(int o, int h) {
            this.order = o;
            this.height = h;
        }
    }

    static void calculateSight() {
        count = new int[N + 1];
        closestBuilding = new int[N + 1];

        ArrayDeque<Integer> stack = new ArrayDeque<>(); // building order 삽입

        // 왼쪽부터 순회
        for (int i = 1; i <= N; i++) {
            int curBuildingHeight = buildings[i].height;

            // i번째 빌딩의 왼쪽 시야에서 보이는 빌딩 수 계산
            while (!stack.isEmpty() && buildings[stack.peek()].height <= curBuildingHeight) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                closestBuilding[i] = stack.peek();
            }
            count[i] += stack.size();

            // 현재 빌딩 번호 stack에 push
            stack.push(i);
        }

        // 오른쪽부터 순회
        stack.clear();
        for (int i = N; i >= 1; i--) {
            int curBuildingHeight = buildings[i].height;

            // i번째 빌딩의 오른쪽 시야에서 보이는 빌딩 수 계산
            while (!stack.isEmpty() && buildings[stack.peek()].height <= curBuildingHeight) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                // 빌딩 왼쪽 시야에 볼 수 있는 빌딩이 없는 경우
                if (closestBuilding[i] == 0) {
                    closestBuilding[i] = stack.peek();
                } else {
                    int leftToCur = Math.abs(i - closestBuilding[i]);
                    int curToRight = Math.abs(i - stack.peek());

                    if (leftToCur > curToRight) closestBuilding[i] = stack.peek();
                }
            }
            count[i] += stack.size();

            // 현재 빌딩 번호 stack에 push
            stack.push(i);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        buildings = new Building[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            buildings[i] = new Building(i, Integer.parseInt(st.nextToken()));
        }

        calculateSight();

        StringBuilder sb;
        for (int i = 1; i <= N; i++) {
            sb = new StringBuilder();
            sb.append(count[i]);
            if (count[i] != 0) sb.append(" ").append(closestBuilding[i]);
            sb.append("\n");

            bw.write(sb.toString());
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
