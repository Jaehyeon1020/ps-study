def dfs(numbers, target, depth):
    answer = 0
    
    if depth == len(numbers):
        if sum(numbers) == target:
            return 1
        return 0
    
    answer += dfs(numbers, target, depth + 1)
    numbers[depth] *= -1
    answer += dfs(numbers, target, depth + 1)
    
    return answer
    
    
def solution(numbers, target):
    return dfs(numbers, target, 0)
    
    