import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int[] houses;

    static int getDeviceCount(int minDist) {
        int latest = houses[0];
        int count = 1;

        for (int i = 1; i < N; i++) {
            int houseLoc = houses[i];
            if (houseLoc - latest >= minDist) {
                latest = houseLoc;
                count += 1;
            }
        }

        return count;
    }

    static int getMaxDist() {
        // C 보다 적은 수의 공유기를 놓을 수 있는 최소의 집 간 거리 구하기
        // (해당 거리 - 1) 이 C개의 공유기를 놓을 수 있는 집 간 거리의 최대

        int left = 1;
        int right = houses[N - 1] - houses[0] + 1; // 최대 집 간 거리

        while (left < right) {
            int mid = (left + right) / 2; // 공유기가 설치된 집들간의 최소 거리
            int installed = getDeviceCount(mid);

            // C보다 적은 수의 공유기만을 놓을 수 있는 경우
            if (installed < C) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int minDistBetweenDevices = right - 1;
        int closestHousesDist = Integer.MAX_VALUE;
        int prev = houses[0];
        for (int i = 1; i < N; i++) {
            int cur = houses[i];

            if (cur - prev >= minDistBetweenDevices) {
                closestHousesDist = Math.min(closestHousesDist, cur - prev);
                prev = cur;
            }
        }

        return closestHousesDist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);
        int maxDist = getMaxDist();

        bw.write(Integer.toString(maxDist));
        bw.flush();

        bw.close();
        br.close();
    }
}
