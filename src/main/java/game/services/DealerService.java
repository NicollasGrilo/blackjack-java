package game.services;

import game.frame.Card;

import java.util.ArrayList;
import java.util.List;

public class DealerService {
    private int sum;
    private int aceCount;

    private List<Card> hands = new ArrayList<>();

    private Card hiddencard;

    public List<Card> buildDealer(List<Card> deck) {
        hands.clear();
        sum = 0;
        aceCount = 0;

        hiddencard = deck.remove(deck.size() - 1); // remover da parte de trás do baralho
        sum += hiddencard.getValue();
        aceCount += hiddencard.isAce() ? 1 : 0;

        Card card = deck.remove(deck.size() - 1);
        sum += card.getValue();
        aceCount += card.isAce() ? 1 : 0;
        hands.add(card);

        System.out.println("BANCA:");
        System.out.println(hiddencard);
        System.out.println(hands);
        System.out.println(sum);
        System.out.println(aceCount);

        return hands;
    }

    public void addCardHand(List<Card> deck){
        while (sum < 17) {
            Card card = deck.remove(deck.size() - 1);
            sum += card.getValue();
            aceCount += card.isAce() ? 1 : 0;
            hands.add(card);
        }
    }

    public int reduceAce() {
        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount -= 1;
        }
        return sum;
    }

    public int getSum() {
        return sum;
    }

    public List<Card> getHand() {
        return hands;
    }

    public Card getHiddencard() {
        return hiddencard;
    }
}
