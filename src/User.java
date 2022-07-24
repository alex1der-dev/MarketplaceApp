public class User {

    long id;
    String firstName;
    String lastName;
    double moneyAmount;

    public User() {
        this.id = System.currentTimeMillis();
        this.firstName = validateStringFieldInput("Enter first name:");
        this.lastName = validateStringFieldInput("Enter last name:");

        System.out.println("Enter user money amount:");
        while (!AppDemo.scanner.hasNextDouble()) {
            AppDemo.scanner.next();
            System.err.println("Wrong format");
            System.out.println("Enter user money amount:");
        }
        this.moneyAmount = AppDemo.scanner.nextDouble();
    }

    private String validateStringFieldInput(String inputMessage) {
        boolean isValid = false;
        String field = null;
        while(!isValid) {
            try {
                System.out.println(inputMessage);
                field = AppDemo.scanner.nextLine();
                isValid = field.matches("^[a-zA-Z\\-\\.\\']*$") && field.length() > 0;
                if(!isValid) {throw new IllegalArgumentException("Wrong format");}
            } catch (IllegalArgumentException e){
                System.err.println(e.getLocalizedMessage());
            }
        }
        return field;
    }

    public void buy() {
        System.out.println("Enter product ID:");

        while (!AppDemo.scanner.hasNextLong()) {
            AppDemo.scanner.next();
            System.err.println("Wrong format");
            System.out.println("Enter product ID:");
        }
        long productId = AppDemo.scanner.nextLong();
        try {
            if (AppData.getPrice(productId) > this.moneyAmount) {
                throw new IllegalArgumentException("User has not enough money");
            } else {
                this.moneyAmount -= AppData.getPrice(productId);
                AppData.addProductToUser(this, AppData.findProductId(productId));
                System.out.printf("product ID%d was successfully purchased%n", productId);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public String toString() {
        String result = id + " " + firstName + " " + lastName + " " + moneyAmount;
        return result;
    }
}
