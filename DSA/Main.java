import java.util.List;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        System.out.println("=== Adding products ===");
        inventory.addProduct(new Product("P001", "Wireless Mouse", 50, 19.99));
        inventory.addProduct(new Product("P002", "USB-C Cable", 200, 5.49));
        inventory.addProduct(new Product("P003", "Mechanical Keyboard", 8, 79.99));
        inventory.addProduct(new Product("P001", "Duplicate Attempt", 10, 9.99)); // should fail

        System.out.println("\n=== Current inventory ===");
        for (Product p : inventory.listAll()) {
            System.out.println(p);
        }

        System.out.println("\n=== Updating P002 (price change) ===");
        inventory.updateProduct("P002", null, null, 4.99);
        System.out.println(inventory.getProduct("P002"));

        System.out.println("\n=== Adjusting stock (sale of 5 keyboards) ===");
        inventory.adjustQuantity("P003", -5);
        System.out.println(inventory.getProduct("P003"));

        System.out.println("\n=== Low stock report (threshold: 10) ===");
        List<Product> low = inventory.findLowStock(10);
        for (Product p : low) {
            System.out.println(p);
        }

        System.out.println("\n=== Deleting P001 ===");
        inventory.deleteProduct("P001");
        inventory.deleteProduct("P999"); // should fail, doesn't exist

        System.out.println("\n=== Final inventory (size=" + inventory.size() + ") ===");
        for (Product p : inventory.listAll()) {
            System.out.println(p);
        }
    }
}