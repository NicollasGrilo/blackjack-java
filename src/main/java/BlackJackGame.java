import game.frame.GamePanel;

public class BlackJackGame {
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel(); // load card in the memory and create components of the screen

        gamePanel.actionHitButton(gamePanel); // execute when click on the card hit button

        gamePanel.actionStandButton(gamePanel); // execute when click on the card stand button

        gamePanel.actionRestartButton(gamePanel); // execute when click on the card restart button

        gamePanel.repaint(); // from the created components and cards in the memory this method draw the screen
    }
}