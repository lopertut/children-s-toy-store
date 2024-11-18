package org.lopertut.services;

import org.lopertut.models.User;
import org.lopertut.interfaces.Input;
import org.lopertut.interfaces.AppHelper;
import org.lopertut.interfaces.Service;
import org.lopertut.interfaces.FileRepository;

import java.util.List;

public class UserService implements Service<User>, Input {
    private final String fileName = "users";
    private final AppHelper<User> userAppHelper;
    private final FileRepository<User> storage;

    public UserService(AppHelper<User> userAppHelper, FileRepository<User> storage) {
        this.storage = storage;
        this.userAppHelper = userAppHelper;
    }

    @Override
    public boolean add() {
        try {
            User user = userAppHelper.create();
            if (user == null) {
                System.out.println("Добавление пользователя не выполнено.");
                return false;
            }
            storage.save(user, fileName);
            System.out.println("Пользователь успешно добавлен.");
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении пользователя: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean edit() {
        try {
            List<User> users = storage.load(fileName);

            if (isListEmpty(users)) {
                return false;
            }

            User updatedUser = userAppHelper.edit(users);

            if (updatedUser != null) {
                storage.saveAll(users, fileName);
                System.out.println("Пользователь успешно изменен.");
                return true;
            }

            System.out.println("Изменение пользователя не выполнено.");
            return false;

        } catch (Exception e) {
            System.out.println("Ошибка при изменении пользователя: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove() {
        try {
            List<User> users = storage.load(fileName);

            if (isListEmpty(users)) {
                return false;
            }

            int index = userAppHelper.delete(users);

            if (index >= 0) {
                storage.saveAll(users, fileName);
                System.out.println("Пользователь успешно удален.");
                return true;
            }

            System.out.println("Удаление пользователя не выполнено.");
            return false;

        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean print() {
        return userAppHelper.printList(this.list());
    }

    @Override
    public List<User> list() {
        return storage.load(fileName);
    }

    private boolean isListEmpty(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("Список пользователей пуст.");
            return true;
        }
        return false;
    }
}
