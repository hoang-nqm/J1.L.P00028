package Services.Implements;

import Model.Customer;
import Services.Interfaces.ICustomerServices;
import Utils.FileUltils;
import Utils.Input;
import Utils.Validations;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersServices implements ICustomerServices {

    List<Customer> listCustomers;

    private final String customerFilePath ="src/resources/customer.dat";

    public CustomersServices() {
        listCustomers = FileUltils.loadCustomersFormatFile(customerFilePath);
    }

    @Override
    public void registerCustomer() {
        String customerCode ;
        String customerName ;
        String phoneNumber;
        String email ;
       do {
           System.out.printf("Enter Customer Code:");
           customerCode= Input.inputString();
           if(!Validations.isValidateCustomerCode(customerCode,listCustomers)){
               System.out.println("Customer Code begin with a letter C,G,K and followed by 4 digits and do not duplicated.");
           }

       }while(!Validations.isValidateCustomerCode(customerCode,listCustomers));
       do {
           System.out.printf("Enter Customer Name: ");
           customerName = Input.inputString();
           if(!Validations.isValidateName(customerName)){
               System.out.println("Customer Name co tu 2- 25 ky tu");
           }
       }while (!Validations.isValidateName(customerName));
        do {
            System.out.printf("Enter Phone Number: ");
            phoneNumber = Input.inputString();
            if (!Validations.isValidatePhone(phoneNumber)) {
                System.out.println("Phone Number Invalid");
            }
        } while (!Validations.isValidatePhone(phoneNumber));
        do {
            System.out.printf("Enter Email Customer: ");
            email = Input.inputString();
            if (!Validations.isValidateEmail(email)) {
                System.out.println("Email Invalid");
            }
        } while (!Validations.isValidateEmail(email));

        Customer customer = new Customer(customerName, phoneNumber, email, customerCode);
        listCustomers.add(customer);

        System.out.println("Customer Registered Successfully <3");

        System.out.println("Do you want to continue (Y | N)");
        String choice =Input.inputString();
        if(choice.equals("Y")){
            registerCustomer();
        }

    }

    @Override
    public void updateCustomer() {
        if (listCustomers.isEmpty()) {
            System.out.println("No customers to update.");
            return;
        }

        System.out.print("Enter Customer Code to update: ");
        String codeToUpdate = Input.inputString().trim().toUpperCase();

        Customer customerToUpdate = null;

        for (Customer customer : listCustomers) {
            if (customer.getCustomerCode().equalsIgnoreCase(codeToUpdate)) {
                customerToUpdate = customer;
                break;
            }
        }

        if (customerToUpdate == null) {
            System.out.println("Customer not found with code: " + codeToUpdate);
            return;
        }

        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-20s | %-20s \n","Code","Customer Name","Customer Phone","Email" );
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-20s | %-20s \n",
                customerToUpdate.getCustomerCode(), customerToUpdate.getName(), customerToUpdate.getPhoneNumber(), customerToUpdate.getEmail());

        while (true) {
            System.out.print("Enter new name (or press Enter to keep current): ");
            String newName = Input.inputString();
            if (newName.isEmpty()) break;
            if (Validations.isValidateName(newName)) {
                customerToUpdate.setName(newName);
                break;
            } else {
                System.out.println("Invalid name. Name must be 2–25 characters.");
            }
        }


        while (true) {
            System.out.print("Enter new phone number (or press Enter to keep current): ");
            String newPhone = Input.inputString();
            if (newPhone.isEmpty()) break;
            if (Validations.isValidatePhone(newPhone)) {
                customerToUpdate.setPhoneNumber(newPhone);
                break;
            } else {
                System.out.println(" Invalid phone number. Please enter a valid Vietnamese phone number.");
            }
        }


        while (true) {
            System.out.print("Enter new email (or press Enter to keep current): ");
            String newEmail = Input.inputString();
            if (newEmail.isEmpty()) break;
            if (Validations.isValidateEmail(newEmail)) {
                customerToUpdate.setEmail(newEmail);
                break;
            } else {
                System.out.println(" Invalid email format. Please try again.");
            }
        }

        System.out.println("Customer updated successfully!");
    }




    @Override
    public void searchCustomerByName() {
        listCustomers.sort(Comparator.comparing(c -> c.getName().toLowerCase()));

        System.out.printf("Enter Matching Customer Name: ");
        String searchName= Input.inputString();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-20s | %-20s \n","Code","Customer Name","Customer Phone","Email" );
        System.out.println("-----------------------------------------------------------------------------");
        boolean found = false;

        for (Customer customer : listCustomers) {
            if (customer.getName().toLowerCase().contains(searchName.toLowerCase().trim())) {
                System.out.printf("%-8s | %-20s | %-20s | %-20s \n",
                        customer.getCustomerCode(), customer.getName(), customer.getPhoneNumber(), customer.getEmail());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching customers found.");
        }
    }

    @Override
    public void displayCustomer() {
        System.out.println("Customers Information:");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-20s | %-20s \n","Code","Customer Name","Customer Phone","Email" );
        System.out.println("-----------------------------------------------------------------------------");
        for (Customer customer : listCustomers) {
            System.out.printf("%-8s | %-20s | %-20s | %-20s \n",
                    customer.getCustomerCode(), customer.getName(), customer.getPhoneNumber(), customer.getEmail());
        }
    }

    @Override
    public void saveCustomerToFile() {
        FileUltils.saveCustomerToFile(this.listCustomers,customerFilePath);
    }
}
