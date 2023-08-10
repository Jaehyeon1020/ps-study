def solution(array, commands):
    answer = []
    
    for command in commands:
        i, k, j = command
        sliced = array[i-1: k]
        sliced.sort()
        answer.append(sliced[j - 1])
    
    return answer