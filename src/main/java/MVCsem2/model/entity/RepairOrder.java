package MVCsem2.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a repair order for an electric bicycle.
 */
public class RepairOrder {
    private String repairOrderId;
    private String problemDescription;
    private String customerPhone;
    private String bikeSerialNo;
    private DiagnosticReport diagnosticReport;
    private List<String> repairTasks;
    private List<Integer> repairTaskCosts;
    private Status status;

    private enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    /**
     * Initializes an empty repair order.
     */
    public RepairOrder() {
        this("", "", "", "");
    }

    /**
     * Initializes a repair order without an ID.
     */
    public RepairOrder(String problemDescription, String customerPhone, String bikeSerialNo) {
        this("", problemDescription, customerPhone, bikeSerialNo);
    }

    /**
     * Initializes a repair order with an ID, problem description, customer phone, and bike serial number.
     */
    public RepairOrder(String repairOrderId, String problemDescription, String customerPhone, String bikeSerialNo) {
        this.repairOrderId = valueOrEmpty(repairOrderId);
        this.problemDescription = valueOrEmpty(problemDescription);
        this.customerPhone = valueOrEmpty(customerPhone);
        this.bikeSerialNo = valueOrEmpty(bikeSerialNo);
        this.diagnosticReport = new DiagnosticReport();
        this.repairTasks = new ArrayList<>();
        this.repairTaskCosts = new ArrayList<>();
        this.status = Status.PENDING;
    }

    /**
     * Appends a diagnostic result to the repair order.
     */
    public void addDiagnosticResult(String diagTaskResult) {
        diagnosticReport.addDiagnosticResult(diagTaskResult);
    }

    /**
     * Appends a repair task and its associated cost to the repair order.
     */
    public void addRepairTask(String repairTask, int cost) {
        if (repairTask == null || repairTask.trim().isEmpty()) {
            return;
        }

        if (cost < 0) {
            return;
        }

        repairTasks.add(repairTask);
        repairTaskCosts.add(cost);
    }

    /**
     * Marks the repair order as accepted.
     */
    public void accept() {
        status = Status.ACCEPTED;
    }

    /**
     * Marks the repair order as rejected.
     */
    public void reject() {
        status = Status.REJECTED;
    }

    /**
     * Computes the total cost of all repair tasks.
     */
    public int calculateTotalCost(int cost) {
        int totalCost = 0;

        for (Integer taskCost : repairTaskCosts) {
            totalCost += taskCost;
        }

        return totalCost;
    }

    /**
     * Retrieves the repair order ID.
     */
    public String getRepairOrderId() {
        return repairOrderId;
    }

    /**
     * Retrieves the description of the problem.
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    /**
     * Retrieves the customer's phone number associated with this repair order.
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Retrieves the bike's serial number associated with this repair order.
     */
    public String getBikeSerialNo() {
        return bikeSerialNo;
    }

    /**
     * Retrieves a copy of the diagnostic report.
     */
    public DiagnosticReport getDiagnosticReport() {
        return new DiagnosticReport(diagnosticReport);
    }

    /**
     * Retrieves all diagnostic results from the report.
     */
    public List<String> getDiagnosticResults() {
        return diagnosticReport.getDiagnosticResults();
    }

    /**
     * Retrieves all descriptions of repair tasks.
     */
    public List<String> getRepairTasks() {
        return new ArrayList<>(repairTasks);
    }

    /**
     * Retrieves all costs associated with repair tasks.
     */
    public List<Integer> getRepairTaskCosts() {
        return new ArrayList<>(repairTaskCosts);
    }

    /**
     * Retrieves the total cost of the repair order.
     */
    public int getTotalCost() {
        return calculateTotalCost(0);
    }

    /**
     * Checks if the repair order has been accepted.
     */
    public boolean isAccepted() {
        return status == Status.ACCEPTED;
    }

    /**
     * Checks if the repair order has been rejected.
     */
    public boolean isRejected() {
        return status == Status.REJECTED;
    }

    /**
     * Retrieves the current status of the repair order.
     */
    public String getStatus() {
        if (status == Status.ACCEPTED) {
            return "Accepted";
        }

        if (status == Status.REJECTED) {
            return "Rejected";
        }

        return "Pending";
    }

    private String valueOrEmpty(String value) {
        if (value == null) {
            return "";
        }
        return value;
    }
}