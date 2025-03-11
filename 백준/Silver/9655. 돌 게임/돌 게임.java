import java.util.*;
import java.io.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        if (N % 2 == 1) {
            bw.write("SK");
        } else {
            bw.write("CY");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
