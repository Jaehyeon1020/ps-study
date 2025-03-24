import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] road = new int[N - 1];
        int[] price = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        long accMoney = (long)price[0] * road[0];
        int minPrice = price[0];
        for (int curCity = 1; curCity < N - 1; curCity++) {
            minPrice = Math.min(minPrice, price[curCity]);
            accMoney += (long)minPrice * road[curCity];
        }

        bw.write(Long.toString(accMoney));
        bw.flush();

        bw.close();
        br.close();
    }
}
