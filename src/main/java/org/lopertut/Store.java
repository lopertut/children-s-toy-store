package org.lopertut;

import org.lopertut.interfaces.*;
import org.lopertut.appHelpers.*;
import org.lopertut.models.*;
import org.lopertut.services.*;
import org.lopertut.storage.Storage;


public class Store {
    public static void main(String[] args) {
        AppHelper<Toy> toyAppHelper = new ToyAppHelper();
        AppHelper<User> userAppHelper = new UserAppHelper();
        FileRepository<Toy> toyStorage = new Storage<>();
        FileRepository<User> userStorage = new Storage<>();
        Service<Toy> toyService = new ToyService(toyAppHelper, toyStorage);
        Service<User> userService = new UserService(userAppHelper, userStorage);
        App app = new App(toyService, userService);
        app.run();
    }
}
