package game.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.frame.Card;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeakService {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final Random random = new Random();
    private List<Card> deck;

    public DeakService() {
        buildDeck();
    }

    public void buildDeck() {
//        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
//        String[] types = {"C", "D", "H", "S"};
//
//        for (int i = 0; i < types.length; i++) {
//            for (int j = 0; j < values.length; j++) {
//                Card card = new Card(values[j], types[i]);
//                deck.add(card);
//            }
//        }

        InputStream input = DeakService.class.getResourceAsStream("/cards.json");
        try {
            deck = objectMapper.readValue(input, new TypeReference<List<Card>>() {});
            Collections.sort(deck);
            System.out.println("CONSTRUIR DECK:");
            System.out.println(deck);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
