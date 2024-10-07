def get_time(start, end):
    start_hour, start_minute = list(map(int, start.split(':')))
    end_hour, end_minute = list(map(int, end.split(':')))

    return (end_hour * 60 + end_minute) - (start_hour * 60 + start_minute)

def solution(m, musicinfos):
    answer = ''
    
    m = m.replace('C#', 'c')
    m = m.replace('D#', 'd')
    m = m.replace('F#', 'f')
    m = m.replace('G#', 'g')
    m = m.replace('A#', 'a')
    m = m.replace('B#', 'b')
        
    musics = []
    
    for info in musicinfos:
        start, end, title, melody = info.split(',')
        play_time = get_time(start, end) # 분단위 재생시간
        
        melody = melody.replace('C#', 'c')
        melody = melody.replace('D#', 'd')
        melody = melody.replace('F#', 'f')
        melody = melody.replace('G#', 'g')
        melody = melody.replace('A#', 'a')
        melody = melody.replace('B#', 'b')
                
        if play_time > len(melody):
            idx = 0
            melody_len = len(melody)
            while len(melody) != play_time:
                melody += melody[idx]
                idx = (idx + 1) % melody_len
        elif play_time < len(melody):
            melody = melody[:play_time]
        
        if m in melody:
            musics.append([play_time, title])
        
        print('melody', melody)
    
    musics.sort(key=lambda x: x[0], reverse=True)
    
    print(musics)
    
    if len(musics) == 0:
        return "(None)"
    
    return musics[0][1]