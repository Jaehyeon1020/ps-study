n, m = map(int, input().split())
nums = sorted(list(map(int, input().split())))

permutations = set()
results = []

counts = {}
for num in nums:
    if num in counts:
        counts[num] += 1
    else:
        counts[num] = 1

case = []
def dfs():
    if len(case) == m:
        if ' '.join(list(map(str, case))) not in permutations:
            permutations.add(' '.join(list(map(str, case))))
            results.append(case.copy())
        return
    
    for i in range(n):
        if len(case) > 0 and counts[nums[i]] == 0:
            continue

        case.append(nums[i])
        counts[nums[i]] -= 1
        dfs()
        counts[nums[i]] += 1
        case.pop()

dfs()

for r in results:
    print(' '.join(list(map(str, r))))