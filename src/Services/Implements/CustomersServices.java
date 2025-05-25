package Services.Implements;

import Model.Customer;
import Services.Interfaces.ICustomerServices;
import UI.MainMenu;
import Utils.FileUltils;
import Utils.Input;
import Utils.Validations;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersServices implements ICustomerServices {

    List<Customer> listCustomers;

    public CustomersServices() {
        listCustomers = FileUltils.loadCustomersFromFile(customerFilePath);
    }

    public final String customerFilePath ="src/Resources/customer.dat";

    @Override
    public void registerCustomer() {
        String customerCode;
        String customerName;
        String phoneNumber;
        String email;
        do {
            System.out.printf("Enter Customer Code:");
            customerCode = Input.inputString();
            if (!Validations.isValidateCustomerCode(customerCode, listCustomers)) {
                System.out.println("Customer Code begin with a letter C,G,K and followed by 4 digits and do not duplicated.");
            }

        } while (!Validations.isValidateCustomerCode(customerCode, listCustomers));
        do {
            System.out.printf("Enter Customer Name: ");
            customerName = Input.inputString();
            if (!Validations.isValidateName(customerName)) {
                System.out.println("Customer Name co tu 2- 25 ky tu");
            }
        } while (!Validations.isValidateName(customerName));
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

        System.out.println("Do you want to register continue ( Y | N )?");

        String choice = Input.inputString();

        if (choice.equalsIgnoreCase("Y")) {
            registerCustomer();
        }


    }

    @Override
    public void updateCustomer() {

        if (listCustomers.isEmpty()) {
            System.out.println("No Customers to update");
            return;
        }

        System.out.println("Enter CustomerCode to update:");
        String codeToUpdate = Input.inputString().toUpperCase().trim();

        Customer customerUpdate = null;

        for (Customer c : listCustomers) {
            if (c.getCustomerCode().equals(codeToUpdate)) {
                customerUpdate = c;
                break;
            }
        }

        if (customerUpdate == null) {
            System.out.println("This customer does not exist!");
            updateCustomer();
        }
        String newName;
        String newPhone;
        String newEmail;
        do {
            System.out.println("Enter new name (or press Enter to keep current): ");
            newName = Input.inputString();
            if (newName.isEmpty()) break;
            if (Validations.isValidateName(newName)) {
                customerUpdate.setName(newName);
            } else {
                System.out.println("Customer Name co tu 2- 25 ky tu");
            }
        } while (!Validations.isValidateEmail(newName));

        do {
            System.out.println("Enter new phoneNumber (or press Enter to keep current): ");
            newPhone = Input.inputString();
            if (newPhone.isEmpty()) break;
            if (Validations.isValidatePhone(newPhone)) {
                customerUpdate.setPhoneNumber(newPhone);
            } else {
                System.out.println("Phone Number Invalid");
            }
        } while (!Validations.isValidateEmail(newPhone));

        do {
            System.out.println("Enter new email (or press Enter to keep current): ");
            newEmail = Input.inputString();
            if (newEmail.isEmpty()) break;
            if (Validations.isValidateEmail(newEmail)) {
                customerUpdate.setEmail(newEmail);
                break;
            } else {
                System.out.println("Email Invalid");
            }
        } while (!Validations.isValidateEmail(newEmail));


        System.out.println("Customer updated successfully!");

        System.out.println("Do you want to update continue ( Y | N )?");

        String choice = Input.inputString();

        if (choice.equalsIgnoreCase("Y")) {
            updateCustomer();
        }else {
            MainMenu mainMenu = new MainMenu();
            mainMenu.mainMenu();
        }

    }

    @Override
    public void searchCustomerByName() {

        if (listCustomers.isEmpty()) {
            System.out.println("No Customers to update");
            return;
        }

        listCustomers.sort(Comparator.comparing(customer -> customer.getName().toLowerCase()));

        System.out.printf("Enter matching customer name: ");
        String searchName = Input.inputString();
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-20s | %-20s \n", "Code", "Customer Name", "Phone", "Email");
        System.out.println("---------------------------------------------------------------");

        boolean found=false;

        for (Customer customer : listCustomers){
            //An
             if(customer.getName().toLowerCase().contains(searchName.toLowerCase().trim())){
                 System.out.printf("%-8s | %-20s | %-20s | %-20s \n", customer.getCustomerCode(), customer.getName(), customer.getPhoneNumber(), customer.getEmail());
                 found =true;
             }
        }

        if(found==false){
            System.out.println("No one matches the search criteria!");
        }

    }

    @Override
    public void displayCustomer() {

        System.out.println("Customer Information:");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-20s | %-20s \n", "Code", "Customer Name", "Phone", "Email");
        System.out.println("---------------------------------------------------------------");
        for (Customer customer : listCustomers) {
            System.out.printf("%-8s | %-20s | %-20s | %-20s \n", customer.getCustomerCode(), customer.getName(), customer.getPhoneNumber(), customer.getEmail());
        }
        System.out.println("---------------------------------------------------------------");
    }

    @Override
    public void saveCustomerToFile() {
        FileUltils.saveCustomerListToFile(this.listCustomers,customerFilePath);
    }
}
