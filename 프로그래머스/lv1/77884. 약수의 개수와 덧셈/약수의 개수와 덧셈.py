def check(num):
    count = 0
    for i in range(1, num + 1):
        if num % i == 0:
            count += 1
    
    if count % 2 == 0:
        return True
    return False

def solution(left, right):
    answer = 0
    
    for i in range(left, right + 1):
        c = check(i)
        if c:
            answer += i
        else:
            answer -= i
    
    return answer