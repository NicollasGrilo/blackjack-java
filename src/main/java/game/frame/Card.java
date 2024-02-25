package game.frame;


import java.util.Objects;

public class Card {
    String value;
    String type;

    public Card(String value, String type) {
        this.value = value;
        this.type = type;
    }
    public int getValue() {
        if ("AJQK".contains(value)) { // A J Q K
            if (value.equals("A")) {
                return 11;
            }
            return 10;
        }
        return Integer.parseInt(value); // 2-10
    }

    public boolean isAce() {
        return Objects.equals(value, "A");
    }

    public String getImagePath() {
        return "/cards/%s.png".formatted(toString());
    }
    public String toString() {
        return value + "-" + type;
    }

}
