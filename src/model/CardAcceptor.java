package model;

import enums.ActionLetter;
import util.UniversalArray;

import java.util.Scanner;

public class CardAcceptor implements MoneyDealer{
    private String cardNumber="11111111";
    private String password="1234";
    private int amount=200;
    public CardAcceptor()  {

    }
    public int getAmount() {
        return amount;
    }

    public boolean purchasing(String action, UniversalArray<Product> products){

        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    amount = amount - products.get(i).getPrice();
                    System.out.println("Вы купили " + products.get(i).getName());
                    return true;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Недопустимая буква. Попрбуйте еще раз.");
        }
        return false;
    }
    public boolean checkInfo(String cardNumber,String password){
        if(cardNumber.equals(this.cardNumber) && password.equals(this.password)){
            return true;
        }
        return false;
    }

}
