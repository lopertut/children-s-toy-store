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
            System.out.print("Фамилия пользователя: ");
            user.setLastname(getString());
            System.out.print("Телефон пользователя: ");
            user.setPhone(getString());
            return user;
        } catch (Exception e) {
            System.out.println("Ошибка при создании пользователя: " + e.getMessage());
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
            System.out.printf("%d. %s %s. Телефон: %s%n", i + 1, user.getFirstname(), user.getLastname(), user.getPhone());
        }
        return true;
    }

    @Override
    public int delete(List<User> users) {
        try {
            if (users.isEmpty()) {
                System.out.println("Список пользователей пуст. Нечего удалять.");
                return -1;
            }
            System.out.print("Введите номер пользователя для удаления: ");
            int index = Integer.parseInt(getString().trim()) - 1;

            if (index < 0 || index >= users.size()) {
                System.out.println("Некорректный номер пользователя.");
                return -1;
            }

            User removedUser = users.remove(index);
            System.out.println("Пользователь \"" + removedUser.getFirstname() + " " + removedUser.getLastname() + "\" успешно удален.");
            return index;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректное значение.");
            return -1;
        }
    }

    @Override
    public User edit(List<User> users) {
        try {
            if (users.isEmpty()) {
                System.out.println("Список пользователей пуст. Нечего изменять.");
                return null;
            }

            System.out.print("Введите номер пользователя для изменения: ");
            int index = Integer.parseInt(getString().trim()) - 1;

            if (index < 0 || index >= users.size()) {
                System.out.println("Некорректный номер пользователя.");
                return null;
            }

            User userToEdit = users.get(index);

            System.out.print("Введите новое имя пользователя (текущее: " + userToEdit.getFirstname() + "): ");
            String newFirstName = getString();
            if (!newFirstName.isEmpty()) {
                userToEdit.setFirstname(newFirstName);
            }

            System.out.print("Введите новую фамилию пользователя (текущая: " + userToEdit.getLastname() + "): ");
            String newLastName = getString();
            if (!newLastName.isEmpty()) {
                userToEdit.setLastname(newLastName);
            }

            System.out.print("Введите новый телефон пользователя (текущий: " + userToEdit.getPhone() + "): ");
            String newPhone = getString();
            if (!newPhone.isEmpty()) {
                userToEdit.setPhone(newPhone);
            }

            System.out.println("Пользователь успешно изменен.");
            return userToEdit;

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректное значение.");
            return null;
        } catch (Exception e) {
            System.out.println("Ошибка при изменении пользователя: " + e.getMessage());
            return null;
        }
    }
}
