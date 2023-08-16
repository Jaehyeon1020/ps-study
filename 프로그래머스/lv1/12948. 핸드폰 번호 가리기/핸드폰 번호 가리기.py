def solution(phone_number):
    change = len(phone_number) - 4
    return phone_number.replace(phone_number[:change], "*" * change)
    