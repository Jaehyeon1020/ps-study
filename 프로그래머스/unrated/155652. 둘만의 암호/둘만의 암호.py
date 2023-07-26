def getNext(code):
    zLoc = ord('z')
    aLoc = ord('a')
    
    if aLoc <= code + 1 and code + 1 <= zLoc:
        return code + 1
    else:
        return aLoc

def solution(s, skip, index):
    answer = ''
    
    zLoc = ord('z')
    aLoc = ord('a')
    
    for ss in s:
        current = ord(ss)
        
        for _ in range(index):
            if chr(getNext(current)) in skip:
                tmp = getNext(current)
                
                while True:
                    if chr(getNext(tmp)) in skip:
                        tmp = getNext(tmp)
                    else:
                        tmp = getNext(tmp)
                        break
                        
                current = tmp
            else:
                current = getNext(current)
            
        answer += chr(current)
    
    return answer