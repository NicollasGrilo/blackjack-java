package game.services;

import game.frame.Card;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {
    private final List<Card> deck;
    private int sum;
    private int aceCount;
    private List<Card> hands;

    public PlayerService(DeakService deakService) {
        this.deck = deakService.getDeck();
    }

    public void addCardHand() {
        Card card = deck.remove(deck.size() - 1);
        sum += card.getValue();
        aceCount += card.isAce() ? 1 : 0;
        hands.add(card);
    }


    public int reduceAce() {
        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount -= 1;
        }
        return sum;
    }

    public void buildPlayer() {
        hands = new ArrayList<Card>();
        sum = 0;
        aceCount = 0;

        for (int i = 0; i < 2; i++) {
            Card card = deck.remove(deck.size() - 1);
            sum += card.getValue();
            aceCount += card.isAce() ? 1 : 0;
            hands.add(card);
        }

        System.out.println("JOGADOR:");
        System.out.println(hands);
        System.out.println(sum);
        System.out.println(aceCount);
    }

    public int getSum() {
        return sum;
    }

    public List<Card> getHand() {
        return hands;
    }
    public Card getHand(int index) {
        return hands.get(index);
    }
}
