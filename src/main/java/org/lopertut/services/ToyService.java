package org.lopertut.services;

import java.util.List;

import org.lopertut.appHelpers.ToyAppHelper;
import org.lopertut.models.Toy;
import org.lopertut.interfaces.AppHelper;
import org.lopertut.interfaces.FileRepository;
import org.lopertut.interfaces.Service;

public class ToyService implements Service<Toy> {

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
    public boolean edit(Toy toy) {
        return false;
    }

    @Override
    public boolean remove(Toy toy) {
        try {
            List<Toy> toys = storage.load(fileName);  // Загружаем все игрушки
            boolean removed = toys.removeIf(existingToy -> existingToy.getId().equals(toy.getId()));
            if (removed) {
                storage.saveAll(toys, fileName);  // Сохраняем обновленный список
                System.out.println("Игрушка успешно удалена.");
            } else {
                System.out.println("Игрушка не найдена.");
            }
            return removed;
        } catch (Exception e) {
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