import java.io.*;

public class Main {
    static String S;
    static String T;
    static String canMakeT = "0";

    static StringBuilder appendA(StringBuilder sb) {
        sb.append("A");
        return sb;
    }

    static StringBuilder appendBAndFlip(StringBuilder sb) {
        sb.append("B");
        sb.reverse();
        return sb;
    }

    static int countA(String s) {
        int count = 0;
        for (char c: s.toCharArray()) {
            if (c == 'A') count += 1;
        }
        return count;
    }

    static int countB(String s) {
        int count = 0;
        for (char c: s.toCharArray()) {
            if (c == 'B') count += 1;
        }
        return count;
    }

    static void backtrack(StringBuilder cur) {
        if (cur.toString().equals(T)) {
            canMakeT = "1";
            return;
        } else if (!T.contains(cur.toString()) && !T.contains((new StringBuilder(cur).reverse()).toString())) return;
        else if (countA(cur.toString()) > countA(T) || countB(cur.toString()) > countB(T)) return;

        backtrack(appendA(new StringBuilder(cur)));
        backtrack(appendBAndFlip(new StringBuilder(cur)));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        S = br.readLine();
        T = br.readLine();

        backtrack(new StringBuilder(S));

        bw.write(canMakeT);
        bw.flush();

        bw.close();
        br.close();
    }
}
