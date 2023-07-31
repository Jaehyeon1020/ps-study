def getDivNum(num):
    count = 0
    for i in range(1, int(num**(1/2)) + 1):
        if num % i == 0:
            count += 1
            if not ((num // i) ** 2 == num):
                count += 1
            
    return count

def solution(number, limit, power):
    answer = 0
    
    for knightNum in range(1, number + 1):
        kg = 0
        divNum = getDivNum(knightNum)
        
        if divNum > limit:
            kg = power
        else:
            kg = divNum
            
        answer += kg
    
    return answer