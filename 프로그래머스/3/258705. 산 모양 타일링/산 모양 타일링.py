def solution(n, tops):
    answer = 0
    
    # dp1: 오른쪽 아래 채운 경우
    # dp2: 나머지 경우
    # dp1[k] + dp2[k]: k번째 전체 경우의 수
    
    mod = 10007
    dp1 = [0 for _ in range(n)]
    dp2 = [0 for _ in range(n)]
    
    dp1[0] = 1
    dp2[0] = 3 if tops[0] else 2
    
    for k in range(1, n):
        dp1[k] = (dp1[k - 1] + dp2[k - 1]) % mod
        
        if tops[k] == 0:
            dp2[k] = (dp1[k - 1] + dp2[k - 1] * 2) % mod
        else:
            dp2[k] = (dp1[k - 1] * 2 + dp2[k - 1] * 3) % mod
    
    return (dp1[n -1] + dp2[n -1]) % mod
    
    
            
        
        
        