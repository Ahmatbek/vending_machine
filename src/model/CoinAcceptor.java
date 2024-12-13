package model;

import enums.ActionLetter;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class CoinAcceptor {
    private int amount;
    private CoinAcceptor coinAcceptor;

    public CoinAcceptor(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void coinAdding(String msg, CoinAcceptor coinAcceptor) {
        if ("a".equalsIgnoreCase(msg)) {
            coinAcceptor.setAmount(coinAcceptor.getAmount() + 10);
            System.out.println("Вы пополнили баланс на 10");
            System.out.println(coinAcceptor.getAmount());
            return;
        }
    }
    public boolean purchasing(String action, CoinAcceptor coinAcceptor, UniversalArray<Product> products) {

        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    coinAcceptor.setAmount(coinAcceptor.getAmount() - products.get(i).getPrice());
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
