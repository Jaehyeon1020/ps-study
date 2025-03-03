import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] nums;
    static HashMap<Integer, Integer> numMap; // 숫자, 숫자가 등장한 횟수

    static int getGoodNums() {
        int count = 0;

        for (int target: nums) {
            boolean isGoodNum = false;

            for (int src: nums) {
                int dest = target - src;


                // target과 같은 수가 하나밖에 없다 == 지금 보고 있는 수를 참조하고 있음을 의미
                if (target == src && numMap.get(target) == 1) continue;
                if (target == dest && numMap.get(target) == 1) continue;
                // src와 dest가 같다면 그 숫자가 두 개 이상 있어야 함
                if (src == dest && numMap.get(src) == 1) continue;
                if (target == src && src == dest && numMap.get(target) < 3) continue;

                if (numMap.containsKey(dest)) {
                    isGoodNum = true;
                    break;
                }
            }

            if (isGoodNum) {
//                System.out.println(target);
                count += 1;
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        numMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;

            if (!numMap.containsKey(num)) numMap.put(num, 0);
            numMap.put(num, numMap.get(num) + 1);
        }

        int goodCount = getGoodNums();

        bw.write(Integer.toString(goodCount));
        bw.flush();

        bw.close();
        br.close();
    }
}
