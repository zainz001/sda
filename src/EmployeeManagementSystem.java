// Abstract Factory Pattern
interface EmployeeFactory {
    Employee createEmployee();
}

class FullTimeEmployeeFactory implements EmployeeFactory {
    @Override
    public Employee createEmployee() {
        return new FullTimeEmployee();
    }
}

class PartTimeEmployeeFactory implements EmployeeFactory {
    @Override
    public Employee createEmployee() {
        return new PartTimeEmployee();
    }
}

// Adapter Pattern
interface Employee {
    void displayDetails();
}

class FullTimeEmployee implements Employee {
    @Override
    public void displayDetails() {
        System.out.println("Displaying full-time employee details.");
    }
}

class PartTimeEmployee implements Employee {
    @Override
    public void displayDetails() {
        System.out.println("Displaying part-time employee details.");
    }
}

interface ExternalHRSystem {
    void retrieveEmployeeData();
}

class ExternalHRSystemAdapter implements ExternalHRSystem {
    private final Employee employee;

    public ExternalHRSystemAdapter(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void retrieveEmployeeData() {
        System.out.println("Retrieving employee data from the external HR system.");
        employee.displayDetails();
    }
}

// Bridge Pattern
abstract class EmployeeDetails {
    protected Employee employee;

    public EmployeeDetails(Employee employee) {
        this.employee = employee;
    }

    public abstract void display();
}

class BasicEmployeeDetails extends EmployeeDetails {
    public BasicEmployeeDetails(Employee employee) {
        super(employee);
    }

    @Override
    public void display() {
        System.out.println("Displaying basic employee details.");
        employee.displayDetails();
    }
}

class AdvancedEmployeeDetails extends EmployeeDetails {
    public AdvancedEmployeeDetails(Employee employee) {
        super(employee);
    }

    @Override
    public void display() {
        System.out.println("Displaying advanced employee details.");
        employee.displayDetails();
    }
}

// Chain of Responsibility Pattern
interface EmployeeProcessor {
    void processCommand(String command);

    public void setNextProcessor(EmployeeProcessor processor2);
}

class ConcreteEmployeeProcessor1 implements EmployeeProcessor {
    private EmployeeProcessor nextProcessor;

    public void setNextProcessor(EmployeeProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void processCommand(String command) {
        if (command.equalsIgnoreCase("command1")) {
            System.out.println("Processing command 1 in ConcreteEmployeeProcessor1.");
        } else if (nextProcessor != null) {
            nextProcessor.processCommand(command);
        }
    }
}

class ConcreteEmployeeProcessor2 implements EmployeeProcessor {
    private EmployeeProcessor nextProcessor;

    public void setNextProcessor(EmployeeProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void processCommand(String command) {
        if (command.equalsIgnoreCase("command2")) {
            System.out.println("Processing command 2 in ConcreteEmployeeProcessor2.");
        } else if (nextProcessor != null) {
            nextProcessor.processCommand(command);
        }
    }
}

// Main class
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        // Abstract Factory Pattern
        EmployeeFactory fullTimeEmployeeFactory = new FullTimeEmployeeFactory();
        Employee fullTimeEmployee = fullTimeEmployeeFactory.createEmployee();

        EmployeeFactory partTimeEmployeeFactory = new PartTimeEmployeeFactory();
        Employee partTimeEmployee = partTimeEmployeeFactory.createEmployee();

        // Adapter Pattern
        ExternalHRSystem externalHRSystem = new ExternalHRSystemAdapter(fullTimeEmployee);
        externalHRSystem.retrieveEmployeeData();

        // Bridge Pattern
        EmployeeDetails basicEmployeeDetails = new BasicEmployeeDetails(fullTimeEmployee);
        basicEmployeeDetails.display();

        EmployeeDetails advancedEmployeeDetails = new AdvancedEmployeeDetails(partTimeEmployee);
        advancedEmployeeDetails.display();

        // Chain of Responsibility Pattern
        EmployeeProcessor processor1 = new ConcreteEmployeeProcessor1();
        EmployeeProcessor processor2 = new ConcreteEmployeeProcessor2();
        processor1.setNextProcessor(processor2);

        processor1.processCommand("command1");
        processor1.processCommand("command2");
        processor1.processCommand("command3");
    }
}
