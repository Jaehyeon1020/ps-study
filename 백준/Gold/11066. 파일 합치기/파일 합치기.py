import sys
input = lambda: sys.stdin.readline().rstrip()

def get_price(chapters):
    n = len(chapters)
    dp = [[0] * n for _ in range(n)]
    prefix = [0] * (n + 1)

    for i in range(n):
        prefix[i + 1] = prefix[i] + chapters[i]

    # 길이 2부터 n까지
    for length in range(2, n + 1):
        for i in range(n - length + 1):
            j = i + length - 1
            dp[i][j] = float('inf')
            for k in range(i, j):
                cost = dp[i][k] + dp[k + 1][j] + prefix[j + 1] - prefix[i]
                dp[i][j] = min(dp[i][j], cost)

    return dp[0][n - 1]

TC = int(input())
for _ in range(TC):
    N = int(input())
    chapters = list(map(int, input().split()))
    print(get_price(chapters))
