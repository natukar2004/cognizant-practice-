import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private final Map<String, Product> products = new LinkedHashMap<>();

    public boolean addProduct(Product product) {
        if (product == null) {
            System.out.println("Cannot add null product.");
            return false;
        }

        if (products.containsKey(product.getId())) {
            System.out.println("Product with ID " + product.getId() + " already exists.");
            return false;
        }

        products.put(product.getId(), product);
        return true;
    }

    public Product getProduct(String id) {
        return products.get(id);
    }

    public boolean updateProduct(String id, String name, Integer quantity, Double price) {
        Product product = products.get(id);
        if (product == null) {
            System.out.println("Product with ID " + id + " does not exist.");
            return false;
        }

        if (name != null) {
            product.setName(name);
        }
        if (quantity != null) {
            product.setQuantity(quantity);
        }
        if (price != null) {
            product.setPrice(price);
        }

        return true;
    }

    public boolean adjustQuantity(String id, int change) {
        Product product = products.get(id);
        if (product == null) {
            System.out.println("Product with ID " + id + " does not exist.");
            return false;
        }

        int updatedQuantity = product.getQuantity() + change;
        if (updatedQuantity < 0) {
            System.out.println("Not enough stock for product ID " + id + ".");
            return false;
        }

        product.setQuantity(updatedQuantity);
        return true;
    }

    public List<Product> findLowStock(int threshold) {
        List<Product> lowStock = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getQuantity() <= threshold) {
                lowStock.add(product);
            }
        }
        return lowStock;
    }

    public boolean deleteProduct(String id) {
        if (!products.containsKey(id)) {
            System.out.println("Product with ID " + id + " does not exist.");
            return false;
        }

        products.remove(id);
        return true;
    }

    public List<Product> listAll() {
        return new ArrayList<>(products.values());
    }

    public int size() {
        return products.size();
    }
}
