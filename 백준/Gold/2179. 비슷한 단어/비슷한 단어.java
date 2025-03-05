import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static String[] words;
    static HashMap<String, Integer> indexMap = new HashMap<>();
    static HashMap<String, HashSet<String>> prefixMap = new HashMap<>();
    static String S = "", T = "";

    static String getPrefix(String w1, String w2) {
        StringBuilder sb = new StringBuilder();

        // 더 짧은 단어로 비교
        for (int i = 0; i < (w1.length() < w2.length() ? w1.length() : w2.length()); i++) {
            char c1 = w1.charAt(i);
            char c2 = w2.charAt(i);

            if (c1 == c2) sb.append(c1);
            else break;
        }

        return sb.toString();
    }

    static void calculateST() {
        String firstWord = words[0];
        String secondWord = words[1];

        Arrays.sort(words);

        int maxSameLen = 0; // 겹치는 최대 길이

        // 두 개씩 비교
        for (int i = 0; i < N - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            if (word1.equals(word2)) continue;

            String prefix = getPrefix(word1, word2);
            int sameLen = prefix.length();

            // 겹치는 길이가 현재까지 계산된 최대보다 클 때
            // prefixMap 초기화, 현재 prefix 넣기
            if (sameLen > maxSameLen) {
                prefixMap.clear();

                maxSameLen = sameLen;

                HashSet<String> set = new HashSet<>();
                set.add(word1);
                set.add(word2);
                prefixMap.put(prefix, set);
            }
            // 겹치는 길이가 같을 때 (길이 0은 무시)
            else if (sameLen != 0 && sameLen == maxSameLen) {
                if (!prefixMap.containsKey(prefix)) {
                    prefixMap.put(prefix, new HashSet<>());
                }
                prefixMap.get(prefix).add(word1);
                prefixMap.get(prefix).add(word2);
            }
        }

        if (maxSameLen == 0) {
            S = firstWord;
            T = secondWord;
            return;
        }

        for (String prefix: prefixMap.keySet()) {
            HashSet<String> wordSet = prefixMap.get(prefix);
            String[] wordsByPrefix = new String[wordSet.size()];
            int i = 0;
            for (String w: wordSet) {
                wordsByPrefix[i] = w;
                i += 1;
            }
            // 처음 index 기준 정렬
            Arrays.sort(wordsByPrefix, (e1, e2) -> indexMap.get(e1) - indexMap.get(e2));

            // 아직 아무 단어도 세팅되지 않은 경우
            if (S.length() == 0) {
                S = wordsByPrefix[0];
                T = wordsByPrefix[1];
                continue;
            }

            // 이미 단어가 세팅되어있던 경우
            // index 기준으로 비교 후, index 작은 값을 S, T에 넣기
            int originIndex = indexMap.get(S);
            int comparingIndex = indexMap.get(wordsByPrefix[0]);

            if (comparingIndex < originIndex) {
                S = wordsByPrefix[0];
                T = wordsByPrefix[1];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        words = new String[N];
        for (int i = 0; i < N; i++) {
            String w = br.readLine();
            words[i] = w;
            if (!indexMap.containsKey(w)) indexMap.put(w, i);
        }

        calculateST();

        StringBuilder sb = (new StringBuilder()).append(S).append("\n").append(T);
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}
