def solution(data, ext, val_ext, sort_by):
    answer = []
    
    tag = {"code": 0, "date": 1, "maximum": 2, "remain": 3}
    
    for d in data:
        if d[tag[ext]] >= val_ext:
            continue
        answer.append(d)
    
    answer.sort(key=lambda x: x[tag[sort_by]])
    
    return answer