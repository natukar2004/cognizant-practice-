package DSA.EXER2;

import java.util.Arrays;
import java.util.Random;

public class ECommerceSearchDemo {

    public static void main(String[] args) {
       
        Product[] catalog = {
                new Product(105, "Wireless Mouse", "Electronics", 19.99),
                new Product(203, "Running Shoes", "Footwear", 59.99),
                new Product(101, "Bluetooth Speaker", "Electronics", 34.50),
                new Product(310, "Yoga Mat", "Fitness", 22.00),
                new Product(150, "Office Chair", "Furniture", 129.99),
                new Product(220, "Football", "Sports", 25.00),
                new Product(99,  "USB-C Cable", "Electronics", 8.99)
        };

        System.out.println("=== Original (unsorted) catalog ===");
        for (Product p : catalog) System.out.println(p);

        
        int searchId = 220;
        System.out.println("\n--- Linear Search for productId=" + searchId + " ---");
        Product foundLinear = SearchService.linearSearch(catalog, searchId);
        System.out.println(foundLinear != null ? "Found: " + foundLinear : "Not found");

       
        Product[] sortedCatalog = Arrays.copyOf(catalog, catalog.length);
        Arrays.sort(sortedCatalog); // uses Product.compareTo() -> sorts by productId

        System.out.println("\n=== Sorted catalog (by productId, for binary search) ===");
        for (Product p : sortedCatalog) System.out.println(p);

        System.out.println("\n--- Binary Search for productId=" + searchId + " ---");
        Product foundBinary = SearchService.binarySearch(sortedCatalog, searchId);
        System.out.println(foundBinary != null ? "Found: " + foundBinary : "Not found");

        
        System.out.println("\n--- Linear Search by name containing 'shoe' ---");
        Product foundByName = SearchService.linearSearchByName(catalog, "shoe");
        System.out.println(foundByName != null ? "Found: " + foundByName : "Not found");

        
        runBenchmark(1_000_000);
    }

    
    private static void runBenchmark(int size) {
        System.out.println("\n=== Benchmark: catalog size = " + size + " ===");

        Product[] products = new Product[size];
        Random rand = new Random(42);
        for (int i = 0; i < size; i++) {
            products[i] = new Product(i, "Product-" + i, "Category-" + (i % 20), rand.nextDouble() * 100);
        }
       
        Arrays.sort(products);

        int worstCaseTarget = size - 1;

        long startLinear = System.nanoTime();
        SearchService.linearSearch(products, worstCaseTarget);
        long endLinear = System.nanoTime();

        long startBinary = System.nanoTime();
        SearchService.binarySearch(products, worstCaseTarget);
        long endBinary = System.nanoTime();

        double linearMs = (endLinear - startLinear) / 1_000_000.0;
        double binaryMs = (endBinary - startBinary) / 1_000_000.0;

        System.out.printf("Linear search (worst case):  %.4f ms%n", linearMs);
        System.out.printf("Binary search (worst case):  %.4f ms%n", binaryMs);
        System.out.printf("Binary search was roughly %.0fx faster on this run%n",
                linearMs / Math.max(binaryMs, 0.0001));
    }
}
