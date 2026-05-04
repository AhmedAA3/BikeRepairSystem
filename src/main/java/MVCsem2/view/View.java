package MVCsem2.view;

import MVCsem2.controller.Controller;
import MVCsem2.model.dto.CustomerDetailsDTO;
import MVCsem2.model.dto.RepairOrderDTO;

/**
 * Handles the user interface.
 */
public class View {
    private Controller controller;

    /**
     * Creates a view with the specified controller.
     * @param controller The controller used by this view.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Starts a hardcoded execution of the program.
     */
    public void start() {

        // Customer lookup scenario
        String phoneNumber = "0737654321";

        CustomerDetailsDTO customerDetails = controller.findCustomer(phoneNumber);

        if (customerDetails != null) {
            System.out.println("Customer found:");
            System.out.println("Name: " + customerDetails.getName());
            System.out.println("Email: " + customerDetails.getEmail());
            System.out.println("Phone: " + customerDetails.getPhone());
            System.out.println();
        } else {
            System.out.println("Customer not found.");
            System.out.println();
        }

        // Create repair order scenario
        String problemDescr = "Wheel is broken";
        String customerPhone = phoneNumber;
        String bikeSerialNo = "RJL403";
        controller.createRepairOrder(problemDescr, customerPhone, bikeSerialNo);

        // Find all repair orders scenario
        RepairOrderDTO[] repairOrders = controller.findAllRepairOrders();

        System.out.println("All repair orders:");

        for (int i = 0; i < repairOrders.length; i++) {
            System.out.println("Repair order id: " + repairOrders[i].getId());
            System.out.println("Status: " + repairOrders[i].getStatus());
            System.out.println("Total cost: " + repairOrders[i].getTotalCost());
            System.out.println();
        }

        // Find customer's repair order scenario
        String phoneNumberForRepairOrder = phoneNumber;

        RepairOrderDTO repairOrder = controller.findRepairOrder(phoneNumberForRepairOrder);

        if (repairOrder != null) {
            System.out.println("Repair order found before diagnostics and repair tasks:");
            System.out.println("Repair order id: " + repairOrder.getId());
            System.out.println("Status: " + repairOrder.getStatus());
            System.out.println("Total cost: " + repairOrder.getTotalCost());
            System.out.println();
        } else {
            System.out.println("No repair order found for phone number: " + phoneNumberForRepairOrder);
            System.out.println();
        }

        // Add diagnostic results and repair tasks scenario
        String repairOrderId = "RO4";
        String[] diagnosticResults = {
            "Wheel is damaged",
            "Headlights are broken",
        };

        for (int i = 0; i < diagnosticResults.length; i++) {
            controller.addDiagnosticResult(repairOrderId, diagnosticResults[i]);
        }

        String[] repairTasks = {
            "Replace wheel",
            "Fix wiring",
        };

        int[] repairTaskCosts = {
            999,
            499,
        };

        for (int i = 0; i < repairTasks.length; i++) {
            controller.addRepairTask(repairOrderId, repairTasks[i], repairTaskCosts[i]);
        }
        RepairOrderDTO updatedRepairOrder = controller.getRepairOrderInfo(repairOrderId);

        if (updatedRepairOrder != null) {
            System.out.println("Repair order after diagnostics and repair tasks:");
            System.out.println("Repair order id: " + updatedRepairOrder.getId());
            System.out.println("Status: " + updatedRepairOrder.getStatus());
            System.out.println("Total cost: " + updatedRepairOrder.getTotalCost());
            System.out.println();
        } else {
            System.out.println("Repair order not found: " + repairOrderId);
            System.out.println();
        }
        // Accept or reject repair order scenario
        String repairOrderIdToDecide = "RO4";
        boolean repairOrderAccepted = true;

        if (repairOrderAccepted) {
            controller.acceptRepairOrder(repairOrderIdToDecide);
        } else {
            controller.rejectRepairOrder(repairOrderIdToDecide);
        }
    }
}