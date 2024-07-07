def solution(nums, target):
    ## 3개 더해서 target 될 수 있는지
    def backtracking(start, cur):
        if len(cur) == 3 and sum([nums[i] for i in cur]) == target:
            return True

        for i in range(start, len(nums)):
            cur.append(i)

            if backtracking(i + 1, cur):
                return True

            cur.pop()
        return False
    return backtracking(0, [])

print(solution([4, 9, 7, 5, 1], 13))