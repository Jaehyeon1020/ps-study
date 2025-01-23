import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        
        var hand = new HashSet<Integer>();
        var passed = new HashSet<Integer>();
        var cardQ = new ArrayDeque<Integer>();
        
        for (int card: cards) cardQ.addLast(card);
        for (int i = 0; i < n / 3; i++) hand.add(cardQ.removeFirst());
        
        int round = 1;
        while (cardQ.size() >= 2) {
            passed.add(cardQ.removeFirst());
            passed.add(cardQ.removeFirst());
            
            boolean goToNextRound = false;
            
            // 손에 있는 카드 두 장을 내고 다음 라운드 진출 (coin 소모 X)
            for (int cardInHand: hand) {
                if (hand.contains(n + 1 - cardInHand)) {
                    hand.remove(cardInHand);
                    hand.remove(n + 1 - cardInHand);
                    goToNextRound = true;
                    break;
                }
            }
            if (goToNextRound) {
                round += 1;
                continue;
            }
            
            // 손에 있는 카드 + 지나간 카드 한 장을 내고 다음 라운드 진출 (coin -= 1)
            for (int cardInHand: hand) {
                if (passed.contains(n + 1 - cardInHand) && coin >= 1) {
                    hand.remove(cardInHand);
                    passed.remove(n + 1 - cardInHand);
                    coin -= 1;
                    goToNextRound = true;
                    break;
                }
            }
            if (goToNextRound) {
                round += 1;
                continue;
            }
            
            // 지나간 카드 두 장을 내고 다음 라운드 진출 (coin -= 2)
            for (int cardInPassed: passed) {
                if (passed.contains(n + 1 - cardInPassed) && coin >= 2) {
                    passed.remove(cardInPassed);
                    passed.remove(n + 1 - cardInPassed);
                    coin -= 2;
                    goToNextRound = true;
                    break;
                }
            }
            if (goToNextRound) {
                round += 1;
                continue;
            }
            
            // 전부 불가능하면 게임 종료
            break;
        }
        
        return round;
    }
}