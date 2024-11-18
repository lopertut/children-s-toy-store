package org.lopertut.appHelpers;

import org.lopertut.models.Toy;
import org.lopertut.interfaces.AppHelper;
import org.lopertut.interfaces.Input;
import java.util.List;
import java.util.UUID;


public class ToyAppHelper implements AppHelper<Toy>, Input {

    @Override
    public Toy create() {
        Toy toy = new Toy();
        System.out.print("Название игрушки: ");
        toy.setName(getString());

        System.out.print("Укажите количество материалов: ");
        int countMaterials = Integer.parseInt(getString());
        for (int i = 0; i < countMaterials; i++) {
            System.out.printf("Введите название материала (%d из %d): ", i + 1, countMaterials);
            String material = getString();
            toy.getMaterials().add(material);
        }

        System.out.print("Рекомендуемый возраст: ");
        toy.setRecommendedAge(Integer.parseInt(getString()));

        System.out.print("Цена игрушки: ");
        toy.setPrice(Integer.parseInt(getString()));

        return toy;
    }

    @Override
    public boolean printList(List<Toy> toys) {
        if (toys == null || toys.isEmpty()) {
            System.out.println("Список игрушек пуст.");
            return false;
        }
        StringBuilder sbToys = new StringBuilder();
        for (int i = 0; i < toys.size(); i++) {
            Toy toy = toys.get(i);
            if (toy == null) {
                continue;
            }
            StringBuilder sbMaterialsToy = new StringBuilder();
            for (String material : toy.getMaterials()) {
                if (material != null) {
                    sbMaterialsToy.append(material).append(" ");
                }
            }
            sbToys.append(String.format("%d. %s. Материалы: %s. Рекомендуемый возраст: %d. Цена: %s%n",
                    i + 1,
                    toy.getName(),
                    sbMaterialsToy.toString().trim(),
                    toy.getRecommendedAge(),
                    toy.getPrice()
            ));
        }
        System.out.println(sbToys);
        return true;
    }

    @Override
    public int delete(List<Toy> toys) {
        // Запрос индекса у пользователя
        System.out.print("Введите номер игрушки для удаления: ");
        int index = Integer.parseInt(getString().trim());

        if (toys.isEmpty()) {
            System.out.println("Список игрушек пуст. Нечего удалять.");
        }

        // Проверка корректности индекса
        if (index < 0 || index >= toys.size()) {
            System.out.println("Некорректный номер игрушки.");
        }
        return index;
    }


    @Override
    public Toy edit(List<Toy> toys) {
        // Запрос индекса у пользователя
        System.out.print("Введите номер игрушки для изменения: ");
        int index = Integer.parseInt(getString().trim());

        if (toys.isEmpty()) {
            System.out.println("Список игрушек пуст. Нечего изменять.");
        }

        // Проверка корректности индекса
        if (index < 0 || index >= toys.size()) {
            System.out.println("Некорректный номер игрушки.");
        }
        // Получаем игрушку по индексу
        Toy toyToEdit = toys.get(index);

        // Изменение свойств игрушки
        System.out.print("Введите новое название игрушки (текущее: " + toyToEdit.getName() + "): ");
        toyToEdit.setName(getString());

        System.out.print("Введите новое количество материалов (текущее: " + toyToEdit.getMaterials().size() + "): ");
        int countMaterials = Integer.parseInt(getString());
        toyToEdit.getMaterials().clear(); // Очищаем текущий список материалов
        for (int i = 0; i < countMaterials; i++) {
            System.out.printf("Введите название материала (%d из %d): ", i + 1, countMaterials);
            String material = getString();
            toyToEdit.getMaterials().add(material);
        }

        System.out.print("Введите новый рекомендуемый возраст (текущий: " + toyToEdit.getRecommendedAge() + "): ");
        toyToEdit.setRecommendedAge(Integer.parseInt(getString()));

        System.out.print("Введите новую цену игрушки (текущая: " + toyToEdit.getPrice() + "): ");
        toyToEdit.setPrice(Integer.parseInt(getString()));

        return toyToEdit;
    }
}