package model;

import enums.ActionLetter;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class CoinAcceptor implements MoneyDealer {
    private int amount;

    public CoinAcceptor(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void coinAdding(String msg) {
        if ("a".equalsIgnoreCase(msg)) {
            amount=amount + 10;
            System.out.println("Вы пополнили баланс на 10");
        }
    }
    public boolean purchasing(String action, UniversalArray<Product> products) {
        if(action.equalsIgnoreCase("a")) return true;
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
            return false;
        }
        return false;
    }
}
