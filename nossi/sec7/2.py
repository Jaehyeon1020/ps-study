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