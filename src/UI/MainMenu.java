package UI;

import Services.Implements.CustomersServices;
import Services.Implements.MenuService;
import Services.Implements.OrderService;
import Services.Interfaces.ICustomerServices;
import Services.Interfaces.IMenuService;
import Services.Interfaces.IOrderService;
import Utils.Input;

public class MainMenu {

    ICustomerServices customerServices;
    IMenuService menuServices;
    IOrderService orderService;

    public MainMenu() {
        customerServices = new CustomersServices();
        menuServices = new MenuService();
        orderService =new OrderService();
    }

    public void mainMenu() {
        int choice;
        do {
            System.out.println("Welcome to the main menu");
            System.out.println("Please select one of the following options:");
            System.out.println("1. Register Customer");
            System.out.println("2. Update Customer Information");
            System.out.println("3. Search Customer");
            System.out.println("4. Display Feats Menu");
            System.out.println("5. Display Feats Orders");
            System.out.println("6. Update order information");
            System.out.println("7. Save data to file");
            System.out.println("8. Display Customer or Orders Lists");
            System.out.println("Orther. Exit");

            Input input = new Input();
            choice = input.inputMenuChoice();


            switch (choice) {
                case 1:
                    customerServices.registerCustomer();
                    break;
                case 2:
                    customerServices.updateCustomer();
                    break;
                case 3:
                    System.out.println("Search Customer");
                    break;
                case 4:
                    menuServices.displayMenu();
                    break;
                case 5:
                    orderService.placeOrder();
                    break;
                case 6:
                    System.out.println("Update order information");
                    break;
                case 7:
                    customerServices.saveCustomerToFile();
                    orderService.saveOrderToFile();
                    break;
                case 8:
                    customerServices.displayCustomer();
                    orderService.displayOrder();
                    break;
                default:
                    System.out.println("Goodbye!!!");
            }
        } while (choice >= 1 && choice <= 8);

    }
}
