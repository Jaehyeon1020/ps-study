import sys

input = lambda: sys.stdin.readline().rstrip()

MOD = 1_000_000_000

N = int(input())

# dp[n][i]: 길이 n인 계단수 중 i로 끝나는 수의 개수 (i: 0-9)
dp = [[0] * 10 for _ in range(N + 1)]

for i in range(1, 10):
    dp[1][i] = 1

for n in range(2, N + 1):
    # 0
    dp[n][0] = dp[n - 1][1] % MOD

    # 9
    dp[n][9] = dp[n - 1][8] % MOD

    # 1-8
    for i in range(1, 9):
        dp[n][i] = (dp[n - 1][i - 1] + dp[n - 1][i + 1]) % MOD

answer = 0
for i in range(0, 10):
    answer += dp[N][i]
    answer %= MOD

print(answer)