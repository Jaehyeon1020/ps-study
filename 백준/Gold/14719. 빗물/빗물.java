import java.util.*;
import java.io.*;

public class Main {
    static int H, W;
    static int[] heights;
    static boolean[][] map; // 해당 위치에 블록이 있는지 없는지 저장
    static int rain = 0;

    static void createMap() {
        map = new boolean[H][W];

        for (int i = 0; i < W; i++) {
            int h = heights[i];

            int step = H - 1;
            while (h > 0) {
                map[step][i] = true;
                h -= 1;
                step -= 1;
            }
        }
    }

    static void calculateRain() {
        // 가로줄 하나씩 확인
        // 양 옆이 블록으로 막혀있는 공간의 넓이를 카운트
        for (int h = 0; h < H; h++) {
            boolean[] slicedMap = map[h];
            rain += getPackedAreaSize(slicedMap);
        }
    }

    static int getPackedAreaSize(boolean[] slice) {
        int measuredSize = 0;
        int measuringSize = 0;
        boolean leftBlockExists = false;

        for (int i = 0; i < slice.length; i++) {
            // 현재 보고있는 칸이 비어있으면서, 왼쪽에 블럭으로 막혀있는 경우
            if (!slice[i] && leftBlockExists) {
                measuringSize += 1;
            } else if (slice[i]) {
                // 현재 보고 있는 칸이 블럭이면서, 측정중인 값이 존재하는 경우 (측정 완료)
                if (measuringSize > 0) {
                    measuredSize += measuringSize;
                    measuringSize = 0;
                }
                leftBlockExists = true;
            }
        }

        return measuredSize;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        heights = new int[W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        createMap();
        calculateRain();

        bw.write(Integer.toString(rain));
        bw.flush();

        bw.close();
        br.close();
    }
}
