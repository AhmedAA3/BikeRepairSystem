package MVCsem2.controller;

import MVCsem2.integration.register.CustomerRegistry;
import MVCsem2.integration.register.RepairOrderRegistry;
import MVCsem2.model.dto.CustomerDetailsDTO;
import MVCsem2.model.dto.RepairOrderDTO;
import MVCsem2.model.device.Printer;

public class Controller {

	private CustomerRegistry customerRegistry;

	private RepairOrderRegistry repairOrderRegistry;

	public CustomerDetailsDTO findCustomer(String phoneNumber) {
		return null;
	}

	public void createRepairOrder(String problemDescr, String customerPhone, String bikerSerialNO) {

	}

	public RepairOrderDTO[] findAllRepairOrders() {
		return null;
	}

	public RepairOrderDTO findRepairOrder(String phoneNumber) {
		return null;
	}

	public void addDiagnosticResult(String repairOrderId, String diagTaskResult) {

	}

	public void addRepairTask(String repairOrderId, String repairTask, int cost) {

	}

	public void acceptRepairOrder(String repairOrderId) {

	}

	public void rejectRepairOrder(String repairOrderId) {

	}

	public Controller createController(CustomerRegistry customerRegistry, RepairOrderRegistry repairOrderRegisrty, Printer printer) {
		return null;
	}

	public RepairOrderDTO getRepairOrderInfo(String repairOrderId) {
		return null;
	}

}
