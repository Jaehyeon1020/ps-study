def solution(n):
    answer = 0
    three = ''

    while n != 0:
        three += str(n % 3)
        n = n // 3
        
    for idx, item in enumerate(three[::-1]):
        answer += (3 ** idx) * int(item)
    
    return answer