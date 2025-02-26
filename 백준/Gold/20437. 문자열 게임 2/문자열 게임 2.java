import java.util.*;
import java.io.*;

public class Main {
    static int shortest;
    static int longest;

    static void find(String w, int k) {
        shortest = Integer.MAX_VALUE;
        longest = -1;

        // 알파벳이 존재하는 위치 리스트 저장
        HashMap<Character, ArrayList<Integer>> indexMap = new HashMap<>();
        // 알파벳이 문자열에 몇 개 있는지 저장
        HashMap<Character, Integer> countMap = new HashMap<>();

        // Map 만들기
        for (int i = 0; i < w.length(); i++) {
            char c = w.charAt(i);

            if (!indexMap.containsKey(c)) {
                indexMap.put(c, new ArrayList<>());
            }
            if (!countMap.containsKey(c)) {
                countMap.put(c, 0);
            }

            indexMap.get(c).add(i);
            countMap.put(c, countMap.get(c) + 1);
        }

        // K == 1 인 경우
        if (k == 1) {
            shortest = 1;
            longest = 1;
            return;
        }

        // K개 이상 등장하는 알파벳 추리기
        ArrayList<Character> validAlphas = new ArrayList<>();
        for (char c: countMap.keySet()) {
            if (countMap.get(c) >= k) validAlphas.add(c);
        }

        // 유효한 알파벳들에 대해 조사
        for (char c: validAlphas) {
            ArrayList<Integer> indexes = indexMap.get(c);

            for (int right = k - 1; right < indexes.size(); right++) {
                int left = right - (k - 1);
                int len = indexes.get(right) - indexes.get(left) + 1;
                shortest = Math.min(shortest, len);
                longest = Math.max(longest, len);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());

            find(w, k);
            if (shortest == -1 || longest == -1) {
                bw.write("-1\n");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(shortest).append(" ").append(longest).append("\n");
                bw.write(sb.toString());
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
