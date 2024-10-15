n, m = map(int, input().split())
nums = sorted(list(map(int, input().split())))

results = []

case = []
using = set()

def dfs():
    if len(case) == m:
        results.append(case.copy())
        return

    for num in nums:
        if num in using:
            continue

        case.append(num)
        using.add(num)
        dfs()
        using.remove(num)
        case.pop()

dfs()

for r in results:
    print(' '.join(list(map(str, r))))