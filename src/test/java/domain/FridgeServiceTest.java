package domain;

import domain.product.Product;
import infrastructure.AlertsManager;
import infrastructure.FridgeManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static domain.product.Type.ANIMAL;
import static domain.product.Type.PRODUCE;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class FridgeServiceTest {
    private FridgeService fridgeService;
    private AlertsManager alertsManager;
    private FridgeManager fridgeManager;

    @Before
    public void setUp() {
        fridgeManager = spy(FridgeManager.class);
        alertsManager = spy(AlertsManager.class);

        fridgeService = new FridgeService(fridgeManager, alertsManager);
    }

    // TEST CASE 1
    // Add 1 chicken and 1 apple to the fridge, remove the chicken next day
    // - write assertion on the quality of the all items in the fridge
    // - expect user to get alerted to restock chicken

    @Test
    public void removeFromFridge_WhenCalled_DecreasesTheQuantityOrRemoveIfZero() {
        Product chicken = new Product("Chicken", 1, ANIMAL);
        Product apple = new Product("Apple", 5, PRODUCE);

        fridgeService.addProductToFridge(chicken);
        fridgeService.addProductToFridge(apple);

        fridgeService.runDayRoutine();

        fridgeService.removeFromFridge(chicken);
        fridgeService.removeFromFridge(apple, 3);

        List<Product> products = fridgeService.lookInsideFridge();

        Assert.assertFalse(products.contains(chicken));
        Assert.assertEquals(1, products.size());
        Assert.assertEquals(2, products.get(0).getStock());

        verify(alertsManager).needsRestockAlert(chicken.getItem());
    }

    // TEST CASE 2
    // Add a chicken, wait a day, add another chicken and then wait for other 2 days
    // - write assertion on the quality of the all items in the fridge
    // - expect user to get alerted to consume the chicken

    @Test
    public void runDayRoutine_WheneverCalled_ProductsWillHaveItsQualityReduced() {
        String item = "Chicken";
        Product first = new Product(item, 1, ANIMAL);
        Product second = new Product(item, 1, ANIMAL);

        fridgeService.addProductToFridge(first);

        fridgeService.runDayRoutine();

        fridgeService.addProductToFridge(second);

        fridgeService.runDayRoutine();
        fridgeService.runDayRoutine();

        Assert.assertEquals(1, first.getQuality());
        Assert.assertEquals(4, second.getQuality());

        verify(alertsManager).alertSpoiledProducts(Collections.singletonList(item));
    }
}
