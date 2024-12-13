package model;

import util.UniversalArray;

public interface MoneyDealer {
     boolean purchasing(String action, UniversalArray<Product> products);
}
