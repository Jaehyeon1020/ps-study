import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static HashMap<Integer, Integer> graph;
    static HashSet<Integer> numberSet;

    static HashSet<Integer> getCycleMakingNums(int target) {
        HashSet<Integer> set = new HashSet<>();

        // 간선 개수는 최대 N - 1개
        // 그 전에 싸이클 발견 시 조기종료
        int cur = target;
        for (int i = 0; i < N - 1; i++) {
            set.add(cur);
            cur = graph.get(cur);
            if (cur == target) return set;
        }

        return new HashSet<>();
    }

    static void find() {
        for (int i = 1; i <= N; i++) {
            HashSet<Integer> cycleNums = getCycleMakingNums(i);
            for (int cn: cycleNums) numberSet.add(cn);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        graph = new HashMap<>();
        numberSet = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, Integer.parseInt(br.readLine()));
        }

        find();

        int[] numbers = new int[numberSet.size()];
        int idx = 0;
        for (int num: numberSet) {
            numbers[idx] = num;
            idx += 1;
        }
        Arrays.sort(numbers);

        StringBuilder sb = new StringBuilder();
        sb.append(numbers.length).append("\n");
        for (int num: numbers) {
            sb.append(num).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}
