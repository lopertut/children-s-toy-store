package org.lopertut;
import java.util.Scanner;


public class App {
    public  void run() {
        System.out.println("--------Childrens toy store--------");
        boolean repeat = true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("List of tasks: ");
            System.out.println("0. Exit");
            System.out.println("1. Add task");
            System.out.println("2. Remove task");
            System.out.println("Enter task number: ");
            int task = scanner.nextInt();

            switch (task) {
                case 0:
                    repeat = false;
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    System.out.println("task 1");
                    break;
                case 2:
                    System.out.println("task 2");
                    break;
                default:
                    System.out.println("Invalid task number");
                    break;
            }
        } while (repeat);
    }
}
