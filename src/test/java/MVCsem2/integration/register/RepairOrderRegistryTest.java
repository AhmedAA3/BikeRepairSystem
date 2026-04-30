package MVCsem2.integration.register;

import MVCsem2.model.entity.RepairOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepairOrderRegistryTest {
    private RepairOrderRegistry repairOrderRegistry;

    @BeforeEach
    public void setUp() {
        repairOrderRegistry = new RepairOrderRegistry();
    }

    @Test
    public void testInitialRegistryHasThreeRepairOrders() {
        RepairOrder[] result = repairOrderRegistry.findAllRepairOrders();

        assertEquals(3, result.length,
                "The registry should start with three hardcoded repair orders.");
    }

    @Test
    public void testFindCristianoRepairOrderRO2() {
        RepairOrder result = repairOrderRegistry.findRepairOrder("0737654321");

        assertNotNull(result, "Cristiano Ronaldo's repair order should be found.");
        assertEquals("RO2", result.getRepairOrderId(),
                "Cristiano Ronaldo's hardcoded repair order should be RO2.");
        assertEquals("Brakes need adjustment", result.getProblemDescription(),
                "Wrong problem description was returned.");
        assertEquals("BIKE200", result.getBikeSerialNo(),
                "Wrong bike serial number was returned.");
    }

    @Test
    public void testGetRepairOrderRO2() {
        RepairOrder result = repairOrderRegistry.getRepairOrder("RO2");

        assertNotNull(result, "Repair order RO2 should exist.");
        assertEquals("Brakes need adjustment", result.getProblemDescription(),
                "RO2 should have the hardcoded brakes problem.");
    }

    @Test
    public void testCreateRepairOrderFromViewScenario() {
        repairOrderRegistry.createRepairOrder("Wheel is broken", "0737654321", "RJL403");

        RepairOrder result = repairOrderRegistry.getRepairOrder("RO4");

        assertNotNull(result, "New repair order RO4 should exist.");
        assertEquals("RO4", result.getRepairOrderId(),
                "The created repair order should have id RO4.");
        assertEquals("Wheel is broken", result.getProblemDescription(),
                "The problem description should match the View scenario.");
        assertEquals("0737654321", result.getCustomerPhone(),
                "The customer phone should match Cristiano Ronaldo.");
        assertEquals("RJL403", result.getBikeSerialNo(),
                "The bike serial number should match the View scenario.");
    }

    @Test
    public void testCreateRepairOrderIncreasesNumberOfOrders() {
        repairOrderRegistry.createRepairOrder("Wheel is broken", "0737654321", "RJL403");

        RepairOrder[] result = repairOrderRegistry.findAllRepairOrders();

        assertEquals(4, result.length,
                "Creating a repair order should increase the number of orders to four.");
    }

    @Test
    public void testFindLatestRepairOrderAfterCreatingRO4() {
        repairOrderRegistry.createRepairOrder("Wheel is broken", "0737654321", "RJL403");

        RepairOrder result = repairOrderRegistry.findRepairOrder("0737654321");

        assertNotNull(result, "A repair order should be found.");
        assertEquals("RO4", result.getRepairOrderId(),
                "The latest repair order for Cristiano Ronaldo should be RO4.");
    }

    @Test
    public void testUpdateRepairOrderToAccepted() {
        RepairOrder repairOrder = repairOrderRegistry.getRepairOrder("RO2");
        repairOrder.accept();

        repairOrderRegistry.updateRepairOrder(repairOrder);

        RepairOrder result = repairOrderRegistry.getRepairOrder("RO2");

        assertEquals("Accepted", result.getStatus(),
                "The updated repair order should be accepted.");
    }

    @Test
    public void testGetRepairOrderThatDoesNotExist() {
        RepairOrder result = repairOrderRegistry.getRepairOrder("RO99");

        assertNull(result, "A repair order that does not exist should return null.");
    }

    @Test
    public void testFindRepairOrderByUnknownPhoneNumber() {
        RepairOrder result = repairOrderRegistry.findRepairOrder("0000000000");

        assertNull(result, "Unknown phone number should not return a repair order.");
    }
}