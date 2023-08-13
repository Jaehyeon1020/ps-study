def gcd(n1, n2):
    a = n1
    b = n2
    if n1 < n2:
        a, b = b, a
    
    while b > 0:
        a, b = b, a % b
    
    return a

def lcm(n1, n2):
    return (n1 * n2) / gcd(n1, n2)

def solution(n, m):
    answer = [gcd(n, m), lcm(n, m)]
    
    
    
    return answer