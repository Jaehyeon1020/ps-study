import java.util.*;

class Solution {

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        // 7 5 3 1
        // 8 6 2 0
        // o o 
        
        int count = 0;
        int bIdx = B.length - 1;
        int bEnd = 0;
        for (int aIdx = A.length - 1; aIdx > -1; aIdx--) {
            if (A[aIdx] < B[bIdx]) {
                count += 1;
                bIdx -= 1;
            } else {
                bEnd += 1;
            }
            
            if (bIdx < bEnd) {
                break;
            }
        }
        
        return count;
    }
}