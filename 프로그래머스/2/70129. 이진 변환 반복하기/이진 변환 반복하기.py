def transform(s):
    ones = s.count('0')
    s = s.replace('0', '')
    return ones, format(len(s), 'b')

def solution(s):
    answer = []
    
    transforms = 0
    removed = 0
    
    cur = s
    while cur != '1':
        ones, result = transform(cur)
        cur = result
        removed += ones
        transforms += 1
    
    return [transforms, removed]