import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int a, b, c;

        while (true) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == 0) break;

            int rest = 0;
            int maxVal = Math.max(a, Math.max(b, c));

            if (maxVal == a && a*a == b*b + c*c) bw.write("right\n");
            else if (maxVal == b && b*b == a*a + c*c) bw.write("right\n");
            else if (maxVal == c && c*c == a*a + b*b) bw.write("right\n");
            else bw.write("wrong\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
