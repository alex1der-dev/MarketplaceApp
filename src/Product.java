import java.util.ArrayList;

public class Product extends ArrayList<Product> {

    long id;
    String name;
    double price;

    Product () {
        this.id = System.currentTimeMillis();

        boolean isValid = false;

        while(!isValid) {
            try {
                System.out.println("Enter product name:");
                this.name = AppDemo.scanner.nextLine();
                isValid = this.name != null && !this.name.isBlank();
                if(!isValid) {throw new IllegalArgumentException("Wrong format");}
            } catch (IllegalArgumentException e){
                System.err.println(e.getLocalizedMessage());
            }
        }

        System.out.println("Enter product price:");

        while (!AppDemo.scanner.hasNextDouble()) {
            AppDemo.scanner.next();
            System.err.println("Wrong format");
            System.out.println("Enter product price:");
        }
        this.price = AppDemo.scanner.nextDouble();
    }

    public String toString() {
        String result = id + " " + name + " " + price;
        return result;
    }
}
