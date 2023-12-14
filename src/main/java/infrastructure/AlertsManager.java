package infrastructure;

import java.util.List;

public class AlertsManager {
    public void needsRestockAlert(String product) {
        System.out.printf("%s needs a restock!%n", product);
    }

    public void alertSpoiledProducts(List<String> spoiledProducts) {
        if (spoiledProducts.size() > 1) {
            StringBuilder builder = new StringBuilder("The following products are about to spoil:\n");
            spoiledProducts.forEach(p -> builder.append(" - ").append(p).append("\n"));
            System.out.printf(builder.append("%n").toString());
        } else {
            System.out.printf("%s will expire soon!%n", spoiledProducts.get(0));
        }
    }
}
