n, m = map(int,input().split())
solutions = []

case = []
def dfs(cur):
    if len(case) == m:
        solutions.append(case.copy())
        return
    
    for i in range(cur, n + 1):
        case.append(i)
        dfs(i + 1)
        case.pop()

dfs(1)

for s in solutions:
    print(' '.join(list(map(str, s))))