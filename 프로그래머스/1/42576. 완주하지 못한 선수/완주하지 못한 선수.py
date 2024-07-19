def solution(participant, completion):    
    par_dict = {}
    comp_dict = {}
    
    for cur in participant:
        if cur not in par_dict:
            par_dict[cur] = 1
            continue
        par_dict[cur] += 1
        
    for cur in completion:
        if cur not in comp_dict:
            comp_dict[cur] = 1
            continue
        comp_dict[cur] += 1
    
    for cur in participant:
        if cur not in comp_dict:
            return cur
        if par_dict[cur] != comp_dict[cur]:
            return cur