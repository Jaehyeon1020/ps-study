def solution(phone_book):
    number_set = set()
    for num in phone_book:
        number_set.add(num)
        
    for num in phone_book:
        number_set.remove(num)
        for i in range(len(num)):
            if num[:i+1] in number_set:
                return False
        number_set.add(num)
    return True