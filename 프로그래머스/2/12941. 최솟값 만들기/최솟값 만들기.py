def solution(A,B):
    answer = 0
    sorted_a = sorted(A)
    sorted_b = sorted(B, reverse=True)
    
    for i in range(len(A)):
        answer += sorted_a[len(sorted_a) - 1] * sorted_b[len(sorted_b) - 1]
        sorted_a.pop()
        sorted_b.pop()

    return answer