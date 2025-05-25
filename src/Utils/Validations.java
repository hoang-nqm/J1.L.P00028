package Utils;

import Model.Customer;

import java.util.List;

public class Validations {

    public static boolean isValidateCustomerCode(String customerCode, List<Customer> customers) {
        String regex = "^[CGK]\\d{4}$";
        if(!customerCode.matches(regex)) {
            return false;
        }

        for (Customer c:customers){
            if(c.getCustomerCode().equals(customerCode)){
                return false;
            }
        }

        return true;
    }

    public static boolean isValidateName(String customerName) {
        String regex = "^.{2,25}$";
        if(!customerName.matches(regex)) {
            return false;
        }
        return true;
    }

    public static boolean isValidatePhone(String customerPhone) {
        String regex = "^(03[2-9]|05[6|8|9]|07[0|6-9]|08[1-5|8|9]|09[0-9])\\d{7}$";
        if(!customerPhone.matches(regex)) {
            return false;
        }
        return true;
    }

    public static boolean isValidateEmail(String customerEmail) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if(!customerEmail.matches(regex)) {
            return false;
        }
        return true;

    }
}
