def solution(n):
    one_count = format(n, 'b').count('1')
    
    cur = n
    while True:
        cur += 1
        if one_count == format(cur, 'b').count('1'):
            return cur
        