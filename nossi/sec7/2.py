memo = {}
cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]

def getMinimum(n):
    if n == 0 or n == 1:
        return 0
    
    if n not in memo:
        memo[n] = min(getMinimum(n - 2) + cost[n - 2],
                      getMinimum(n - 1) + cost[n - 1])
    return memo[n]

print(getMinimum(len(cost)))

memo2 = {0: 0, 1: 0}
def getMinimum_bottomup(n):
    if n == 0 or n == 1:
        return memo2[n]
    
    for i in range(2, n + 1):
        memo2[i] = min(memo2[i - 2] + cost[i - 2], memo2[i - 1] + cost[i - 1])
    return memo2[n]

print(getMinimum_bottomup(len(cost)))