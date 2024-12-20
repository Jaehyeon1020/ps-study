import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        for (int i=1; i<yellow+1; i++) {
            // i: 세로길이
            if (yellow % i != 0) {
                continue;
            }
            
            int x = yellow / i; // 가로길이
            int predictedBrown = i*2 + x*2 + 4;
            
            if (predictedBrown == brown) {
                return new int[]{x + 2, i + 2};
            }
        }
        return new int[]{0,0};
    }
}