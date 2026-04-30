package MVCsem2.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepairOrderTest {
    private RepairOrder repairOrder;

    @BeforeEach
    public void setUp() {
        repairOrder = new RepairOrder("RO4", "Wheel is broken", "0737654321", "RJL403");
    }

    @Test
    public void testNewRepairOrderFromViewScenarioHasCorrectData() {
        assertEquals("RO4", repairOrder.getRepairOrderId(),
                "Wrong repair order id.");
        assertEquals("Wheel is broken", repairOrder.getProblemDescription(),
                "Wrong problem description.");
        assertEquals("0737654321", repairOrder.getCustomerPhone(),
                "Wrong customer phone.");
        assertEquals("RJL403", repairOrder.getBikeSerialNo(),
                "Wrong bike serial number.");
    }

    @Test
    public void testNewRepairOrderIsPending() {
        assertEquals("Pending", repairOrder.getStatus(),
                "A new repair order should be pending.");
    }

    @Test
    public void testAddDiagnosticResultsFromViewScenario() {
        repairOrder.addDiagnosticResult("Wheel is damaged");
        repairOrder.addDiagnosticResult("Headlights are broken");

        assertEquals("Wheel is damaged", repairOrder.getDiagnosticResults().get(0),
                "First diagnostic result is wrong.");
        assertEquals("Headlights are broken", repairOrder.getDiagnosticResults().get(1),
                "Second diagnostic result is wrong.");
    }

    @Test
    public void testAddRepairTasksFromViewScenario() {
        repairOrder.addRepairTask("Replace wheel", 999);
        repairOrder.addRepairTask("Fix wiring", 499);

        assertEquals("Replace wheel", repairOrder.getRepairTasks().get(0),
                "First repair task is wrong.");
        assertEquals("Fix wiring", repairOrder.getRepairTasks().get(1),
                "Second repair task is wrong.");
    }

    @Test
    public void testTotalCostFromViewScenario() {
        repairOrder.addRepairTask("Replace wheel", 999);
        repairOrder.addRepairTask("Fix wiring", 499);

        assertEquals(1498, repairOrder.getTotalCost(),
                "Total cost should be 999 + 499.");
    }

    @Test
    public void testAcceptRepairOrderFromViewScenario() {
        repairOrder.accept();

        assertTrue(repairOrder.isAccepted(),
                "Repair order should be accepted.");
        assertEquals("Accepted", repairOrder.getStatus(),
                "Accepted repair order should have status Accepted.");
    }

    @Test
    public void testRejectRepairOrder() {
        repairOrder.reject();

        assertTrue(repairOrder.isRejected(),
                "Repair order should be rejected.");
        assertEquals("Rejected", repairOrder.getStatus(),
                "Rejected repair order should have status Rejected.");
    }

    @Test
    public void testEmptyRepairTaskIsNotAdded() {
        repairOrder.addRepairTask("   ", 999);

        assertEquals(0, repairOrder.getRepairTasks().size(),
                "Empty repair task should not be added.");
    }

    @Test
    public void testNegativeRepairTaskCostIsNotAdded() {
        repairOrder.addRepairTask("Replace wheel", -999);

        assertEquals(0, repairOrder.getRepairTasks().size(),
                "Repair task with negative cost should not be added.");
    }
}