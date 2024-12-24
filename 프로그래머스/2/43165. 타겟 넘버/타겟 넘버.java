import java.util.*;

class Solution {
    public static int count = 0;
    
    public void dfs(int sum, int[] numbers, int idx, int target) {
        if (idx == numbers.length) {
            if (sum == target) count += 1;
            return;
        }
        
        dfs(sum + numbers[idx], numbers, idx + 1, target);
        dfs(sum - numbers[idx], numbers, idx + 1, target);
    }
    
    public int solution(int[] numbers, int target) {
        dfs(0, numbers, 0, target);
        return count;
    }
}