def solution(n):
    l = sorted(list(map(int, str(n))), reverse=True)
    ll = list(map(str, l))
    return int(''.join(ll))