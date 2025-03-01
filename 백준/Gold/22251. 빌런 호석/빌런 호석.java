import java.io.*;
import java.util.*;

public class Main {
    // N: 층수 최대
    // K: 디스플레이 자리수
    // P: 반전 가능한 LED 최대 수
    // X: 현재 엘레베이터가 멈춰있는 층
    static int N, K, P, X;
    static final String[] display = new String[]{
            "1110111",
            "0010010",
            "1011101",
            "1011011",
            "0111010",
            "1101011",
            "1101111",
            "1010010",
            "1111111",
            "1111011"
    };
    static int count = 0;
    static HashMap<String, Integer> memo = new HashMap<>();

    // K자리수의 층수 String 만들기
    static String makeFloorString(int floor) {
        StringBuilder sb = new StringBuilder();
        String f = Integer.toString(floor);

        while (sb.length() < K - f.length()) {
            sb.append(0);
        }
        sb.append(f);

        return sb.toString();
    }

    // src -> dest로 숫자를 바꿀 때 flip되는 led 개수
    static int getFlipped(int src, int dest) {
        int flipped = 0;

        if (src == dest) return 0;

        String key = src + "-" + dest;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        String srcBinaryStr = display[src];
        String destBinaryStr = display[dest];

        int srcBinary = Integer.parseInt(srcBinaryStr, 2);
        int destBinary = Integer.parseInt(destBinaryStr, 2);
        int diff = srcBinary ^ destBinary;

        for (char c: Integer.toBinaryString(diff).toCharArray()) {
            if (c == '1') flipped += 1;
        }

        memo.put(key, flipped);
        memo.put(dest + "-" + src, flipped);

        return flipped;
    }

    // X => target 층으로 바꾸기 위해 반전해야 하는 led 수 구하기
    static int changeTo(int target) {
        int flipped = 0;

        String xStr = makeFloorString(X);
        String targetStr = makeFloorString(target);

        for (int i = 0; i < xStr.length(); i++) {
            char xCur = xStr.charAt(i);
            char targetCur = targetStr.charAt(i);

            flipped += getFlipped(Integer.parseInt(Character.toString(xCur)), Integer.parseInt(Character.toString(targetCur)));
        }

        return flipped;
    }

    static void countCanChange() {
        for (int i = 1; i <= N; i++) {
            if (i == X) continue;

            // X층 => i층으로 디스플레이 조작. 반전해야되는 led 개수 구하기
            int controlled = changeTo(i);

            // 조작된 led 개수가 P개 이하가 되어야 함. 이 경우에만 카운트
            if (controlled <= P) {
                count += 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        countCanChange();

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
