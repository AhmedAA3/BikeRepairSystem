package MVCsem2.integration.register;

import MVCsem2.model.entity.RepairOrder;

/**
 * Handles access and storage of repair orders in the Bike Repair Shop.
 */
public class RepairOrderRegistry {
    private RepairOrder[] repairOrders;

    /**
     * Creates a registry with hardcoded repair orders, instead of using a real database.
     */
    public RepairOrderRegistry() {
        repairOrders = new RepairOrder[] {
            new RepairOrder("RO1", "Battery does not charge", "0701234567", "BIKE100"),
            new RepairOrder("RO2", "Brakes need adjustment", "0737654321", "BIKE200"),
            new RepairOrder("RO3", "Motor makes strange noise", "0761112233", "BIKE300")
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
        String repairOrderId = "RO" + (repairOrders.length + 1);
        RepairOrder newRepairOrder = new RepairOrder(repairOrderId, problemDescr, customerPhone, bikeSerialNo);

        RepairOrder[] updatedRepairOrders = new RepairOrder[repairOrders.length + 1];

        for (int i = 0; i < repairOrders.length; i++) {
            updatedRepairOrders[i] = repairOrders[i];
        }

        updatedRepairOrders[updatedRepairOrders.length - 1] = newRepairOrder;
        repairOrders = updatedRepairOrders;
    }

    /**
     * Updates an existing repair order.
     *
     * @param repairOrder The repair order to update.
     */
    public void updateRepairOrder(RepairOrder repairOrder) {
        for (int i = 0; i < repairOrders.length; i++) {
            if (repairOrders[i].getRepairOrderId().equals(repairOrder.getRepairOrderId())) {
                repairOrders[i] = repairOrder;
                return;
            }
        }
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