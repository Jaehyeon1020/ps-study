from functools import cmp_to_key

def compare(x, y):
    if int(x + y) > int (y + x):
        return 1
    else: 
        return -1

def solution(numbers):
    numbers = list(map(str, numbers))
    numbers.sort(reverse = True, key = cmp_to_key(compare))
    
    return ''.join(numbers) if numbers[0] != '0' else '0'