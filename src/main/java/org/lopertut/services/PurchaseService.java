package org.lopertut.services;

import org.lopertut.interfaces.AppHelper;
import org.lopertut.interfaces.FileRepository;
import org.lopertut.interfaces.Service;
import org.lopertut.models.Purchase;
import org.lopertut.models.Toy;

import java.util.List;

public class PurchaseService implements Service<Purchase> {
    private final AppHelper<Purchase> appHelperPurchase;
    private final String fileName = "purchase";
    private final FileRepository<Purchase> storage;

    public PurchaseService(AppHelper<Purchase> appHelperPurchase, FileRepository<Purchase> storage) {
        this.storage = storage;
        this.appHelperPurchase = appHelperPurchase;
    }

    @Override
    public boolean add() {
        try {
            Purchase purchase = appHelperPurchase.create();
            if (purchase != null) {
                storage.save(purchase, fileName);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit(Purchase entity) {
        return false;
    }


    @Override
    public boolean remove(Purchase entity) {
        return false;
    }

    @Override
    public boolean print() {
        return appHelperPurchase.printList(this.list());
    }

    @Override
    public List<Purchase> list() {
        return storage.load(fileName);
    }
}

