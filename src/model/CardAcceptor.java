package model;

import enums.ActionLetter;
import util.UniversalArray;

import java.util.Scanner;

public class CardAcceptor {
    private String cardNumber;
    private String password;
    private int amount=200;
    public CardAcceptor()  {

    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void loggingIn(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the card number: ");
        String cardNumber = scanner.nextLine();
        System.out.println("Please enter the password: ");
        String password = scanner.nextLine();
        if(cardNumber.equals(this.cardNumber) && password.equals(this.password)){
            System.out.println("You have successfully logged in!");
        }
    }
    public boolean purchasing(String action, CardAcceptor cardAcceptor, UniversalArray<Product> products){

        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    cardAcceptor.setAmount(cardAcceptor.getAmount() - products.get(i).getPrice());
                    System.out.println("Вы купили " + products.get(i).getName());
                    return true;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Недопустимая буква. Попрбуйте еще раз.");
        }
        return false;
    }
}
