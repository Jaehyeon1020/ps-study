n, m = map(int, input().split())
nums = sorted(list(map(int, input().split())))

results = []

case = []
case_set = set()
def dfs(cur_idx, cur_num):
    if len(case) == m:
        if ' '.join(list(map(str, case))) in case_set:
            return

        results.append(case.copy())
        case_set.add(' '.join(list(map(str, case))))
        return
    
    for idx, num in enumerate(nums):
        if len(case) != 0 and (idx <= cur_idx or cur_num > num):
            continue

        case.append(num)
        dfs(idx, num)
        case.pop()

dfs(0, 0)

for r in results:
    print(' '.join(list(map(str, r))))
