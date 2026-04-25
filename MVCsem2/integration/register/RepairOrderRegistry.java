package MVCsem2.integration.register;

import MVCsem2.model.entity.RepairOrder;

/**
 * Handles access and storage of repair orders in the Bike Repair Shop.
 */
public class RepairOrderRegistry {
    private final RepairOrder[] repairOrders;

    /**
     * Creates a registry with hardcoded repair orders instead of using a real database.
     */
    public RepairOrderRegistry() {
        repairOrders = new RepairOrder[] {

            new RepairOrder("RO10SDFQ"),
            new RepairOrder("RO10GSKF"),
            new RepairOrder("ROLWERS")

        };
    }

    /**
     * Finds a repair order using repairOrderId as a look up.
     *
     * @param repairOrderId The repair order id.
     * @return The matching repair order, or null if no match was found.
     */
    public RepairOrder getRepairOrder(String repairOrderId) {
        for (int i = 0; i < repairOrders.length; i++) {
            if (repairOrders[i].getRepairOrderId().equals(repairOrderId)) {
                return repairOrders[i];
            }
        }
        return null;
    }

    /**
     * Returns all stored repair orders.
     * @return All repair orders.
     */
    public RepairOrder[] findAllRepairOrders() {
        return repairOrders;
    }

    /**
     * Stores a new repair order with three input paramaters.
     * @param problemDescr The problem description.
     * @param customerPhone The customer phone number.
     * @param bikeSerialNo The bike serial number.
     */
    public void createRepairOrder(String problemDescr, String customerPhone, String bikeSerialNo) {
    }

    /**
     * Updates an existing repair order.
     *
     * @param repairOrder The repair order to update.
     */
    public void updateRepairOrder(RepairOrder repairOrder) {
    }

    /**
     * Finds 1 repair order.
     *
     * @param repairOrderId The repair order id.
     * @return The matching repair order.
     */
    public RepairOrder findRepairOrder(String repairOrderId) {
        return getRepairOrder(repairOrderId);
    }
}