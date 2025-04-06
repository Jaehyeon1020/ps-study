import sys

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
wine = []
for _ in range(N):
    wine.append(int(input()))

def get_max_wine():
    if len(wine) <= 2:
        return sum(wine)

    # dp[case][i]: i번째 와인을 마셨을 때의 최대 누적
    # case: i - 1번째 와인을 마셨는지 여부
    dp = [[0] * N, [0] * N]
    dp[0][0] = wine[0]
    dp[1][0] = wine[0]
    dp[0][1] = wine[1]
    dp[1][1] = wine[0] + wine[1]
    dp[0][2] = wine[0] + wine[2]
    dp[1][2] = wine[1] + wine[2]

    for i in range(3, N):
        dp[0][i] = wine[i] + max(dp[0][i - 2], dp[1][i - 2], dp[0][i - 3], dp[1][i - 3])
        dp[1][i] = wine[i] + dp[0][i - 1]

    # for a in dp:
    #     print(a)
    # print()
    
    return max(dp[0][N - 1], dp[1][N - 1], dp[0][N - 2], dp[1][N - 2])

print(get_max_wine())