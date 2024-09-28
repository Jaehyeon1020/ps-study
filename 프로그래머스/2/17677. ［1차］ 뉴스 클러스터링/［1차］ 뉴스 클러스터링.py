def solution(str1, str2):
    str1, str2 = str1.lower(), str2.lower()
    
    str1_tokens = {}
    str2_tokens = {}
    
    # str1 토큰
    for i in range(len(str1) - 1):
        token = str1[i:i+2]
        if token.isalpha():
            str1_tokens.setdefault(token, 0)
            str1_tokens[token] += 1
            
    
    # str2 토큰
    for i in range(len(str2) - 1):
        token = str2[i:i+2]
        if token.isalpha():
            str2_tokens.setdefault(token, 0)
            str2_tokens[token] += 1
    
    intersec = []
    union = []
    
    # 교집합, 합집합(str1)
    for key in str1_tokens:
        count_1 = str1_tokens[key]
        
        if key in str2_tokens:
            count_2 = str2_tokens[key]
            intersec.extend([key] * min(count_1, count_2))
            union.extend([key] * max(count_1, count_2))
        else:
            union.extend([key] * count_1)
            
    # 합집합
    for key in str2_tokens:
        count_2 = str2_tokens[key]
        if key not in str1_tokens:
            union.extend([key] * count_2)
            
    if not union:
        return 65536
    
    return int((len(intersec) / len(union)) * 65536)