def solution(nums):
    answer = 0
    
    selected = []
    for poke in nums:
        if poke not in selected:
            selected.append(poke)
            answer += 1
    
    if answer > len(nums) / 2:
        answer = len(nums) / 2
    
    
    return answer