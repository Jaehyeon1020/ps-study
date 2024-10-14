n, m = map(int, input().split())

solutions = []

case = []
def dfs():
    if len(case) == m:
        solutions.append(case.copy())
        return
    
    for i in range(1, n + 1):
        case.append(i)
        dfs()
        case.pop()

dfs()
for s in solutions:
    print(' '.join(list(map(str, s))))