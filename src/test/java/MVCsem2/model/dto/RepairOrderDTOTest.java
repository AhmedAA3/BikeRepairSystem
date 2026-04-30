package MVCsem2.model.dto;

import MVCsem2.model.entity.RepairOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepairOrderDTOTest {

    @Test
    public void testRepairOrderDTOCopiesViewScenarioData() {
        RepairOrder repairOrder = new RepairOrder("RO4", "Wheel is broken", "0737654321", "RJL403");

        RepairOrderDTO result = new RepairOrderDTO(repairOrder);

        assertEquals("RO4", result.getId(),
                "DTO should copy the repair order id.");
        assertEquals("Pending", result.getStatus(),
                "DTO should copy the repair order status.");
        assertEquals(0, result.getTotalCost(),
                "DTO should copy the repair order total cost.");
    }

    @Test
    public void testRepairOrderDTOCopiesViewScenarioRepairTasks() {
        RepairOrder repairOrder = new RepairOrder("RO4", "Wheel is broken", "0737654321", "RJL403");
        repairOrder.addRepairTask("Replace wheel", 999);
        repairOrder.addRepairTask("Fix wiring", 499);

        RepairOrderDTO result = new RepairOrderDTO(repairOrder);

        assertEquals("Replace wheel", result.getTasks().get(0),
                "DTO should copy Replace wheel.");
        assertEquals("Fix wiring", result.getTasks().get(1),
                "DTO should copy Fix wiring.");
        assertEquals(1498, result.getTotalCost(),
                "DTO should copy the total cost 1498.");
    }

    @Test
    public void testRepairOrderDTOCopiesAcceptedStatus() {
        RepairOrder repairOrder = new RepairOrder("RO4", "Wheel is broken", "0737654321", "RJL403");
        repairOrder.accept();

        RepairOrderDTO result = new RepairOrderDTO(repairOrder);

        assertEquals("Accepted", result.getStatus(),
                "DTO should copy accepted status.");
    }

    @Test
    public void testCreateRepairOrderDTOArray() {
        RepairOrder[] repairOrders = new RepairOrder[] {
                new RepairOrder("RO1", "", "0701234567", "BIKE100"),
                new RepairOrder("RO2", "Brakes need adjustment", "0737654321", "BIKE200"),
                new RepairOrder("RO3", "Motor makes strange noise", "0761112233", "BIKE300"),
                new RepairOrder("RO4", "Wheel is broken", "0737654321", "RJL403")
        };

        RepairOrderDTO[] result = RepairOrderDTO.createRepairOrderDTO(repairOrders);

        assertEquals(4, result.length,
                "DTO array should have the same length as the repair order array.");
        assertEquals("RO4", result[3].getId(),
                "Last DTO should be RO4.");
    }
}