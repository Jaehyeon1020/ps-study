def solution(a, b, n):
    answer = 0
    
    while n >= a:
        given = n // a
        n -= a * given
        n += given * b
        answer += given * b
    
    return answer