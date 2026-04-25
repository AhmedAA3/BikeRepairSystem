package se.kth.iv1350.bikerepair.model.device;

import se.kth.iv1350.bikerepair.model.entity.RepairOrder;

/**
 * Represents the physical printer device. In this implementation, 
 * it simulates printing by outputting to the console.
 */
public class Printer {

    /**
     * Creates a new instance of the printer.
     */
    public Printer() {
    }

    /**
     * Prints the details of the specified repair order.
     * * @param order The repair order to print.
     */
    public void printRepairOrder(RepairOrder order) {
        System.out.println("\n--- REPAIR ORDER PRINTOUT ---");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Date: " + order.getDate());
        System.out.println("Status: " + order.getState());
        System.out.println("Total Cost: " + order.calculateTotalCost());
        System.out.println("-----------------------------\n");
    }
}
