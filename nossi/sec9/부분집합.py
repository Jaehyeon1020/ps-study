def solution(nums):
    answer = []
    m = 0

    def backtracking(start, cur):
        if m == len(cur):
            answer.append(cur[:])
            return
        for i in range(start, len(nums)):
            cur.append(nums[i])
            backtracking(i + 1, cur)
            cur.pop()
        
    for i in range(0, len(nums) + 1):
        m = i
        backtracking(0, [])

    return answer

nums = [1, 2, 3, 4]
print(solution(nums))