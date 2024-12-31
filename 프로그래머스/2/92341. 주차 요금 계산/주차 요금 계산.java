import java.util.*;


class Solution {
    public int[] solution(int[] fees, String[] records) {
        var answer = new ArrayList<Integer>();
        
        var defaultTime = fees[0];
        var defaultPrice = fees[1];
        var unitTime = fees[2];
        var unitPrice = fees[3];
        
        var parking = new HashMap<Integer, int[]>(); // 차량번호, {누적 minutes, 입차 시각, 출차 여부(0 -> in, 1 -> out)}
        
        for (var record: records) {
            var info = record.split(" ");
            var time = convertTime2Minute(info[0]);
            var carNumber = Integer.parseInt(info[1]);
            var inOut = info[2];
            
            // IN인 경우 기록 있는지 확인 후 기록 없으면 등록, 기록 있으면 입차 시각 기록 && 입차 상태로 변경
            // OUT인 경우 누적 시간 계산 && 출차 상태로 변경
            if (inOut.equals("IN")) {
                if (parking.containsKey(carNumber)) {
                    var cur = parking.get(carNumber);
                    cur[1] = time;
                    cur[2] = 0;
                } else {
                    parking.put(carNumber, new int[]{0, time, 0});
                }
            } else { // OUT
                var cur = parking.get(carNumber);
                cur[0] += time - cur[1];
                cur[2] = 1;
            }
        }
        
        // 출차되지 않은 차들 23:59 출차처리
        for (var carNumber: parking.keySet()) {
            var parkingInfo = parking.get(carNumber);
            if (parkingInfo[2] == 0) {
                parkingInfo[0] += convertTime2Minute("23:59") - parkingInfo[1];
                parkingInfo[2] = 1;
            }
        }
        
        // 차량별 요금 계산
        var feeByCars = new ArrayList<int[]>(); // {차번호, 요금}
        for (var carNumber: parking.keySet()) {
            var parkingInfo = parking.get(carNumber);
            var time = parkingInfo[0];
            
            feeByCars.add(new int[]{carNumber, getFee(time, defaultTime, defaultPrice, unitTime, unitPrice)});
        }
        feeByCars.sort(Comparator.comparingInt(f -> f[0]));
        
        for (var fee: feeByCars) {
            answer.add(fee[1]);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    int convertTime2Minute(String time) {
        var h = Integer.parseInt(time.substring(0, 2));
        var m = Integer.parseInt(time.substring(3, 5));
        
        return h * 60 + m;
    }
    
    int getFee(int time, int defaultTime, int defaultPrice, int unitTime, int unitPrice) {
        int fee = 0;
        
        if (time >= defaultTime) {
            time -= defaultTime;
            fee += defaultPrice;    
        } else {
            return defaultPrice;    
        }
        
        while (time > unitTime) {
            time -= unitTime;
            fee += unitPrice;
        }
        
        if (time > 0) {
            fee += unitPrice;
        }
        
        return fee;
    }
}