import sys

input = lambda: sys.stdin.readline().rstrip()

T, W = map(int, input().split())
tree = []
for _ in range(T):
    tree.append(int(input()))

def get_max():
    if W >= T:
        return T

    # dp[move][t]: 최대 move번 t초까지 움직였을 때 받을 수 있는 자두의 최대 개수
    dp = [[-1] * T for _ in range(W + 1)]
    
    acc_tree = 0
    for i in range(T):
        if tree[i] == 1:
            acc_tree += 1
        
        dp[0][i] = acc_tree
    
    dp[1][0] = 1 if dp[0][0] == 0 else 0

    for move in range(1, W + 1):
        for t in range(move - 1, T):
            if move == 1 and t == 0: continue

            if move % 2 == 1:
                if tree[t] == 1:
                    dp[move][t] = max(dp[move][t - 1], dp[move - 1][t - 1] + 1)
                else:
                    dp[move][t] = max(dp[move][t - 1] + 1, dp[move - 1][t - 1])
            else:
                if tree[t] == 1:
                    dp[move][t] = max(dp[move][t - 1] + 1, dp[move - 1][t - 1])
                else:
                    dp[move][t] = max(dp[move][t - 1], dp[move - 1][t - 1] + 1)
    
    # for l in dp:
    #     print(l)
    # print()

    return dp[W][T - 1]

print(get_max())