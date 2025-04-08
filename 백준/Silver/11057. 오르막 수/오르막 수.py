import sys

input = lambda: sys.stdin.readline().rstrip()

N = int(input())

def get_sum():
    if N == 1:
        return 10
    
    dp = [[0] * 10 for _ in range(N + 1)]
    
    for j in range(10):
        dp[1][j] = 1
    
    for i in range(2, N + 1):
        for j in range(10):
            dp[i][j] = sum(dp[i - 1][j:]) % 10007
    
    return sum(dp[N]) % 10007

print(get_sum())