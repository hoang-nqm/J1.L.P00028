package Services.Implements;

import Model.Customer;
import Model.Order;
import Model.SetMenu;
import Services.Interfaces.IMenuService;
import Services.Interfaces.IOrderService;
import Utils.FileUltils;
import Utils.Input;
import Utils.Validations;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {

    List<Customer> listCustomers;
    List<SetMenu> setMenus;
    IMenuService menuService;
    List<Order> orders;

    private final String customerFilePath = "src/resources/customer.dat";
    private final String MenusFilePath = "src/resources/FeastMenu.csv";
    private final String orderFilePath = "src/resources/feast_order_services.dat";


    public OrderService() {
        listCustomers = FileUltils.loadCustomersFormatFile(customerFilePath);
        setMenus = FileUltils.loadSetMenusFromFile(MenusFilePath);
        menuService = new MenuService();
        orders = FileUltils.loadOrdersFormatFile(orderFilePath);
    }

    @Override
    public void placeOrder() {
        boolean continueOrdering;
        do {
            String eventDate;
            do {
                System.out.println("Enter event (dd/MM/yyyy):  ");
                eventDate = Input.inputString();
                if (!Validations.isValidDate(eventDate)) {
                    System.out.println("nhap ngay lon hon");
                }
            } while (!Validations.isValidDate(eventDate));
            String customerCode;
            do {
                System.out.printf("Enter Customer Code:");
                customerCode = Input.inputString();
            } while (Validations.isValidateCustomerCode(customerCode, listCustomers));

            menuService.displayMenu();

            String menuCode;

            do {
                System.out.println("Enter MenuCode: ");
                menuCode = Input.inputString();
                if (!Validations.isValidMenuCode(menuCode, setMenus)) {
                    System.out.println("CodeMenu is not exist");
                }
            } while (!Validations.isValidMenuCode(menuCode, setMenus));

            int numberOfTable;
            do {
                System.out.println("Enter Number Of Table: ");
                numberOfTable = Integer.parseInt(Input.inputString());
                if (!Validations.isValidNumber(numberOfTable)) {
                    System.out.println("Number must be greater than zero");
                }
            } while (!Validations.isValidNumber(numberOfTable));
            SetMenu setMenu = Validations.getSetMenuByMenuCode(menuCode, setMenus);
            Customer customer = Validations.getCustomerByCustomerCode(customerCode, listCustomers);
            Double totalCost = numberOfTable * setMenu.getPrice();
            String orderId = orders.size() + 1 + "";
            Order order = new Order(orderId,
                    customer,
                    LocalDate.parse(eventDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    numberOfTable,
                    setMenu,
                    totalCost);
            orders.add(order);
            System.out.println("Đặt bàn thành công. Chi tiết đơn hàng:");
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.printf("Mã đơn     : %s\n", order.getOrderId());
            System.out.printf("Khách hàng : %s\n", order.getCustomer().getCustomerCode());
            System.out.printf("Ngày tổ chức: %s\n", order.getEvenDate());
            System.out.printf("Set Menu   : %s\n", order.getSetMenu().getCode());
            System.out.printf("Số bàn     : %d\n", order.getNumberOfTable());
            System.out.printf("Giá        : %,.0f Vnd\n", order.getSetMenu().getPrice());
            System.out.printf("Tổng chi phí: %,.0f Vnd\n", order.getTotalCost());
            System.out.println("---------------------------------------------------------------------------------------------");

            System.out.println("Bạn có muốn tiếp tục đặt đơn khác không? (Y/N): ");
            String choice = Input.inputString().trim().toUpperCase();
            continueOrdering = choice.equals("Y");
        }while (continueOrdering);

    }

    @Override
    public void updateOrder() {

    }

    @Override
    public void displayOrder() {
        System.out.println("List orders");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s \n", "ID", "Event date", "CustomerID", "Set Menu", "Price", "Table", "Cost");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        for (Order order : orders) {
            System.out.printf(
                    "%-8s | %-20s | %-20s | %-20s | %20s | %-20d | %20s \n",
                    order.getOrderId(),
                    order.getEvenDate(),
                    order.getCustomer().getCustomerCode(),
                    order.getSetMenu().getCode(),
                    String.format("%,.0f Vnd", (double) order.getSetMenu().getPrice()),
                    order.getNumberOfTable(),
                    String.format("%,.0f Vnd", (double) order.getTotalCost())
            ); }
    }

    @Override
    public void saveOrderToFile() {
        FileUltils.saveOrdersToFile(this.orders, orderFilePath);
    }
}
