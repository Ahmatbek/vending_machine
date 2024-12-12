package model;

public class CardAcceptor {
    private int cardNumber;
    private int password;
    public CardAcceptor(int cardNumber, int password) {
        this.cardNumber = cardNumber;
        this.password = password;
    }
    public int getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    public int getPassword() {
        return password;
    }
    public void setPassword(int password) {
        this.password = password;
    }
}
