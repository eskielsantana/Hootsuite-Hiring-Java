package infrastructure;

import domain.product.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FridgeManager {

    private final List<Product> products;

    public FridgeManager() {
        this.products = new ArrayList<>();
    }

    public Optional<Product> findProduct(String name, int quality) {
        return products.stream().filter(p -> p.getItem().equals(name) && p.getQuality() == quality).findFirst();
    }

    public Set<String> decreaseProductsQuality() {
        Set<String> spoiledProducts = new HashSet<>();

        products.forEach(product -> {
            product.decreaseQuality();
            if (product.isSpoiling()) {
                spoiledProducts.add(product.getItem());
            }
        });
        return spoiledProducts;
    }

    public void updateProductStock(Product product) {
        for (Product p : products) {
            if (p.getItem().equals(product.getItem()) && p.getQuality() == product.getQuality()) {
                p.setStock(product.getStock());
                return;
            }
        }
    }

    public void addOrRefillProductStock(Product product) {
        for (Product p : products) {
            if (p.getItem().equals(product.getItem()) && p.getQuality() == product.getQuality()) {
                p.setStock(p.getStock() + product.getStock());
                return;
            }
        }
        products.add(product);
    }

    public void purgeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> listAllProducts() {
        return products;
    }
}
