import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] nums;

    static HashMap<Integer, Integer> calculateSafeZone() {
        HashMap<Integer, Integer> safeZone = new HashMap<>();
        safeZone.put(0, 0);

        int left = 0;
        int right = 0;

        HashSet<Integer> numExisting = new HashSet<>();
        numExisting.add(nums[0]);

        while (right != N -1 || left < right) {
//            System.out.println(left);
//            System.out.println(right);
//            System.out.println("-----");

            if (right == N - 1) {
                left += 1;
                safeZone.put(left, N - 1);
                continue;
            }

            // move right
            if (!numExisting.contains(nums[right + 1])) {
                right += 1;
                numExisting.add(nums[right]);
                safeZone.put(left, right);
            }
            // move left (right가 더 클 때)
            else if (right > left) {
                numExisting.remove(nums[left]);
                left += 1;
                safeZone.put(left, right);
            }
            // move left + right (숫자가 동일하면서, left == right 일 때)
            else {
                right += 1;
                left += 1;
                safeZone.put(left, right);
            }
        }

        return safeZone;
    }

    static long calculateNumOfCases() {
        if (N == 1) return 1;

        long count = 0;
        HashMap<Integer, Integer> safeZone = calculateSafeZone();

        for (int from = 0; from < N; from++) {
            int to = safeZone.get(from);
            int safeZoneLen = to - from + 1;

            count += safeZoneLen;
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long count = calculateNumOfCases();

        bw.write(Long.toString(count));
        bw.flush();

        bw.close();
        br.close();
    }
}
