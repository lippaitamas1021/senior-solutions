package employees;

import java.util.Scanner;

public class EmployeesController {

    private Scanner scanner = new Scanner(System.in);

//    private EmployeesService employeesService =
//            new EmployeesService(new MariadbEmployeesRepository());

    private EmployeesService employeesService =
            new EmployeesService(new InMemoryEmployeesRepository());


    public static void main(String[] args) {
        new EmployeesController().start();
    }

    public void start() {
        System.out.println("");

        for (int i = 0; i < 5; i++) {
            String name = scanner.nextLine();
            employeesService.save(name);
        }

        System.out.println(employeesService.listEmployees());
    }
}
