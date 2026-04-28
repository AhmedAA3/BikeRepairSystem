package MVCsem2.controller;

import MVCsem2.integration.register.CustomerRegistry;
import MVCsem2.integration.register.RepairOrderRegistry;
import MVCsem2.model.dto.CustomerDetailsDTO;
import MVCsem2.model.dto.RepairOrderDTO;
import MVCsem2.model.device.Printer;
import MVCsem2.model.entity.Customer;
import MVCsem2.model.entity.RepairOrder;

public class Controller {


	private Printer printer;

	private CustomerRegistry customerRegistry;

	private RepairOrderRegistry repairOrderRegistry;

	public Controller(CustomerRegistry customerRegistry, RepairOrderRegistry repairOrderRegistry, Printer printer) {
    this.customerRegistry = customerRegistry;
    this.repairOrderRegistry = repairOrderRegistry;
    this.printer = printer;
	}




	 /**
	 * Finds customer details by using their phone number.
	 * @param phoneNumber The phone number used to find the customer.
	 * @return Customer details (DTO) if the customer exists, otherwise null.
	 */

	public CustomerDetailsDTO findCustomer(String phoneNumber) {
		Customer customer = customerRegistry.findCustomer(phoneNumber);

    if (customer == null) {
        return null;
	}
	return new CustomerDetailsDTO(customer);
	}

	/**
	 * Creates a new repair order using the provided input data
	 * @param problemDescr The problem description.
	 * @param customerPhone The customers phone number.
	 * @param bikeSerialNo The bike serial number.
	 */
	public void createRepairOrder(String problemDescr, String customerPhone, String bikerSerialNo) {
		repairOrderRegistry.createRepairOrder(problemDescr, customerPhone, bikerSerialNo);
	}

	/**
	* Finds all repair orders and converts them to array DTOs.
	* @return All repair orders as DTOs.
	*/
	public RepairOrderDTO[] findAllRepairOrders() {
		RepairOrder[] repairList = repairOrderRegistry.findAllRepairOrders();
		return RepairOrderDTO.createRepairOrderDTO(repairList);
	}

	/**
	* Finds one repair order using the customers phone number and converts it to a customer DTO.
	* @param phoneNumber The customers phone number.
	* @return The customer object as a DTO, or null if no repair order was found.
	*/
	public RepairOrderDTO findRepairOrder(String phoneNumber) {
		RepairOrder foundRepairOrder = repairOrderRegistry.findRepairOrder(phoneNumber);
		if (foundRepairOrder == null) {
			return null;
		}

		return new RepairOrderDTO(foundRepairOrder);
	}

	 /**
	 * Adds a diagnostic result to a the specified repair order
	 * @param repairOrderId The repair order id.
	 * @param diagTaskResult The diagnostic result to add.
	 */
	public void addDiagnosticResult(String repairOrderId, String diagTaskResult) {
    	RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(repairOrderId);

    	if (repairOrder == null) {
        	return;
    	}

    	repairOrder.addDiagnosticResult(diagTaskResult);
	}

		/**
	 * Adds a repair task and its cost to a repair order.
	 * @param repairOrderId The repair order id.
	 * @param repairTask The repair task to add.
	 * @param cost The cost of the repair task.
	 */
	public void addRepairTask(String repairOrderId, String repairTask, int cost) {
		RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(repairOrderId);

		if (repairOrder == null) {
			return;
		}

		repairOrder.addRepairTask(repairTask, cost);
		repairOrder.calculateTotalCost(cost);
	}

	 /**
	 * Accepts a repair order, updates it in the repair order registry and then prints it out.
	 * @param repairOrderId The id of the repair order to accept.
	 */
	public void acceptRepairOrder(String repairOrderId) {
    	RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(repairOrderId);

    	if (repairOrder == null) {
        	return;
    	}

    	repairOrder.accept();
    	repairOrderRegistry.updateRepairOrder(repairOrder);
    	printer.printRepairOrder(repairOrder);
	}

		/**
	 * Rejects a repair order and updates it in the repair order registry.
	 *
	 * @param repairOrderId The id of the repair order to reject.
	 */
	public void rejectRepairOrder(String repairOrderId) {
	
		RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(repairOrderId);

		if (repairOrder == null) {
			return;
		}

		repairOrder.reject();
		repairOrderRegistry.updateRepairOrder(repairOrder);
	}

	 /**
	 * Gets information about one repair order.
	 *
	 * @param repairOrderId The id of the repair order.
	 * @return The repair order information as a DTO, or null if no repair order was found.
	 */
	public RepairOrderDTO getRepairOrderInfo(String repairOrderId) {
		RepairOrder foundRepairOrder = repairOrderRegistry.getRepairOrder(repairOrderId);

		if (foundRepairOrder == null) {
			return null;
		}
		return new RepairOrderDTO(foundRepairOrder);
	}

}
