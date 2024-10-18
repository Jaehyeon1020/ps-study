n, m = map(int, input().split())
nums = sorted(list(map(int, input().split())))

results = []
case = []
case_set = set()

def dfs():
    if len(case) == m:
        check = ' '.join(map(str, case))
        if check not in case_set:
            results.append(case.copy())
            case_set.add(check)
        return
    
    for num in nums:
        if len(case) != 0 and num < case[-1]:
            continue

        case.append(num)
        dfs()
        case.pop()

dfs()

for r in results:
    print(' '.join(map(str, r)))