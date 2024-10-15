n, m = map(int, input().split())
nums = sorted(list(map(int, input().split())))

results = []

case = []
def dfs(cur):
    if len(case) == m:
        results.append(case.copy())
        return
    
    for num in nums:
        if len(case) > 0 and num < cur:
            continue
        case.append(num)
        dfs(num)
        case.pop()

dfs(0)

for r in results:
    print(' '.join(list(map(str, r))))
