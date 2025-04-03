import sys

input = lambda: sys.stdin.readline().rstrip()

N, S, M = list(map(int, input().split()))
volume = list(map(int, input().split()))

## dp[i][j]: i번째 곡에서 j의 볼륨을 만들 수 있는지?
dp = [[False] * (M + 1) for _ in range(N)]

if S + volume[0] <= M:
    dp[0][S + volume[0]] = True
if S - volume[0] >= 0:
    dp[0][S - volume[0]] = True

for i in range(1, N):
    for v in range(M + 1):
        # 전 곡에서 볼륨 v를 만들 수 없던 경우 pass
        if not dp[i - 1][v]:
            continue

        # 만들 수 있었다면 dp 갱신
        if v + volume[i] <= M:
            dp[i][v + volume[i]] = True
        if v - volume[i] >= 0:
            dp[i][v - volume[i]] = True

max_vol = -1
for i in range(M + 1):
    if dp[N - 1][i]:
        max_vol = i

print(max_vol)