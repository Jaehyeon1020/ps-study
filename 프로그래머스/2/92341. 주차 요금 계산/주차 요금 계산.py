import math

default_time, default_fee, time_unit, fee = [0, 0, 0, 0]

def get_time(in_record, out_record):
    in_hour, in_minute = list(map(int, in_record.split(':')))
    out_hour, out_minute = list(map(int, out_record.split(':')))
    
    in_minute += in_hour * 60
    out_minute += out_hour * 60
    
    return out_minute - in_minute

def get_fee(time_record):
    if time_record < default_time:
        return default_fee
    
    time_left = time_record - default_time
    if time_left / time_unit == math.floor(time_left / time_unit):
        return default_fee + (time_left // time_unit) * fee
    
    return default_fee + (time_left // time_unit + 1) * fee
    
    
def solution(fees, records):
    global default_time, default_fee, time_unit, fee
    
    default_time, default_fee, time_unit, fee = fees
    parking = {}
    total_fees = []
    
    for record in records:
        time_record, car, in_out = record.split()
        
        if car not in parking:
            parking[car] = [time_record, 0]
        elif car in parking and in_out == "IN":
            parking[car][0] = time_record
        else: # OUT 기록인 경우
            parking[car][1] += get_time(parking[car][0], time_record)
            parking[car][0] = ""
            
    for car in parking.keys():
        if parking[car][0] != "":
            parking[car][1] += get_time(parking[car][0], "23:59")
        
        total_fee = get_fee(parking[car][1])
        total_fees.append([car, total_fee])
        
    return [x[1] for x in sorted(total_fees, key=lambda x: x[0])]
    