package game.services;

import game.frame.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeakService {
    private final Random random = new Random();
    private final List<Card> deck = new ArrayList<Card>();

    public void buildDeck() {
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] types = {"C", "D", "H", "S"};

        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < values.length; j++) {
                Card card = new Card(values[j], types[i]);
                deck.add(card);
            }
        }

        System.out.println("CONSTRUIR DECK:");
        System.out.println(deck);
    }

    public void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
        }

        System.out.println("EMBARALHAR DECK:");
        System.out.println(deck);
    }

    public List<Card> getDeck() {
        return deck;
    }
}
