def check(today, term, date):
    today_num = int(today.replace('.', ''))
    y, m, d = list(map(int, date.split('.')))
    
    y += term // 12
    m += term % 12
    
    if m > 12:
        y += 1
        m -= 12
    
    new_date = []
    new_date.append(str(y))
    new_date.append(str(m) if len(str(m)) == 2 else '0' + str(m))
    new_date.append(str(d) if len(str(d)) == 2 else '0' + str(d))
    
    if today_num < int(''.join(new_date)):
        return True
    return False
    

def solution(today, terms, privacies):
    answer = []
    terms_dict = {}
    
    for t in terms:
        alpha, month = t.split()
        terms_dict[alpha] = int(month)
    
    for i, p in enumerate(privacies):
        date, term_sign = p.split()
        
        if not check(today, terms_dict[term_sign], date):
            answer.append(i + 1)
            
    return answer