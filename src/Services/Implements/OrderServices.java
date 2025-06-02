package Services.Implements;

import Model.Customer;
import Model.Orders;
import Model.SetMenu;
import Services.Interfaces.IMenuServices;
import Services.Interfaces.IOrderServices;
import Utils.FileUltils;
import Utils.Input;
import Utils.Validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderServices implements IOrderServices {

    List<Customer> listCustomers;
    IMenuServices menuServices;
    List<SetMenu> setMenus;
    List<Orders> orders;

    public final String menuFilePath ="src/Resources/FeastMenu.csv";
    public final String orderFilePath ="src/Resources/feast_order_services.dat";

    public OrderServices() {
        listCustomers = FileUltils.loadCustomersFromFile(customerFilePath);
        menuServices = new MenuServices();
        setMenus = FileUltils.loadSetMenusFromFile(menuFilePath);
        orders = FileUltils.loadOrderFromFile(orderFilePath);
    }

    public final String customerFilePath ="src/Resources/customer.dat";
    @Override
    public void placeOrder() {
        boolean flag ;
       do {
           String customerCode;
           do {
               System.out.printf("Enter Customer Code:");
               customerCode = Input.inputString();
               if (!Validations.isValidateCustomerCodeOrder(customerCode, listCustomers)) {
                   System.out.println("Customer Code Sai dinh dang hoac khong ton tai trong list ");
               }

           } while (!Validations.isValidateCustomerCodeOrder(customerCode, listCustomers));

           menuServices.displayMenu();

           String menuCode;

           do {
               System.out.printf("Enter Menu Code:");
               menuCode = Input.inputString();
               if (!Validations.isValidateMenuCode(menuCode, setMenus)) {
                   System.out.println("SetMenu Code Khong ton tai trong list ");
               }

           } while (!Validations.isValidateMenuCode(menuCode, setMenus));

           int numberOfTable;

           do {
               System.out.printf("Enter Number of Table:");
               numberOfTable = Integer.parseInt(Input.inputString());
               if (!Validations.isValidNumber(numberOfTable)) {
                   System.out.println("Number must be great than 0");
               }

           } while (!Validations.isValidNumber(numberOfTable));

           String orderDate;
           do {
               System.out.printf("Enter Order Date(dd/MM/yyyy):");
               orderDate = Input.inputString();
               if (!Validations.isValidDate(orderDate)) {
                   System.out.println("Ngay pgai la ngay lon hon ngay hien tai");
               }

           } while (!Validations.isValidDate(orderDate));
           SetMenu setMenu = Validations.getSetMenuBySetMenuCode(menuCode,setMenus);
           Customer customer = Validations.getCustomerByCustomerCode(customerCode,listCustomers);
           double totalPrice = numberOfTable *setMenu.getPrice();
           String orderId = orders.size() +1+"";
           Orders order= new Orders(orderId,customer,setMenu,numberOfTable,totalPrice, LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
           orders.add(order);

           System.out.println("Đặt bàn thành công. Chi tiết đơn hàng:");
           System.out.println("---------------------------------------------------------------------------------------------");
           System.out.println("Customer order information: [Order ID: "+order.getOrderID()+" ]");
           System.out.println("---------------------------------------------------------------------------------------------");
           System.out.printf("  Customer code  : %s\n", order.getCustomer().getCustomerCode());
           System.out.printf("  Customer name : %s\n", order.getCustomer().getName());
           System.out.printf("  Phone Number : %s\n", order.getCustomer().getPhoneNumber());
           System.out.printf("  Email : %s\n", order.getCustomer().getEmail());
           System.out.println("---------------------------------------------------------------------------------------------");
           System.out.printf("  Code of Set Menu : %s\n", order.getSetMenu().getCode());
           System.out.printf("  Set Menu Name: %s\n", order.getSetMenu().getName());
           System.out.printf("  Even Date : %s\n", order.getOrderDate());
           System.out.printf("  Number of Table : %s\n", order.getNumberOfTable());
           System.out.printf("  Price : %,.0f VND\n", order.getSetMenu().getPrice());
           System.out.printf("  Ingredients: \n ");

           for (String ingredient : setMenu.getIngredients()) {
               System.out.println("     " + ingredient);
           }
           System.out.println("---------------------------------------------------------------------------------------------");
           System.out.printf("Total Cost : %,.0f VND\n", order.getTotalPrice());
           System.out.println("---------------------------------------------------------------------------------------------");

           System.out.println("Bạn có muốn tiếp tục đặt đơn khác không? (Y/N): ");
           String choice = Input.inputString().trim().toUpperCase();
           flag = choice.equals("Y");

       }while (flag);





    }

    @Override
    public void updateOrder() {

    }

    @Override
    public void displayOrder() {
       if(orders.size()==0){
           System.out.println("List order is empty");
       }else{
           System.out.println("List orders");
           System.out.println("----------------------------------------------------------------------------------------------------------------------------");
           System.out.printf("%-8s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s \n", "ID", "Event date", "CustomerID", "Set Menu", "Price", "Table", "Cost");
           System.out.println("----------------------------------------------------------------------------------------------------------------------------");
           for (Orders order : orders) {
               System.out.printf(
                       "%-8s | %-20s | %-20s | %-20s | %20s | %-20d | %20s \n",
                       order.getOrderID(),
                       order.getOrderDate(),
                       order.getCustomer().getCustomerCode(),
                       order.getSetMenu().getCode(),
                       String.format("%,.0f Vnd", (double) order.getSetMenu().getPrice()),
                       order.getNumberOfTable(),
                       String.format("%,.0f Vnd", (double) order.getTotalPrice())
               ); }
       }
    }

    @Override
    public void saveOrderToFile() {

            FileUltils.saveOrdersToFile(orders,orderFilePath);


    }
}
