n, m = map(int, input().split())
nums = sorted(list(map(int, input().split())))

results = []

case = []
def dfs():
    if len(case) == m:
        results.append(case.copy())
        return
    
    for num in nums:
        case.append(num)
        dfs()
        case.pop()

dfs()

for r in results:
    print(' '.join(list(map(str, r))))