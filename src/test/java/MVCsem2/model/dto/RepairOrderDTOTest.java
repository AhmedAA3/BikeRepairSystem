package MVCsem2.model.dto;

import MVCsem2.model.entity.RepairOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepairOrderDTOTest {

    @Test
    public void dtoShouldContainBasicInformationFromScenarioOrder() {
        RepairOrder orderFromScenario = new RepairOrder("RO4", "Wheel is broken", "0737654321", "RJL403");

        RepairOrderDTO orderDetails = new RepairOrderDTO(orderFromScenario);

        assertEquals("RO4", orderDetails.getId(),
                "The DTO should contain the repair order id from the scenario.");
        assertEquals("Pending", orderDetails.getStatus(),
                "The DTO should contain the initial repair order status.");
        assertEquals(0, orderDetails.getTotalCost(),
                "The DTO should contain zero total cost before repair tasks are added.");
    }

    @Test
    public void dtoShouldCopyRepairTasksAndTotalCostFromScenarioOrder() {
        RepairOrder orderWithTasks = new RepairOrder("RO4", "Wheel is broken", "0737654321", "RJL403");

        String wheelTask = "Replace wheel";
        String wiringTask = "Fix wiring";
        int wheelCost = 999;
        int wiringCost = 499;

        orderWithTasks.addRepairTask(wheelTask, wheelCost);
        orderWithTasks.addRepairTask(wiringTask, wiringCost);

        RepairOrderDTO orderDetails = new RepairOrderDTO(orderWithTasks);

        assertEquals(wheelTask, orderDetails.getTasks().get(0),
                "The first task in the DTO should be the wheel replacement.");
        assertEquals(wiringTask, orderDetails.getTasks().get(1),
                "The second task in the DTO should be the wiring repair.");
        assertEquals(wheelCost + wiringCost, orderDetails.getTotalCost(),
                "The DTO should contain the total cost of the scenario repair tasks.");
    }

    @Test
    public void dtoShouldShowAcceptedStatusAfterOrderIsAccepted() {
        RepairOrder acceptedOrder = new RepairOrder("RO4", "Wheel is broken", "0737654321", "RJL403");
        acceptedOrder.accept();

        RepairOrderDTO orderDetails = new RepairOrderDTO(acceptedOrder);

        assertEquals("Accepted", orderDetails.getStatus(),
                "The DTO should contain the updated accepted status.");
    }

    @Test
    public void dtoArrayShouldKeepSameNumberOfRepairOrders() {
        RepairOrder firstOrder = new RepairOrder("RO1", "", "0701234567", "BIKE100");
        RepairOrder cristianoOrder = new RepairOrder("RO2", "Brakes need adjustment", "0737654321", "BIKE200");
        RepairOrder motorOrder = new RepairOrder("RO3", "Motor makes strange noise", "0761112233", "BIKE300");
        RepairOrder wheelOrder = new RepairOrder("RO4", "Wheel is broken", "0737654321", "RJL403");

        RepairOrder[] orders = { firstOrder, cristianoOrder, motorOrder, wheelOrder };

        RepairOrderDTO[] orderDTOs = RepairOrderDTO.createRepairOrderDTO(orders);

        assertEquals(4, orderDTOs.length,
                "The DTO array should contain one DTO for each repair order.");
        assertEquals("RO4", orderDTOs[3].getId(),
                "The last DTO should represent the scenario repair order.");
    }
}