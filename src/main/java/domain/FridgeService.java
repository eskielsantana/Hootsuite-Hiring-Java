package domain;

import domain.product.Product;
import infrastructure.AlertsManager;
import infrastructure.FridgeManager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FridgeService {
    private final FridgeManager fridgeManager;
    private final AlertsManager alertsManager;

    public FridgeService(FridgeManager fridgeManager, AlertsManager alertsManager) {
        this.fridgeManager = fridgeManager;
        this.alertsManager = alertsManager;
    }

    public void runDayRoutine() {
        Set<String> spoiledProducts = fridgeManager.decreaseProductsQuality();

        if (spoiledProducts.isEmpty()) {
            return;
        }

        alertsManager.alertSpoiledProducts(List.copyOf(spoiledProducts));
    }

    public List<Product> lookInsideFridge() {
        return fridgeManager.listAllProducts();
    }

    public void addProductToFridge(Product product) {
        fridgeManager.addOrRefillProductStock(product);
    }

    public void removeFromFridge(Product product) {
        removeFromFridge(product, 1);
    }

    public void removeFromFridge(Product search, int amount) {
        Optional<Product> productOpt = fridgeManager.findProduct(search.getItem(), search.getQuality());

        if (productOpt.isEmpty()) {
            throw new IllegalStateException();
        }

        Product product = productOpt.get();

        if (product.isEmptyStock()) {
            throw new IllegalArgumentException();
        }

        product.setStock(product.getStock() - amount);

        if (product.isEmptyStock()) {
            fridgeManager.purgeProduct(product);
            alertsManager.needsRestockAlert(product.getItem());
            return;
        }

        fridgeManager.updateProductStock(product);
    }
}
