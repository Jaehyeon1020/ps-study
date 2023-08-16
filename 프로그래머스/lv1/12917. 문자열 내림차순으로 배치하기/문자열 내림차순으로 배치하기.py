def solution(s):
    small = []
    cap = []
    
    for ss in s:
        if ord('A') <= ord(ss) and ord(ss) <= ord('Z'):
            cap.append(ss)
        else:
            small.append(ss)
    
    small.sort(reverse=True)
    cap.sort(reverse=True)
    
    small.extend(cap)
    
    return ''.join(small)