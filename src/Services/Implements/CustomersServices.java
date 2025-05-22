package Services.Implements;

import Model.Customer;
import Services.Interfaces.ICustomerServices;
import Utils.Input;
import Utils.Validations;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomersServices implements ICustomerServices {

    ArrayList<Customer> listCustomers;

    public CustomersServices() {
        listCustomers = new ArrayList<>();
    }

    @Override
    public void registerCustomer() {
        String customerCode ;
        String customerName ;
        String phoneNumber="0356449764" ;
        String email="hoang@gmail.com" ;
       do {
           System.out.printf("Enter Customer Code:");
           customerCode= Input.inputString();
           if(!Validations.isValidateCustomerCode(customerCode)){
               System.out.println("Customer Code begin with a letter C,G,K and followed by 4 digits.");
           }

       }while(!Validations.isValidateCustomerCode(customerCode));
       do {
           System.out.printf("Enter Customer Name: ");
           customerName = Input.inputString();
           if(!Validations.isValidateName(customerName)){
               System.out.println("Customer Name co tu 2- 25 ky tu");
           }
       }while (!Validations.isValidateName(customerName));
//        do {
//            System.out.printf("Enter Phone Number: ");
//            phoneNumber = Input.inputString();
//            if(!Validations.isValidatePhone(phoneNumber)){
//                System.out.println("Phone Number Invalid");
//            }
//        }while (!Validations.isValidatePhone(phoneNumber));
//        do {
//            System.out.printf("Enter Email Customer: ");
//            email = Input.inputString();
//            if(!Validations.isValidateEmail(email)){
//                System.out.println("Email Invalid");
//            }
//        }while (!Validations.isValidateEmail(email));

        Customer customer = new Customer(customerName, phoneNumber, email, customerCode);
        listCustomers.add(customer);

        System.out.println("Customer Registered Successfully <3");

    }

    @Override
    public void updateCustomer() {

    }

    @Override
    public void searchCustomerByName() {

    }

    @Override
    public void displayCustomer() {
        for (Customer customer : listCustomers) {
            customer.displayInfor();
            System.out.println("--------------");
        }
    }
}
