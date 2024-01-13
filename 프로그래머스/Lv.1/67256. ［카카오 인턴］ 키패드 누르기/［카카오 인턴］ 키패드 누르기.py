def get_distance(position, target):
    position_row = position % 3
    position_col = (position - 1) // 3 + 1
    target_row = target % 3
    target_col = (target - 1) // 3 + 1
    
    if position_row == 0:
        position_row = 3
    if target_row == 0:
        target_row = 3
    
    return abs(position_row - target_row) + abs(position_col - target_col)

def solution(numbers, hand):
    answer = ''
    
    left = 10
    right = 12
    
    for n in numbers:
        if n == 2 or n == 5 or n == 8 or n == 0:
            if n == 0:
                n = 11
            if get_distance(left, n) < get_distance(right, n):
                left = n
                answer += 'L'
            elif get_distance(left, n) > get_distance(right, n):
                right = n
                answer += 'R'
            else:
                if hand == 'right':
                    right = n
                    answer += 'R'
                else:
                    left = n
                    answer += 'L'
        elif n == 1 or n == 4 or n == 7:
            left = n
            answer += 'L'
        else:
            right = n
            answer += 'R'
    
    return answer