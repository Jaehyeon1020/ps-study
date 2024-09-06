def solution(numbers, target):
    result = [0]
    
    for number in numbers:
        cur = []
        for last in result:
            cur.append(last + number)
            cur.append(last - number)
        result = cur
    
    return result.count(target)