import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppData {

    static ArrayList<Product> marketProducts = new ArrayList<>();
    static HashMap<User, ArrayList<Product>> userData = new HashMap<>();

    public static void addProduct() {
        marketProducts.add(new Product());
    }

    public static void addUser() {
        userData.put(new User(), new ArrayList<>());
    }

    public static double getPrice(long productId) {
        double price = 0;
        do {
            for (Product p :
                    marketProducts) {
                if (p.id == productId) {
                    price = p.price;
                }
            }
            if (price == 0) {
                System.err.println("Wrong product ID or no such product");
            }
        } while (price == 0);
        return price;
    }

    public static User findUserId() {
        long userId = validateInputId("Enter user ID:");
        User foundUser = null;
        do {
            for (User user : userData.keySet()) {
                if (user.id == userId) {
                    foundUser = user;
                }
            }
            if (foundUser == null) {
                System.out.println("Wrong user ID or no such user");
                userId = validateInputId("Enter user ID:");
            }
        } while (foundUser == null);
        return foundUser;
    }

    public static Product findProductId(long productId) {
        for (Product p : marketProducts) {
            if (p.id == productId) {
                return p;
            }
        }
        return null;
    }

    public static void addProductToUser(User user, Product product) {
        ArrayList<Product> userProducts = userData.get(user);
        userProducts.add(product);
    }

    public static void displayUser() {
        for (User user : userData.keySet()) {
            System.out.println(user.toString());
        }
    }

    public static void displayProduct(ArrayList<Product> productsList) {
        for (Product product : productsList) {
            System.out.println(product.toString());
        }
    }

    public static void displayUserProducts() {
        displayProduct(userData.get(findUserId()));
    }

    public static void displayUserByProductId() {
        long productId = validateInputId("Enter product ID:");

        for (User user : userData.keySet()) {
            for (Product product : userData.get(user)) {
                if (product.id == productId) {
                    System.out.println(user.toString());
                }
            }
        }
        System.err.println("Wrong product ID or no such product");
    }

    public static void deleteProduct() {
        long productId = validateInputId("Enter product ID:");
        List<Product> foundInMarket = new ArrayList<>();
        for (Product product : marketProducts) {
            if (product.id == productId) {
                foundInMarket.add(product);
            }
        }
        if (foundInMarket.isEmpty()) {
            System.err.println("Wrong product ID or no such product");
            return;
        }
        marketProducts.removeAll(foundInMarket);

        List<Product> foundInUser = new ArrayList<>();
        for (User user : userData.keySet()) {
            for (Product product : userData.get(user)) {
                if (product.id == productId) {
                    foundInUser.add(product);
                }
            }
            userData.get(user).removeAll(foundInUser);
        }
    }

    public static void deleteUser() {
        userData.remove(findUserId());
    }

    public static long validateInputId(String inputMessage) {
        System.out.println(inputMessage);

        while (!AppDemo.scanner.hasNextLong()) {
            AppDemo.scanner.next();
            System.err.println("Wrong format");
            System.out.println(inputMessage);
        }
        long inputId = AppDemo.scanner.nextLong();
        return inputId;
    }
}
