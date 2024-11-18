package org.lopertut.appHelpers;

import org.lopertut.models.User;
import org.lopertut.interfaces.AppHelper;
import org.lopertut.interfaces.Input;
import java.util.List;


public class UserAppHelper implements AppHelper<User>, Input {
    @Override
    public User create() {
        try {
            User user = new User();
            System.out.print("Имя пользователя: ");
            user.setFirstname(getString());
            System.out.println("Фамилия пользователя: ");
            user.setLastname(getString());
            System.out.println("Телефон пользователя: ");
            user.setPhone(getString());
            return user;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean printList(List<User> users) {
        if (users == null || users.isEmpty()) {
            System.out.println("Список пользователей пуст.");
            return false;
        }
        System.out.println("---------- Список пользователей --------");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.printf("%d. %s %s. %s%n", i + 1, user.getFirstname(), user.getLastname(), user.getPhone());
        }
        return true;
    }

    @Override
    public int delete(List<User> elements) {
        return 0;
    }

    @Override
    public User edit(List<User> elements) {
        return null;
    }
}
