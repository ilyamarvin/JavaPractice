package ru.mirea.task21_22;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        User interfacing = new User();
        ItemStore localStore = new LocalStore();
        ItemStore serverStore = new ServerStore();
        Scanner scanner = new Scanner(System.in);
        int change = 0;
        while (change != 3) {
            System.out.print(interfacing.printChangeJson().toString());
            change = scanner.nextInt();
            switch (change) {
                case 1:
                    while (change != 6) {
                        System.out.print(interfacing.printChangeLocalJson().toString());
                        change = getChange(interfacing, localStore, scanner, localStore.get(change));
                    }
                    change = 0;
                    break;
                case 2:
                    while (change != 6) {
                        System.out.print(interfacing.printChangeHttpJson().toString());
                        change = getChange(interfacing, serverStore, scanner, localStore.get(change));
                    }
                    change = 0;
                    break;
                case 3:
                    System.out.println(interfacing.printExit().toString());
                    break;
                default:
                    System.out.println(interfacing.printError().toString());
                    break;
            }
        }
    }

    private static int getChange(User user, ItemStore localStore, Scanner scanner, Item item) {
        int change;
        String line;
        change = scanner.nextInt();
        switch (change) {
            case 1:
                System.out.print(user.printGetAllItem());
                System.out.println("" + localStore.getAll());
                break;
            case 2:
                System.out.print(user.printGetItem());
                change = scanner.nextInt();
                if ((localStore.get(change)) != null) {
                    System.out.println(localStore.get(change));
                } else System.out.println(user.printGetError().toString());
                break;
            case 3:
                System.out.println(user.printAddItem());
                scanner.nextLine();
                line = scanner.nextLine();
                System.out.println("" +
                        (localStore.addItem(new Item(Integer.parseInt(line.split(" ")[0]),
                                line.split(" ")[1],
                                line.split(" ")[2]))
                                ? user.printAddSuccessful().toString()
                                : user.printAddError().toString()));
                break;
            case 4:
                System.out.println(user.printEditItem());
                scanner.nextLine();
                line = scanner.nextLine();
                System.out.println("" +
                        (localStore.editItem(
                                Integer.parseInt(line.split(" ")[0]),
                                new Item(Integer.parseInt(line.split(" ")[1]),
                                        line.split(" ")[2],
                                        line.split(" ")[3]))
                                ? user.printEditSuccessful().toString()
                                : user.printEditError().toString()));
                break;
            case 5:
                System.out.println(user.printDeleteItem());
                change = scanner.nextInt();
                System.out.println("" +
                        (localStore.deleteItem(change)
                                ? user.printDeleteSuccessful().toString()
                                : user.printDeleteError().toString()));
                break;
            case 6:
                System.out.println(user.printExit().toString());
                break;
            default:
                System.out.println(user.printError().toString());
                break;
        }
        return change;
    }
}
