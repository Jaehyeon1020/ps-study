def is_prime(n):
    for i in range(2, int(n**(1/2)) + 1):
        if n % i == 0:
            return False
    return True

def solution(n):
    answer = 0
    
    p = [True for i in range(n + 1)]
    for num in range(2, n + 1):
        if p[num] is False:
            continue
        if not is_prime(num):
            try:
                for i in range(1, len(p) + 1):
                    p[num * i] = False
            except:
                continue
                
    return p.count(True) - 2