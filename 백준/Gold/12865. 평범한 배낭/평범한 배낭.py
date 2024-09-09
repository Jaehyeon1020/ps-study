import sys
input = sys.stdin.readline

N, K = list(map(int, input().split()))
things = [[0, 0]]
for _ in range(N + 1):
    things.append(list(map(int, input().split())))

dp = [[0] * (K + 1) for _ in range(N + 1)]

for i in range(1, N + 1): # 물건 개수(순서대로)
    weight, value = things[i]
    for j in range(1, K + 1): # 무게 제한
        if j >= weight: 
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight] + value)
        else:
            dp[i][j] = dp[i - 1][j]

print(dp[N][K])