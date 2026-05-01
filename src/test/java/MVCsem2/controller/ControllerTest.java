package MVCsem2.controller;

import MVCsem2.integration.register.CustomerRegistry;
import MVCsem2.integration.register.RepairOrderRegistry;
import MVCsem2.model.device.Printer;
import MVCsem2.model.dto.CustomerDetailsDTO;
import MVCsem2.model.dto.RepairOrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        
        CustomerRegistry customers = new CustomerRegistry();
        RepairOrderRegistry repairOrders = new RepairOrderRegistry();
        Printer printer = new Printer();

        controller = new Controller(customers, repairOrders, printer);
    }

    @Test
    public void controllerShouldFindRegisteredCustomerByPhoneNumber() {

        String registeredCustomerPhone = "0737654321";

        CustomerDetailsDTO customerDetails = controller.findCustomer(registeredCustomerPhone);

        assertNotNull(customerDetails,
                "Cristiano Ronaldo should be found by his registered phone number.");
        assertEquals("Cristiano Ronaldo", customerDetails.getName(),
                "The controller should return the correct customer name.");
        assertEquals("CR7@mail.com", customerDetails.getEmail(),
                "The controller should return the correct customer email.");
        assertEquals(registeredCustomerPhone, customerDetails.getPhone(),
                "The controller should return the same phone number that was searched for.");
    }

    @Test
    public void controllerShouldReturnNullForUnknownCustomerPhone() {

        CustomerDetailsDTO customerDetails = controller.findCustomer("0000000000");

        assertNull(customerDetails,
                "An unknown customer phone number should not return customer details.");
    }

    @Test
    public void registeredCustomerShouldHaveExistingRepairOrderBeforeNewScenarioOrderIsCreated() {

        String registeredCustomerPhone = "0737654321";

        RepairOrderDTO existingOrder = controller.findRepairOrder(registeredCustomerPhone);

        assertNotNull(existingOrder,
                "Cristiano Ronaldo should already have a hardcoded repair order.");
        assertEquals("RO2", existingOrder.getId(),
                "The existing hardcoded repair order for Cristiano Ronaldo should be RO2.");
        assertEquals("Pending", existingOrder.getStatus(),
                "The hardcoded repair order should start as pending.");
    }

    @Test
    public void creatingScenarioOrderShouldCreatePendingRO4() {

        String problem = "Wheel is broken";
        String registeredCustomerPhone = "0737654321";
        String bikeSerialNo = "RJL403";

        controller.createRepairOrder(problem, registeredCustomerPhone, bikeSerialNo);

        RepairOrderDTO createdOrder = controller.getRepairOrderInfo("RO4");

        assertNotNull(createdOrder,
                "The scenario repair order RO4 should exist after it is created.");
        assertEquals("RO4", createdOrder.getId(),
                "The created scenario order should have id RO4.");
        assertEquals("Pending", createdOrder.getStatus(),
                "A newly created repair order should have status Pending.");
        assertEquals(0, createdOrder.getTotalCost(),
                "A newly created repair order should not have any repair costs yet.");
    }

    @Test
    public void latestRegisteredCustomerOrderShouldBeRO4AfterCreatingScenarioOrder() {

        String registeredCustomerPhone = "0737654321";

        controller.createRepairOrder("Wheel is broken", registeredCustomerPhone, "RJL403");

        RepairOrderDTO latestOrder = controller.findRepairOrder(registeredCustomerPhone);

        assertNotNull(latestOrder,
                "A repair order should be found for Cristiano Ronaldo.");
        assertEquals("RO4", latestOrder.getId(),
                "The latest repair order for Cristiano Ronaldo should be the newly created RO4.");
    }

    @Test
    public void diagnosticResultsShouldBeAddedToScenarioOrder() {

        controller.createRepairOrder("Wheel is broken", "0737654321", "RJL403");

        controller.addDiagnosticResult("RO4", "Wheel is damaged");
        controller.addDiagnosticResult("RO4", "Headlights are broken");

        RepairOrderDTO scenarioOrder = controller.getRepairOrderInfo("RO4");

        assertNotNull(scenarioOrder,
                "RO4 should still exist after adding diagnostic results.");
        assertEquals("RO4", scenarioOrder.getId(),
                "The diagnostic results should be connected to the scenario repair order.");
    }

    @Test
    public void scenarioRepairTasksShouldUpdateTasksAndTotalCost() {

        controller.createRepairOrder("Wheel is broken", "0737654321", "RJL403");

        String wheelTask = "Replace wheel";
        String wiringTask = "Fix wiring";

        controller.addRepairTask("RO4", wheelTask, 999);
        controller.addRepairTask("RO4", wiringTask, 499);

        RepairOrderDTO scenarioOrder = controller.getRepairOrderInfo("RO4");

        assertNotNull(scenarioOrder,
                "RO4 should exist after adding repair tasks.");
        assertEquals(1498, scenarioOrder.getTotalCost(),
                "The total cost should be the sum of the two scenario repair tasks.");
        assertEquals(wheelTask, scenarioOrder.getTasks().get(0),
                "The first repair task should be the wheel replacement.");
        assertEquals(wiringTask, scenarioOrder.getTasks().get(1),
                "The second repair task should be the wiring repair.");
    }

    @Test
    public void scenarioOrderShouldBecomeAcceptedWhenAcceptedThroughController() {

        controller.createRepairOrder("Wheel is broken", "0737654321", "RJL403");
        controller.addRepairTask("RO4", "Replace wheel", 999);
        controller.addRepairTask("RO4", "Fix wiring", 499);

        controller.acceptRepairOrder("RO4");

        RepairOrderDTO acceptedOrder = controller.getRepairOrderInfo("RO4");

        assertEquals("Accepted", acceptedOrder.getStatus(),
                "The scenario repair order should have status Accepted.");
    }

    @Test
    public void existingRO2ShouldBecomeRejectedWhenRejectedThroughController() {

        controller.rejectRepairOrder("RO2");

        RepairOrderDTO rejectedOrder = controller.getRepairOrderInfo("RO2");

        assertEquals("Rejected", rejectedOrder.getStatus(),
                "The existing repair order RO2 should have status Rejected.");
    }

    @Test
    public void controllerShouldReturnNullForMissingRepairOrderId() {

        RepairOrderDTO missingOrder = controller.getRepairOrderInfo("RO99");

        assertNull(missingOrder,
                "A repair order id that does not exist should return null.");
    }
}