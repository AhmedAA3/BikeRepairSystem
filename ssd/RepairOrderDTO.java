package se.kth.iv1350.bikerepair.model.dto;

import se.kth.iv1350.bikerepair.model.entity.RepairOrder;
import java.util.Date;

/**
 * Contains all information about a repair order, including costs and diagnostic results.
 * This is used to inform the customer as per system requirements.
 */
public class RepairOrderDTO {
    private final String id;
    private final Date date;
    private final String state;
    private final String totalCost;

    /**
     * Creates a new instance based on the state of the specified repair order.
     * * @param order The repair order to extract data from.
     */
    public RepairOrderDTO(RepairOrder order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.state = order.getState();
        this.totalCost = order.calculateTotalCost().toString();
    }

    /**
     * Returns the unique identifier for the repair order.
     * * @return The order ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the date when the repair order was created.
     * * @return The creation date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the current state of the repair order.
     * * @return The state.
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the total cost of the repair as a formatted string.
     * * @return The total cost.
     */
    public String getTotalCost() {
        return totalCost;
    }
}
