from itertools import combinations

def solution(orders, course):
    answer = []
    combi_save = {}
    
    for size in course:
        for order in orders:
            if len(order) >= size:
                combis = combinations(list(order), size)
                for combi in combis:
                    combi_str = ''.join(sorted(combi))
                    if combi_str not in combi_save:
                        combi_save[combi_str] = 1
                    else:
                        combi_save[combi_str] += 1
        
    for size in course:
        result = []
        max_use = 0
        
        # 최대 주문 횟수 구하기
        for combi in combi_save:
            if len(combi) == size:
                used = combi_save[combi]
                if used > max_use:
                    max_use = used
        
        # 조합 구하기
        for combi in combi_save:
            if len(combi) == size and combi_save[combi] == max_use and max_use >= 2:
                result.append(combi)
        
        answer.extend(result)
                  
    return sorted(answer)