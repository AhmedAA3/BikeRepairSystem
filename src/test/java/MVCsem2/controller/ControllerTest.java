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
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();

        controller = new Controller(customerRegistry, repairOrderRegistry, printer);
    }

    @Test
    public void testFindCristianoRonaldoCustomer() {
        CustomerDetailsDTO result = controller.findCustomer("0737654321");

        assertNotNull(result, "Cristiano Ronaldo should be found.");
        assertEquals("Cristiano Ronaldo", result.getName(),
                "Wrong customer name was returned.");
        assertEquals("CR7@mail.com", result.getEmail(),
                "Wrong customer email was returned.");
        assertEquals("0737654321", result.getPhone(),
                "Wrong customer phone was returned.");
    }

    @Test
    public void testFindCustomerThatDoesNotExist() {
        CustomerDetailsDTO result = controller.findCustomer("0000000000");

        assertNull(result, "A customer that does not exist should return null.");
    }

    @Test
    public void testCreateRepairOrderFromViewScenario() {
        controller.createRepairOrder("Wheel is broken", "0737654321", "RJL403");

        RepairOrderDTO result = controller.getRepairOrderInfo("RO4");

        assertNotNull(result, "The created repair order RO4 should exist.");
        assertEquals("RO4", result.getId(),
                "The created repair order should have id RO4.");
        assertEquals("Pending", result.getStatus(),
                "A new repair order should have status Pending.");
        assertEquals(0, result.getTotalCost(),
                "A new repair order should have total cost 0.");
    }

    @Test
    public void testFindRepairOrderForCristianoBeforeCreatingNewOrder() {
        RepairOrderDTO result = controller.findRepairOrder("0737654321");

        assertNotNull(result, "Cristiano Ronaldo should have an existing repair order.");
        assertEquals("RO2", result.getId(),
                "Cristiano Ronaldo's existing repair order should be RO2.");
        assertEquals("Pending", result.getStatus(),
                "The repair order should initially be pending.");
    }

    @Test
    public void testFindLatestRepairOrderForCristianoAfterCreatingNewOrder() {
        controller.createRepairOrder("Wheel is broken", "0737654321", "RJL403");

        RepairOrderDTO result = controller.findRepairOrder("0737654321");

        assertNotNull(result, "A repair order should be found.");
        assertEquals("RO4", result.getId(),
                "The latest repair order for Cristiano Ronaldo should be RO4.");
    }

    @Test
    public void testAddDiagnosticResultsFromViewScenario() {
        controller.createRepairOrder("Wheel is broken", "0737654321", "RJL403");

        controller.addDiagnosticResult("RO4", "Wheel is damaged");
        controller.addDiagnosticResult("RO4", "Headlights are broken");

        RepairOrderDTO result = controller.getRepairOrderInfo("RO4");

        assertNotNull(result, "Repair order RO4 should exist after adding diagnostics.");
        assertEquals("RO4", result.getId(),
                "Diagnostics should be added to the correct repair order.");
    }

    @Test
    public void testAddRepairTasksFromViewScenario() {
        controller.createRepairOrder("Wheel is broken", "0737654321", "RJL403");

        controller.addRepairTask("RO4", "Replace wheel", 999);
        controller.addRepairTask("RO4", "Fix wiring", 499);

        RepairOrderDTO result = controller.getRepairOrderInfo("RO4");

        assertNotNull(result, "Repair order RO4 should exist.");
        assertEquals(1498, result.getTotalCost(),
                "Total cost should be 999 + 499.");
        assertEquals("Replace wheel", result.getTasks().get(0),
                "First repair task should be Replace wheel.");
        assertEquals("Fix wiring", result.getTasks().get(1),
                "Second repair task should be Fix wiring.");
    }

    @Test
    public void testAcceptRepairOrderFromViewScenario() {
        controller.createRepairOrder("Wheel is broken", "0737654321", "RJL403");

        controller.addRepairTask("RO4", "Replace wheel", 999);
        controller.addRepairTask("RO4", "Fix wiring", 499);
        controller.acceptRepairOrder("RO4");

        RepairOrderDTO result = controller.getRepairOrderInfo("RO4");

        assertEquals("Accepted", result.getStatus(),
                "Repair order RO4 should be accepted.");
    }

    @Test
    public void testRejectExistingRepairOrder() {
        controller.rejectRepairOrder("RO2");

        RepairOrderDTO result = controller.getRepairOrderInfo("RO2");

        assertEquals("Rejected", result.getStatus(),
                "Repair order RO2 should be rejected.");
    }

    @Test
    public void testGetRepairOrderThatDoesNotExist() {
        RepairOrderDTO result = controller.getRepairOrderInfo("RO99");

        assertNull(result, "A repair order that does not exist should return null.");
    }
}