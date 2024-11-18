package org.lopertut.services;

import java.util.List;

import org.lopertut.appHelpers.ToyAppHelper;
import org.lopertut.interfaces.Input;
import org.lopertut.models.Toy;
import org.lopertut.interfaces.AppHelper;
import org.lopertut.interfaces.FileRepository;
import org.lopertut.interfaces.Service;

public class ToyService implements Service<Toy>, Input {

    private final AppHelper<Toy> appHelperToy;
    private final String fileName = "toys";
    private final FileRepository<Toy> storage;

    public ToyService(AppHelper<Toy> appHelperToy, FileRepository<Toy> storage) {
        this.appHelperToy = appHelperToy;
        this.storage = storage;
    }

    @Override
    public boolean add() {
        try {
            Toy toy = appHelperToy.create();
            if (toy == null) { return false; }
            storage.save(toy, fileName);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit() {
        try {
            List<Toy> toys = storage.load(fileName);
            Toy toy = appHelperToy.edit(toys);

            if (toys.isEmpty()) {
                System.out.println("Список игрушек пуст. Нечего удалять.");
                return false;
            }

            toys.set(toys.indexOf(toy), toy );
            // Сохранение обновленного списка обратно в файл
            storage.save(toy, fileName);
            return true;

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введено некорректное значение. Пожалуйста, введите число.");
            return false;
        } catch (Exception e) {
            // Обработка любых других исключений
            System.out.println("Ошибка при удалении игрушки: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove() {
        try {
            // Запрос индекса у пользователя
            System.out.print("Введите номер игрушки для удаления: ");
            int index = Integer.parseInt(getString().trim());

            // Загрузка списка игрушек из файла
            List<Toy> toys = storage.load(fileName);

            if (toys.isEmpty()) {
                System.out.println("Список игрушек пуст. Нечего удалять.");
                return false;
            }

            // Проверка корректности индекса
            if (index < 0 || index >= toys.size()) {
                System.out.println("Некорректный номер игрушки.");
                return false;
            }

            // Удаление игрушки по индексу
            Toy toyToRemove = toys.remove(index);

            // Сохранение обновленного списка обратно в файл
            storage.saveAll(toys, fileName);

            // Вывод сообщения об успешном удалении
            System.out.println("Игрушка \"" + toyToRemove.getName() + "\" успешно удалена.");
            return true;

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введено некорректное значение. Пожалуйста, введите число.");
            return false;
        } catch (Exception e) {
            // Обработка любых других исключений
            System.out.println("Ошибка при удалении игрушки: " + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean print() {
        return appHelperToy.printList(this.list());
    }

    @Override
    public List<Toy> list() {
        return storage.load(fileName);
    }
}