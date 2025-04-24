import sys

input = lambda: sys.stdin.readline().rstrip()

S = input()
N = int(input())
words = [input() for _ in range(N)]

def judge():
    if len(S) == 1:
        return 1 if S in words else 0

    dp = [[False] * (len(S) + 1) for _ in range(len(S))]

    # init
    for i in range(len(S)):
        for word in words:
            if i + len(word) <= len(S) and S[i: i + len(word)] == word:
                dp[i][i + len(word)] = True
    
    for e in range(len(S) + 1):
        # dp[0][e] = True
        # when dp[0][k] is True and dp[k][e] is True
        for k in range(1, e):
            if dp[0][k] and dp[k][e]:
                dp[0][e] = True
    
    # for l in dp:
    #     print(l)

    return 1 if dp[0][len(S)] else 0

print(judge())