package org.lopertut;

import org.lopertut.interfaces.Input;
import org.lopertut.interfaces.Service;
import org.lopertut.models.*;


public class App implements Input {

    private final Service<Toy> toyService;
    private final Service<User> userService;

    public App(Service<Toy> toyService, Service<User> userService) {
        this.toyService = toyService;
        this.userService = userService;
    }

    public  void run() {
        System.out.println("--------Childrens toy store--------");
        boolean repeat = true;
        do {
            System.out.println("List of tasks: ");
            System.out.println("0. Exit");
            System.out.println("1. Toys list");
            System.out.println("2. Add toy");
            System.out.println("3. Edit toy");
            System.out.println("4. Delete toy");
            System.out.println("5. User list");
            System.out.println("6. Add user");
            System.out.println("7. Edit user");
            System.out.println("8. Delete user");
            System.out.println("9. Buy toy");
            System.out.println("0. history of bought toys");

            System.out.println("Enter task number: ");
            int task = Integer.parseInt(getString());

            switch (task) {
                case 0:
                    repeat = false;
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    System.out.println("task 1");
                    toyService.print();
                    break;
                case 2:
                    System.out.println("task 2");
                    if (toyService.add()) {
                        System.out.println("Added toy");
                    } else {
                        System.out.println("Not added toy");
                    }
                    break;
                case 3:
                    System.out.println("task 3");
                    // edit toy
                    break;
                case 4:
                    System.out.println("task 4");
                    // delete toy
                    break;
                case 5:
                    System.out.println("task 5");
                    userService.list();
                    break;
                case 6:
                    System.out.println("task 6");
                    if (userService.add()) {
                        System.out.println("Added user");
                    } else {
                        System.out.println("Not added user");
                    }
                    break;
                default:
                    System.out.println("Invalid task number");
                    break;
            }
        } while (repeat);
    }
}
