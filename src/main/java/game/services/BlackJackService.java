package game.services;

import game.frame.Card;

import java.util.List;

public class BlackJackService {
    private DeakService deakService = new DeakService();
    private DealerService dealerService = new DealerService();
    private PlayerService playerService = new PlayerService();


    public void loadGame() {
        // deck
        deakService.buildDeck();
        deakService.shuffleDeck();
        System.out.println("Total de cartas: " + getDeck().size());

        // banca
        dealerService.buildDealer(deakService.getDeck());

        // jogador
        playerService.buildPlayer(deakService.getDeck());

        System.out.println("Total de cartas: " + deakService.getDeck().size());
    }

    public String message() {
        int dealerSum = dealerService.reduceAce();
        int playerSum = playerService.reduceAce();
        System.out.println("STAY: ");
        System.out.println(dealerSum);
        System.out.println(playerSum);

        if (playerSum > 21) {
            return "You Lose!";
        } else if (dealerSum > 21) {
            return "You Win!";
        } else if (playerSum == dealerSum) {
            return "Tie!";
        } else if (playerSum > dealerSum) {
            return "You Win!";
        } else if (playerSum < dealerSum) {
            return "You Lose!";
        } else
            return "OPPS! We had a problem!";
    }


    public List<Card> getPlayerHand() {
        return playerService.getHand();
    }
    public Card getPlayerHand(int index) {
        return playerService.getHand(index);
    }
    public List<Card> getDealerHand() {
        return dealerService.getHand();
    }
    public Card getDealerHand(int index) {
        return dealerService.getHand().get(index);
    }

    private List<Card> getDeck() {
        return deakService.getDeck();
    }

    public int getPlayerSum() {
        return playerService.getSum();
    }

    public String getPlayerFormattedSum() {
        return "YOU: %s".formatted(getPlayerSum());
    }

    public int getDealerSum() {
        return dealerService.getSum();
    }
    public String getDealerFormattedSum() {
        return "DEALER: %s".formatted(getDealerSum());

    }

    public Card getHiddenCard() {
        return dealerService.getHiddencard();
    }

    public void addDealerHand() {
        dealerService.addCardHand(deakService.getDeck());
    }

    public void addPlayerHand() {
        playerService.addCardHand(deakService.getDeck());
    }

    public int reducePlayerAce() {
        return playerService.reduceAce();
    }


}
