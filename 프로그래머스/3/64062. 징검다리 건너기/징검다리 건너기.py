def solution(stones, k):
    answer = -1
    left = 0
    right = 2e+8
    
    while left <= right:
        boo = True
        mid = (left + right) // 2
        cnt = 0
        
        for s in stones:
            if s - mid <= 0:
                cnt += 1
                if cnt == k:
                    boo = False
                    break
            else:
                cnt = 0
        
        if boo:
            left = mid + 1
            answer = max(answer, mid+1)
        else:
            right = mid - 1
        
    return answer