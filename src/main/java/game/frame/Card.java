package game.frame;


import java.util.Objects;

public class Card implements Comparable<Card> {
    public String value;

    public String type;

    public Card() {
    }

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

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(value, card.value) && Objects.equals(type, card.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    @Override
    public int compareTo(Card card) {
        String valueThis = this.getType();
        String valueCard = card.getType();
        return valueCard.compareTo(valueThis);
    }
}
