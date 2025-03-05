import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] buildings;

    static boolean canSeeFrom(int src, int dest) {
        if (src == dest) return false; // 내가 있는 빌딩은 체크 X

        // 항상 src < dest 유지
        if (src > dest) {
            int tmp = src;
            src = dest;
            dest = tmp;
        }

        // src - dest 사이 기울기
        double srcToDest = (double) (buildings[dest] - buildings[src]) / (dest - src);

        // src - dest 사이에 있는 다른 빌딩이 가로막고있는지 확인
        // 만약 src - dest 기울기보다 src - mid 기울기가 더 크거나 같다면 가로막혀있는 상태를 의미
        for (int mid = src + 1; mid < dest; mid++) {
         double midToSrc = (double) (buildings[mid] - buildings[src]) / (mid - src);
         if (midToSrc >= srcToDest) return false;
        }
        return true;
    }

    static int getMaxBuilding() {
        int maxBuildingDiscovered = 0;

        // i번 빌딩에서 j번 빌딩이 보이는지 조사
        for (int i = 0; i < N; i++) {
            int buildingDiscovered = 0;

            for (int j = 0; j < N; j++) {
                // i번 빌딩에서 j번 빌딩이 보인다면 발견된 빌딩 수 + 1
                if (canSeeFrom(i, j)) buildingDiscovered += 1;
            }
            maxBuildingDiscovered = Math.max(maxBuildingDiscovered, buildingDiscovered);
        }

        return maxBuildingDiscovered;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        buildings = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        int maxBuildingDiscoverd = getMaxBuilding();
        bw.write(Integer.toString(maxBuildingDiscoverd));
        bw.flush();

        bw.close();
        br.close();
    }
}
