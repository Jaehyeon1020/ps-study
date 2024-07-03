def get_unique_paths(m, n):
    memo = [[0 for _ in range(n)] for _ in range(m)]

    for x in range(m):
        for y in range(n):
            if x == 0 or y == 0:
                memo[x][y] = 1
                continue
            memo[x][y] = memo[x - 1][y] + memo[x][y - 1]
    return memo[m - 1][n - 1]

def get_unique_paths_top_down(m, n):
    memo = [[0 for _ in range(n)] for _ in range(m)]
    for x in range(m):
        for y in range(n):
            if x == 0 or y == 0:
                memo[x][y] = 1

    def dp(x, y):
        if memo[x][y] == 0:
            memo[x][y] = dp(x - 1, y) + dp(x, y - 1)

        return memo[x][y]
    
    return dp(m - 1, n - 1)

print(get_unique_paths(3, 7))