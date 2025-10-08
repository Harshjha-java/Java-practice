import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Comparator;

class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - Price: " + price;
    }
}

public class ProductStreamDemo {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 60000, "Electronics"),
            new Product("Phone", 30000, "Electronics"),
            new Product("Banana", 40, "Groceries"),
            new Product("Apple", 120, "Groceries"),
            new Product("Headphone", 2000, "Electronics")
        );

        // Group products by category
        Map<String, List<Product>> grouped = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));
        System.out.println("Grouped by Category:");
        grouped.forEach((cat, list) -> System.out.println(cat + ": " + list));

        // Most expensive product in each category
        Map<String, Optional<Product>> maxProduct = products.stream()
            .collect(Collectors.groupingBy(p -> p.category,
                Collectors.maxBy(Comparator.comparingDouble(p -> p.price))));
        System.out.println("\nMost Expensive product per Category:");
        maxProduct.forEach((cat, prodOpt) -> 
            System.out.println(cat + ": " + prodOpt.orElse(null)));

        // Average price of all products
        double avgPrice = products.stream()
            .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage price of all products: " + avgPrice);
    }
}
