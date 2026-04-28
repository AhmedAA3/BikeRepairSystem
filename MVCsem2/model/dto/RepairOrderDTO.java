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

    public String getId() { return id; }
    public String getStatus() { return status; }
    public int getTotalCost() { return totalCost; }
    public List<String> getTasks() { return tasks; }
}
