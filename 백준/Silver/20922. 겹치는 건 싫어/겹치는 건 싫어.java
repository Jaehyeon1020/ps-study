// 20922 겹치는 건 싫어

import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] nums;
    static HashMap<Integer, Integer> map; // 현재 부분 수열에서 어떤 수가 몇 번 포함되어있는지 체크
    static int maxLen = 0;

    static void findMaxLen() {
        int l = 0;
        int r = 0;
        map.put(nums[l], 1);

        // r이 움직일 때 Map에서 횟수 증가
        // l이 움직일 때 Map에서 횟수 감소
        while (true) {
            int lNum = nums[l];
            int rNum = nums[r];

            // 어느 포인터를 움직일지 결정
            // right를 움직여서 K번 넘게 포함된 숫자가 있다면 left 당기기 (K번 이하 될때까지)
            while (map.get(rNum) > K) {
                map.put(lNum, map.get(lNum) - 1);
                l += 1;
                lNum = nums[l];
            }

            // K번 넘게 포함된 숫자 없으면 maxLen 계산
            int len = r - l + 1;
            maxLen = Math.max(len, maxLen);

            // r이 맨 끝에 있는 경우 탈출
            if (r == N -1) break;

            // r 밀기
            if (r < N - 1) {
                r += 1;
                rNum = nums[r];
                map.put(rNum, map.get(rNum) + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new int[N];
        map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, 0);
            nums[i] = num;
        }

        findMaxLen();

        bw.write(Integer.toString(maxLen));
        bw.flush();

        bw.close();
        br.close();
    }
}
