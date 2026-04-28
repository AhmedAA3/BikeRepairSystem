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

		// 1: Customer details look up scenario
		// Its been tested and works, prints out Cristiano Ronaldo customer details

        String phoneNumber = "0737654321";

        CustomerDetailsDTO customerDetails = controller.findCustomer(phoneNumber);

        if (customerDetails != null) {
            System.out.println("Customer found:");
            System.out.println("Name: " + customerDetails.getName());
            System.out.println("Email: " + customerDetails.getEmail());
            System.out.println("Phone: " + customerDetails.getPhone());
        } 
		else 
			{
            	System.out.println("Customer not found.");
        	}
		
		
		
		// 2: create repair order scenario
		// Tested and works

		String problemDescr = "Wheel is broken";
		String customerPhone = phoneNumber;
		String bikeSerialNo = "RJL403";
		controller.createRepairOrder(problemDescr, customerPhone, bikeSerialNo);



		
		// 3: find all repair orders scenario
		// Tested and works
		RepairOrderDTO[] repairOrders = controller.findAllRepairOrders();

		System.out.println("All repair orders:");

		for (int i = 0; i < repairOrders.length; i++) 
		{
    		System.out.println("Repair order id: " + repairOrders[i].getId());
    		System.out.println("Status: " + repairOrders[i].getStatus());
 			System.out.println("Total cost: " + repairOrders[i].getTotalCost());
    		System.out.println();
		}


		// Find customesrs repair order
		// Test works 
		String phoneNumberForRepairOrder = phoneNumber;

		RepairOrderDTO repairOrder = controller.findRepairOrder(phoneNumberForRepairOrder);

		if (repairOrder != null) {
			System.out.println("Repair order found before change:");
			System.out.println("Repair order id: " + repairOrder.getId());
			System.out.println("Status: " + repairOrder.getStatus());
			System.out.println("Total cost: " + repairOrder.getTotalCost());
		}	
		else 
		{
			System.out.println("No repair order found for phone number: " + phoneNumberForRepairOrder);
		}
		



        
		// Add diagnostic results and repair tasks loop scenario

		String repairOrderId = "RO4";

		String[] diagnosticResults = 
		{
			"Wheel is damaged",
			"Headlights are broken",
		};

		for (int i = 0; i < diagnosticResults.length; i++) 
		{
			controller.addDiagnosticResult(repairOrderId, diagnosticResults[i]);
		}

		String[] repairTasks = 
		{
			"Replace wheel",
			"Fix wiring",
		};

		int[] repairTaskCosts = 
		{
			999,
			499,
		};

		for (int i = 0; i < repairTasks.length; i++) 
		{
			controller.addRepairTask(repairOrderId, repairTasks[i], repairTaskCosts[i]);
		}

		RepairOrderDTO updatedRepairOrder = controller.getRepairOrderInfo(repairOrderId);

		if (updatedRepairOrder != null) 
		{
    		System.out.println("Updated repair order:");
    		System.out.println("Repair order id: " + updatedRepairOrder.getId());
    		System.out.println("Status: " + updatedRepairOrder.getStatus());
    		System.out.println("Sum cost: " + updatedRepairOrder.getTotalCost());
		} 
		else 
		{
    		System.out.println("Repair order not found: " + repairOrderId);
		}

		 
		// accept or reject repair order scenario
		String repairOrderIdToDecide = "RO4";
		boolean repairOrderAccepted = true;

		if (repairOrderAccepted) 
		{
			controller.acceptRepairOrder(repairOrderIdToDecide);
		} 
		else 
		{
			controller.rejectRepairOrder(repairOrderIdToDecide);
		}
    }
}