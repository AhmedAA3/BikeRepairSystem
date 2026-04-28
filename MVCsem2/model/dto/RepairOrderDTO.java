package MVCsem2.model.dto;

import MVCsem2.model.entity.RepairOrder;
import java.util.List;

/**
 * Innehåller all information om en reparationsorder för att informera kunden.
 */
public class RepairOrderDTO {
    private final String id;
    private final String status;
    private final int totalCost;
    private final List<String> tasks;

    /**
     * Skapar en ny instans baserat på en RepairOrder-entitet.
     * @param order Order-entiteten som data ska extraheras ifrån.
     */
    public RepairOrderDTO(RepairOrder order) {
        this.id = order.getRepairOrderId();
        this.status = order.getStatus();
        this.totalCost = order.getTotalCost();
        this.tasks = order.getRepairTasks();
    }

    /**
    * Creates repair order DTO array from repair orders.
    *
    * @param repairList The repair orders that will be converted.
    * @return An array with repair order DTOs.
    */
    public static RepairOrderDTO[] createRepairOrderDTO(RepairOrder[] repairList) {

        RepairOrderDTO[] repairOrderDTOs = new RepairOrderDTO[repairList.length];

        for (int i = 0; i < repairList.length; i++) {
            repairOrderDTOs[i] = new RepairOrderDTO(repairList[i]);
    }

    return repairOrderDTOs;
    }

    public String getId() { return id; }

    /**
     * Returns the repair order status.
     */
    public String getStatus() { return status; }

    /**
     * Returns the total cost.
     */
    public int getTotalCost() { return totalCost; }

    /**
     * Returns the repair tasks.
     */
    public List<String> getTasks() { return tasks; }
}
