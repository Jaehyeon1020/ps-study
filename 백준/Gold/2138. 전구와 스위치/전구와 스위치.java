import java.util.*;
import java.io.*;

public class Main {
    static String src, dest;
    static int size;

    static String flip(String s) {
        StringBuilder newSb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            newSb.append(s.charAt(i) == '0' ? '1' : '0');
        }

        return newSb.toString();
    }

    static int change() {
        StringBuilder srcSb = (new StringBuilder()).append(src);

        // case 1: 첫 번째 스위치 누르지 않음(그대로)
        int pushed = 0;
        // 2 ~ N-1번째 스위치 결정
        for (int i = 1; i < size - 1; i++) {
            if (srcSb.charAt(i - 1) == dest.charAt(i - 1)) continue;
            srcSb.replace(i - 1, i + 2, flip(srcSb.substring(i - 1, i + 2)));
            pushed += 1;
        }
        // N번째 스위치 결정
        if (srcSb.toString().equals(dest)) return pushed;
        else {
            srcSb.replace(size - 2, size, flip(srcSb.substring(size - 2, size)));
            pushed += 1;
            if (srcSb.toString().equals(dest)) return pushed;
        }

        //case 2: 첫 번째 스위치 누름
        srcSb = (new StringBuilder()).append(src);
        pushed = 1;
        srcSb.replace(0, 2, flip(srcSb.substring(0, 2)));
        // 2 ~ N-1번째 스위치 결정
        for (int i = 1; i < size - 1; i++) {
            if (srcSb.charAt(i - 1) == dest.charAt(i - 1)) continue;
            srcSb.replace(i - 1, i + 2, flip(srcSb.substring(i - 1, i + 2)));
            pushed += 1;
        }
        // N번째 스위치 결정
        if (srcSb.toString().equals(dest)) return pushed;
        else {
            srcSb.replace(size - 2, size, flip(srcSb.substring(size - 2, size)));
            pushed += 1;
            if (srcSb.toString().equals(dest)) return pushed;
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        size = Integer.parseInt(br.readLine());
        src = br.readLine();
        dest = br.readLine();

        int answer = change();

        bw.write(Integer.toString(answer));
        bw.flush();

        bw.close();
        br.close();
    }
}
