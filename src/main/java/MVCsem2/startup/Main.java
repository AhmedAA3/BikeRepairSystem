package MVCsem2.startup;

import MVCsem2.controller.Controller;
import MVCsem2.integration.register.CustomerRegistry;
import MVCsem2.integration.register.RepairOrderRegistry;
import MVCsem2.model.device.Printer;
import MVCsem2.view.View;

/**
 * Starts the application.
 */
public class Main {

    /**
     * The main method used to start up  the system.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();

        Controller controller = new Controller(customerRegistry, repairOrderRegistry, printer);
        View view = new View(controller);

        view.start();
    }
}