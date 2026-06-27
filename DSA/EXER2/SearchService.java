package DSA.EXER2;

public class SearchService {

    public static Product linearSearch(Product[] products, int targetId) {
        for (Product p : products) {
            if (p.getProductId() == targetId) {
                return p;
            }
        }
        return null;
    }

   
    public static Product binarySearch(Product[] sortedProducts, int targetId) {
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // avoids overflow vs (low+high)/2
            int midId = sortedProducts[mid].getProductId();

            if (midId == targetId) {
                return sortedProducts[mid];
            } else if (midId < targetId) {
                low = mid + 1;  
            } else {
                high = mid - 1; 
            }
        }
        return null; 
    }

    
    public static Product linearSearchByName(Product[] products, String query) {
        String lower = query.toLowerCase();
        for (Product p : products) {
            if (p.getProductName().toLowerCase().contains(lower)) {
                return p;
            }
        }
        return null;
    }
}
