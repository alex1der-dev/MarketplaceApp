package com.gson.view;

public class MainView {
    private final static String[][] MENU = {
            {"1", "Add new user"},
            {"2", "Add new product"},
            {"3", "Display list of all users"},
            {"4", "Display list of all products"},
            {"5", "Buy product"},
            {"6", "Display list of user products"},
            {"7", "Display list of users, that bought product"},
            {"8", "Delete product"},
            {"9", "Delete user"},
            {"0", "Quit"}
    };

    private final ConsoleReader consoleReader = new ConsoleReader();
    private final ProductView productView = new ProductView();
    private final UserView userView = new UserView();

    public void displayMenu() {
        System.out.println("\tMAIN MENU");
        for (String[] s :
                MENU) {
            System.out.printf("%s. %s%n", s[0], s[1]);
        }
    }

    public void performMenu() {
        String choice;

        boolean isFinished = false;
        do {
            displayMenu();
            System.out.println("Enter a number to select an option: ");
            choice = consoleReader.readTextValue();

            switch (choice) {
                case "1":
                    userView.addUser();
                    break;
                case "2":
                    productView.addNewProduct();
                    break;

                case "3":
                    userView.displayAllUsers();
                    break;

                case "4":
                    productView.displayAllProducts();
                    break;

                case "5":
                    userView.buyProduct();
                    break;

                case "6":
                    userView.displayUserProducts();
                    break;

                case "7":
                    userView.displayUsersHaveProduct();
                    break;

                case "8":
                    productView.deleteProduct();
                    break;

                case "9":
                    userView.deleteUser();
                    break;
                case "0":
                    isFinished = true;
                    consoleReader.closeScanner();

                default:
                    System.err.println("Wrong option");
            }
        }
        while (!isFinished);
    }
}


