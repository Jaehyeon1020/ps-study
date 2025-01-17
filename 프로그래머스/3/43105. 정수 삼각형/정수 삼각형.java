class Solution {
    public int solution(int[][] triangle) {
        // dp[i][j]: 위에서부터 i번째 줄의 j번째 값까지 거치는 숫자 합의 최댓값
        int[][] dp = new int[triangle.length][];
        for (int h = 0; h < triangle.length; h++) {
            dp[h] = new int[triangle[h].length];
        }
        dp[0][0] = triangle[0][0];
        
        for (int i = 0; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (i == 0 && j == 0) continue;
                
                // 왼쪽 끝 값인 경우
                if (j == 0) {
                    dp[i][j] = triangle[i][j] + dp[i - 1][j];
                } else if (j == triangle[i].length - 1) {
                    // 오른쪽 끝 값인 경우
                    dp[i][j] = triangle[i][j] + dp[i - 1][j - 1];
                } else {
                    // 가운데 값인 경우 max(왼쪽에서 오기, 오른쪽에서 오기)
                    dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
            }
        }
        
        int answer = -1;
        for (int s: dp[triangle.length - 1]) {
            answer = Math.max(answer, s);
        }
            
        return answer;
    }
}