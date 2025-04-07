import sys

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
price = [list(map(int, input().split())) for _ in range(N)]

def get_min_pay():
    # dp[n][color]: n번째 집이 color로 칠해질 때까지의 최소 비용
    # color: 0-R, 1-G, 2-B
    dp = [[0] * 3 for _ in range(N)]

    dp[0][0] = price[0][0]
    dp[0][1] = price[0][1]
    dp[0][2] = price[0][2]

    for i in range(1, N):
        r, g, b = price[i]

        dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + r
        dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + g
        dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + b
    
    return min(dp[N - 1])

print(get_min_pay())
