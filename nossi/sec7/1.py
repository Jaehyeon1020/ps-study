memo = {1: 1, 2: 2}

def getCaseCount(n):
    if n == 1:
        return memo[1]
    if n == 2:
        return memo[2]

    if n - 1 not in memo:
        memo[n - 1] = getCaseCount(n - 1)
    if n - 2 not in memo:
        memo[n - 2] = getCaseCount(n - 2)


    return memo[n - 1] + memo[n -2]

print(getCaseCount(5))