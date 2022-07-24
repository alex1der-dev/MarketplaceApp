import java.util.Scanner;

public class AppDemo {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String choice = null;

        boolean isFinished = false;
        do {
            Menu.displayMenu();
            choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    AppData.addUser();
                    scanner.nextLine();
                }
                case "2" -> {
                    AppData.addProduct();
                    scanner.nextLine();
                }
                case "3" -> AppData.displayUser();
                case "4" -> AppData.displayProduct(AppData.marketProducts);
                case "5" -> {
                    AppData.findUserId().buy();
                    scanner.nextLine();
                }
                case "6" -> {
                    AppData.displayUserProducts();
                    scanner.nextLine();
                }
                case "7" -> {
                    AppData.displayUserByProductId();
                    scanner.nextLine();
                }
                case "8" -> {
                    AppData.deleteProduct();
                    scanner.nextLine();
                }
                case "9" -> AppData.deleteUser();
                case "0" -> {
                    isFinished = true;
                    scanner.close();
                }
                default -> System.err.println("Wrong option");
            }
        }
        while (!isFinished);
    }
}
