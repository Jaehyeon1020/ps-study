n, m = map(int, input().split())
nums = sorted(list(map(int, input().split())))

results = []
case = []
case_set = set()

def dfs():
    if len(case) == m:
        check = ' '.join(list(map(str, case)))
        if check not in case_set:
            results.append(case.copy())
            case_set.add(check)
        return
    
    for num in nums:
        case.append(num)
        dfs()
        case.pop()

dfs()

for r in results:
    print(' '.join(list(map(str, r))))
