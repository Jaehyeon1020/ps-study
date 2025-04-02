import sys

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
maze = list(map(int, input().split()))

dp = [-1 for _ in range(N)]
dp[0] = 0

for i, jump in enumerate(maze):
    if dp[i] == -1: continue

    for move in range(1, jump + 1):
        if i + move >= N: break
        dp[i + move] = min(dp[i + move], dp[i] + 1) if dp[i + move] != -1 else dp[i] + 1

print(dp[N - 1])