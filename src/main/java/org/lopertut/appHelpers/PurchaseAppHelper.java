package org.lopertut.appHelpers;

import org.lopertut.interfaces.AppHelper;
import org.lopertut.interfaces.*;
import org.lopertut.models.Purchase;
import org.lopertut.models.User;
import org.lopertut.models.Toy;

import java.time.LocalDate;
import java.util.List;

public class PurchaseAppHelper implements AppHelper<Purchase>, Input {

    @Override
    public Purchase create() {
        try {
            System.out.print("Введите имя покупателя: ");
            String firstName = getString();
            System.out.print("Введите фамилию покупателя: ");
            String lastName = getString();
            User user = new User(firstName, lastName, "");

            System.out.print("Введите название игрушки: ");
            String toyName = getString();
            System.out.print("Введите цену игрушки: ");
            int toyPrice = Integer.parseInt(getString());
            Toy toy = new Toy(toyName, toyPrice, null, 0);

            System.out.print("Введите дату покупки (гггг-мм-дд): ");
            String dateInput = getString();
            LocalDate purchaseDate = LocalDate.parse(dateInput);

            return new Purchase(user, toy, purchaseDate);
        } catch (Exception e) {
            System.out.println("Ошибка при создании покупки: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean printList(List<Purchase> purchases) {
        if (purchases == null || purchases.isEmpty()) {
            System.out.println("Список покупок пуст.");
            return false;
        }
        System.out.println("---------- Список покупок --------");
        for (int i = 0; i < purchases.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, purchases.get(i).toString());
        }
        return true;
    }
}
