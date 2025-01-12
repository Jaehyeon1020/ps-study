import java.util.*;

class Solution {
    int[] takes;
    int people;
    
    boolean checkIfTimeEnough(long time) {
        long count = 0;
        
        for (int take: takes) {
            count += time / (long)take;
        }
        
        return count >= (long)people ? true : false;
    }
    
    long para() {
        long left = 0;
        long right = (long)takes[takes.length - 1] * (long)people;
        
        while (left < right) {
            long mid = (left + right) / 2;
            if (checkIfTimeEnough(mid)) right = mid;
            else left = mid + 1;
        }
        
        return right;
    }
    
    public long solution(int n, int[] times) {
        takes = times;
        people = n;
        
        Arrays.sort(takes);
        return para();
    }
}