package MVCsem2.integration.register;

import MVCsem2.model.entity.RepairOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;

    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();
    }

    @Test
    public void registryShouldContainTheHardcodedOrdersAtStartup() {

        RepairOrder[] ordersInRegistry = registry.findAllRepairOrders();

        assertEquals(3, ordersInRegistry.length,
                "The registry should contain the three hardcoded repair orders at startup.");
    }

    @Test
    public void registeredCustomerShouldHaveTheHardcodedRO2RepairOrder() {

        String registeredCustomerPhone = "0737654321";

        RepairOrder registeredCustomerOrder = registry.findRepairOrder(registeredCustomerPhone);

        assertNotNull(registeredCustomerOrder,
                "Cristiano Ronaldo's hardcoded repair order should be found.");
        assertEquals("RO2", registeredCustomerOrder.getRepairOrderId(),
                "The hardcoded repair order for Cristiano Ronaldo should be RO2.");
        assertEquals("Brakes need adjustment", registeredCustomerOrder.getProblemDescription(),
                "The hardcoded problem description for RO2 should be returned.");
        assertEquals("BIKE200", registeredCustomerOrder.getBikeSerialNo(),
                "The hardcoded bike serial number for RO2 should be returned.");
    }

    @Test
    public void ro2ShouldBePossibleToFindByRepairOrderId() {

        RepairOrder order = registry.getRepairOrder("RO2");

        assertNotNull(order,
                "RO2 should exist in the hardcoded registry.");
        assertEquals("Brakes need adjustment", order.getProblemDescription(),
                "RO2 should contain the hardcoded brakes problem.");
    }

    @Test
    public void missingRepairOrderIdShouldReturnNull() {

        RepairOrder missingOrder = registry.getRepairOrder("RO99");

        assertNull(missingOrder,
                "A repair order id that does not exist should return null.");
    }

    @Test
    public void unknownCustomerPhoneShouldNotReturnRepairOrder() {

        RepairOrder order = registry.findRepairOrder("0000000000");

        assertNull(order,
                "An unknown phone number should not match any repair order.");
    }

    @Test
    public void creatingViewScenarioOrderShouldStoreRO4() {

        String problem = "Wheel is broken";
        String registeredCustomerPhone = "0737654321";
        String bikeSerialNo = "RJL403";

        registry.createRepairOrder(problem, registeredCustomerPhone, bikeSerialNo);

        RepairOrder createdOrder = registry.getRepairOrder("RO4");

        assertNotNull(createdOrder,
                "The newly created scenario repair order should exist.");
        assertEquals("RO4", createdOrder.getRepairOrderId(),
                "The created scenario repair order should get id RO4.");
        assertEquals(problem, createdOrder.getProblemDescription(),
                "The created order should store the scenario problem description.");
        assertEquals(registeredCustomerPhone, createdOrder.getCustomerPhone(),
                "The created order should store Cristiano Ronaldo's phone number.");
        assertEquals(bikeSerialNo, createdOrder.getBikeSerialNo(),
                "The created order should store the bike serial number from the scenario.");
    }

    @Test
    public void creatingScenarioOrderShouldIncreaseRegistrySize() {

        registry.createRepairOrder("Wheel is broken", "0737654321", "RJL403");

        RepairOrder[] ordersAfterCreate = registry.findAllRepairOrders();

        assertEquals(4, ordersAfterCreate.length,
                "The registry should contain four repair orders after creating RO4.");
    }

    @Test
    public void latestRegisteredCustomerOrderShouldBeRO4AfterScenarioOrderIsCreated() {

        String registeredCustomerPhone = "0737654321";

        registry.createRepairOrder("Wheel is broken", registeredCustomerPhone, "RJL403");

        RepairOrder latestOrder = registry.findRepairOrder(registeredCustomerPhone);

        assertNotNull(latestOrder,
                "A repair order should be found for Cristiano Ronaldo.");
        assertEquals("RO4", latestOrder.getRepairOrderId(),
                "The latest repair order for Cristiano Ronaldo should be the newly created RO4.");
    }

    @Test
    public void updatedRepairOrderShouldKeepAcceptedStatus() {
        
        RepairOrder orderToUpdate = registry.getRepairOrder("RO2");
        orderToUpdate.accept();

        registry.updateRepairOrder(orderToUpdate);

        RepairOrder storedOrder = registry.getRepairOrder("RO2");

        assertEquals("Accepted", storedOrder.getStatus(),
                "The registry should keep the updated accepted status.");
    }
}