from collections import deque

def solution(msg):
    answer = []
    dictionary = {}
    msg = deque(list(msg))
    
    index = 0
    for i in range(ord('A'), ord('Z') + 1):
        index += 1
        dictionary[chr(i)] = index
    
    while msg:
        w = msg[0]
        
        i = 1
        while i < len(msg) and w + msg[i] in dictionary:
            w += msg[i]
            i += 1
        
        answer.append(dictionary[w])
        
        for _ in range(len(w)):
            msg.popleft()
        
        if len(msg) > 0:
            index += 1
            dictionary[w + msg[0]] = index

    return answer