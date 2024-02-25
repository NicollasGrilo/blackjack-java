package game.frame;

import game.services.BlackJackService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.requireNonNull;

public class GamePanel extends JPanel {
    public static final String CARDS_BACK_PNG = "/cards/BACK.png";
    private int cardWidth = 110;
    private int cardHeight = 154;
    private JFrame frame = new JFrame("Black Jack");
    private JButton hitButton = new JButton("HIT ME");
    private JButton standButton = new JButton("STAND");
    private JButton restartButton = new JButton("RESTART");
    private BlackJackService blackJackService = new BlackJackService();

    private AtomicInteger counterDealer = new AtomicInteger(-1);
    private AtomicInteger counterPlayer = new AtomicInteger(-1);

    public GamePanel() {
        loadGraphics();
        getFrame().add(this);
    }
    private void loadGraphics() {
        blackJackService.loadGame();
        setLayout(new BorderLayout());
        setBackground(new Color(53, 101, 77));
        setLayout(null);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        counterPlayer.set(-1);
        counterDealer.set(-1);

        try {
            // desenhando a carta escondida
            hiddenCard(g);

            // desenhando a mão da banca
            blackJackService.getDealerHand().stream()
                    .forEach(card->{
                        drawDealerHand(g, card);
                    });

            // desenhando a mao do jogador
            blackJackService.getPlayerHand().stream()
                    .forEach(card->{
                        drawPlayerHand(g, card);
                    });

            if (!standButton.isEnabled())
                showMessage(blackJackService.message(), g);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void actionHitButton(GamePanel gamePanel) {
        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                blackJackService.addPlayerHand();
                if (blackJackService.reducePlayerAce() > 21)
                    hitButton.setEnabled(false);

                gamePanel.repaint();
            }
        });
    }
    public void actionStandButton(GamePanel gamePanel) {
        standButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false);
                standButton.setEnabled(false);

                blackJackService.addDealerHand();

                gamePanel.repaint();
            }
        });
    }

    public void actionRestartButton(GamePanel gamePanel) {
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadGraphics();
                hitButton.setEnabled(true);
                standButton.setEnabled(true);

                gamePanel.repaint();
            }
        });
    }
    private void drawPlayerHand(Graphics g, Card card) {
        Image image = getImage(card.getImagePath());
        g.drawImage(image, 20 + (cardWidth + 5) * counterPlayer.incrementAndGet(), 320, cardWidth, cardHeight, null);

        labelScore(g, blackJackService.getPlayerFormattedSum(), 550, 400);
    }
    private void drawDealerHand(Graphics g, Card card) {
        Image image = getImage(card.getImagePath());
        g.drawImage(image, cardWidth + 25 + (cardWidth + 5) * counterDealer.incrementAndGet(), 20, cardWidth, cardHeight, null);

        if (!standButton.isEnabled())
            labelScore(g, blackJackService.getDealerFormattedSum(), 550, 100);

    }
    private void hiddenCard(Graphics g) {
        Image hiddenCardImg = getImage(CARDS_BACK_PNG);
        if (!standButton.isEnabled())
            hiddenCardImg = getImage(blackJackService.getHiddenCard().getImagePath());;

        g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);
    }

    private void showMessage(String message, Graphics graphics) {
        graphics.setFont(new Font("Arial", Font.PLAIN, 30));
        graphics.setColor(Color.white);
        graphics.drawString(message, 220, 250);
    }
    private void labelScore(Graphics graphics, String blackJackService, int x, int y) {
        graphics.setFont(new Font("Arial", Font.PLAIN, 30));
        graphics.setColor(Color.white);
        graphics.drawString(blackJackService, x, y);
    }
    private static Image getImage(String path) {
        URL url = requireNonNull(GamePanel.class.getResource(path));
        return new ImageIcon(url).getImage();
    }

    private JFrame getFrame() {
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(53, 101, 77));
        this.setLayout(null);

        this.buildHitButton();
        this.buildStandButton();
        this.buildRestartButton();

        frame.add(this);
        return frame;
    }

    private JButton buildHitButton() {
        hitButton.setFocusable(false);
        hitButton.setBounds(160, 500, 100, 50);
        hitButton.setFont(new Font("Arial", Font.BOLD, 15));
        hitButton.setForeground(new Color(255, 255, 255));
        hitButton.setBackground(new Color(243, 70, 15));
        add(hitButton);
        return hitButton;
    }

    private JButton buildStandButton() {
        standButton.setFocusable(false);
        standButton.setBounds(270, 500, 100, 50);
        standButton.setFont(new Font("Arial", Font.BOLD, 15));
        standButton.setForeground(new Color(255, 255, 255));
        standButton.setBackground(new Color(243, 70, 15));
        add(standButton);
        return standButton;
    }

    private JButton buildRestartButton() {
        restartButton.setFocusable(false);
        restartButton.setBounds(380, 500, 150, 50);
        restartButton.setFont(new Font("Arial", Font.BOLD, 15));
        restartButton.setForeground(new Color(255, 255, 255));
        restartButton.setBackground(new Color(243, 70, 15));
        add(restartButton);
        return restartButton;
    }



}
