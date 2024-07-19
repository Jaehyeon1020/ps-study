from itertools import combinations

def solution(clothes):
    clothes_dict = {}
    for name, category in clothes:
        if category not in clothes_dict:
            clothes_dict[category] = [name]
            continue
        clothes_dict[category].append(name)
        
    answer = 1
    for category, names in clothes_dict.items():
        answer *= len(names) + 1
    return answer - 1