// 백준 2842: 집배원 한상덕

import java.io.*;
import java.util.*;

public class Main {
    static int[][] town; // 0: 우체국, 1: 집, 2: 목초지
    static int[][] heightsMap;
    static List<Integer> heights;

    static int[] dx = new int[] {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = new int[] {1, -1, 0, 0, 1, -1, 1, -1};

    static int n;

    static int startX, startY;
    static int houseCount = 0;

    static int getMinTired() {
        int minTired = Integer.MAX_VALUE;

        int leftP = 0;
        int rightP = 0;

        while (rightP < heights.size() && leftP <= rightP) {
            if (isVisitedAllHouse(heights.get(leftP), heights.get(rightP))) {
                minTired = Math.min(minTired, heights.get(rightP) - heights.get(leftP));
                leftP += 1;
            } else {
                rightP += 1;
            }
        }

        return minTired;
    }

    static boolean isVisitedAllHouse(int minHeight, int maxHeight) {
        if (heightsMap[startX][startY] < minHeight || heightsMap[startX][startY] > maxHeight) return false;

        ArrayDeque<int[]> q = new ArrayDeque<>(); // x, y
        boolean[][] visited = new boolean[n][n];
        int houses = 0;

        q.addLast(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            int[] cur = q.removeFirst();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 8; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX >= 0 && newX < n && newY >= 0 && newY < n && !visited[newX][newY]) {
                    if (minHeight <= heightsMap[newX][newY] && heightsMap[newX][newY] <= maxHeight) {
                        q.addLast(new int[]{newX, newY});
                        visited[newX][newY] = true;

                        if (town[newX][newY] == 1) {
                            houses += 1;
                        }
                    }
                }
            }
        }

        return houses == houseCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        town = new int[n][n];
        heightsMap = new int[n][n];
        heights = new ArrayList<>();

        int x=0, y=0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = line.charAt(j);
                if (c == 'P') {
                    town[i][j] = 0;
                    x = i;
                    y = j;
                }
                else if (c == 'K') {
                    town[i][j] = 1;
                    houseCount += 1;
                }
                else if (c == '.') town[i][j] = 2;
            }
        }

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int cur = Integer.parseInt(st.nextToken());
                if (!heights.contains(cur)) heights.add(cur);
                heightsMap[i][j] = cur;
            }
        }
        br.close();

        startX = x;
        startY = y;
        Collections.sort(heights);

        System.out.print(getMinTired());
    }
}
