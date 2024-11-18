package org.lopertut.services;

import org.lopertut.models.User;
import org.lopertut.interfaces.Input;
import org.lopertut.interfaces.AppHelper;
import org.lopertut.interfaces.Service;
import org.lopertut.interfaces.FileRepository;
import java.util.List;


public class UserService implements Service<User>, Input {
    private final String fileName="users";
    private final AppHelper<User> userAppHelper;
    private final FileRepository<User> storage;

    public UserService(AppHelper<User> userAppHelper, FileRepository<User> storage) {
        this.storage = storage;
        this.userAppHelper = userAppHelper;
    }

    @Override
    public boolean add() {
        User user = userAppHelper.create();
        if(user == null) {return false;}
        storage.save(user,fileName);
        return true;
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public boolean remove() {
        return false;
    }

    @Override
    public boolean print() {
        return userAppHelper.printList(this.list());
    }

    @Override
    public List<User> list() {
        return storage.load(fileName);
    }
}
