import enums.ActionLetter;
import model.*;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.List;
import java.util.Scanner;

public class MoneyAcceptor {

    private final UniversalArray<Product> products = new UniversalArrayImpl<>();

    private final CoinAcceptor coinAcceptor;
    private final CardAcceptor cardAcceptor;

    private static boolean isExit = false;
    private   MoneyAcceptor(){
        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
        coinAcceptor = new CoinAcceptor(100);
        cardAcceptor = new CardAcceptor();


    }

    public static void run() {
        MoneyAcceptor app = new MoneyAcceptor();
        while (!isExit) {
            String method;
            while (true) {
                try {
                    System.out.println("Enter payment method (card or coin): ");
                    Scanner scanner = new Scanner(System.in);
                    method = scanner.nextLine();
                    if (method.equals("card") || method.equals("coin")) {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println();
                }
            }
            app.startSimulation(method);
        }
    }

    private void startSimulation(String method) {
        print("В автомате доступны:");
        showProducts(products);
        String payment = "";
        if(method.equals("coin")) {
            payment= method;
            print("Монет на сумму: " + coinAcceptor.getAmount());
        }
        else if(method.equals("card")) {
            payment = method;
            print("The balance in the card: "+cardAcceptor.getAmount());
        }


        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts(method).toArray());
        chooseAction(allowProducts,payment);

    }

    private UniversalArray<Product> getAllowedProducts(String method) {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if(method.equals("coin")) {
                if (coinAcceptor.getAmount() >= products.get(i).getPrice()) {
                    allowProducts.add(products.get(i));
                }
            }
            else if(method.equals("card")) {
                if (cardAcceptor.getAmount() >= products.get(i).getPrice()) {
                    allowProducts.add(products.get(i));
                }
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products, String method) {

        if (method.equals("coin")) {
            print(" a - add to balance");

        }
        showActions(products);
        print(" h - Выйти");
        String action = fromConsole().substring(0, 1);
        if (method.equals("coin")) {
            coinAcceptor.coinAdding(action,coinAcceptor);
        }


        if ("h".equalsIgnoreCase(action)) {
            isExit = true;
            return;
        }
        if(method.equals("coin")) {
            if(!coinAcceptor.purchasing(action,coinAcceptor, products)) {
                chooseAction(products,method);
            }
        }
       if (method.equals("card")) {
           if(!cardAcceptor.purchasing(action,cardAcceptor,products)) {
               showActions(products);
           }
       }
       else {
           chooseAction(products,method);
       }



    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }


}
