package DSA.EXER2;

public class Product implements Comparable<Product> {

    private final int productId;
    private final String productName;
    private final String category;
    private final double price;

    public Product(int productId, String productName, String category, double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    
    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.productId, other.productId);
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, name='%s', category='%s', price=%.2f]",
                productId, productName, category, price);
    }
}
