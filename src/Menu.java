public class Menu {

    final static String[][] MENU = {
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

    public static void displayMenu() {
        for (String[] s:
             MENU) {
            System.out.printf("%s. %s%n", s[0], s[1]);
        }
    }
}
