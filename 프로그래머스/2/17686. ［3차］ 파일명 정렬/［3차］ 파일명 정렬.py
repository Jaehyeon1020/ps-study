from functools import cmp_to_key

def extract(name):
    extracted = []
    
    token = ''
    head_extracted = False
    for c in name:
        if c.isnumeric() and not head_extracted:
            extracted.append(token.lower())
            token = ''
            head_extracted = True    
        
        if not c.isnumeric() and head_extracted:
            extracted.append(int(token))
            return extracted
        token += c
        
    extracted.append(int(token))
    return extracted

def compare_head_number(file1, file2):
    extracted1 = extract(file1)
    extracted2 = extract(file2)
    
    if extracted1[0] > extracted2[0]:
        return 1
    elif extracted1[0] == extracted2[0]:
        if extracted1[1] > extracted2[1]:
            return 1
        elif extracted1[1] == extracted2[1]:
            return 0
        else:
            return -1
    else:
        return -1

def solution(files):
    return sorted(files, key=cmp_to_key(compare_head_number))
    
    