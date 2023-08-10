def solution(answers):
    answer = []
    
    count = {1:0, 2:0, 3:0}
    one = []
    two = []
    three = []
    
    for i in range(2000):
        one.extend([1,2,3,4,5])
        two.extend([2,1,2,3,2,4,2,5])
        three.extend([3,3,1,1,2,2,4,4,5,5])
    
    for i in range(len(answers)):
        if answers[i] == one[i]:
            count[1] += 1
        if answers[i] == two[i]:
            count[2] += 1
        if answers[i] == three[i]:
            count[3] += 1
            
    maxVal = max(count.values())
    for key, value in count.items():
        if value == maxVal:
            answer.append(key)
            
    return answer