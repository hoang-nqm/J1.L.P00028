package Utils;

import Model.Customer;
import Model.SetMenu;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Validations {

    public static boolean isValidateCustomerCode(String customerCode, List<Customer> customers) {
        String regex = "^[CGK]\\d{4}$";
        if (!customerCode.matches(regex)) {
            return false;
        }
        for (Customer c : customers) {
            if (c.getCustomerCode().equals(customerCode)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidateName(String customerName) {
        String regex = "^.{2,25}$";
        if (!customerName.matches(regex)) {
            return false;
        }
        return true;
    }

    public static boolean isValidatePhone(String customerPhone) {
        String regexPhone = "^(03[2-9]|05[6|8|9]|07[0|6-9]|08[1-5|8|9]|09[0-9])\\d{7}$";
        if (!customerPhone.matches(regexPhone)) {
            return false;
        }
        return true;
    }

    public static boolean isValidateEmail(String customerEmail) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!customerEmail.matches(regex)) {
            return false;
        }
        return true;

    }

    public static boolean isValidMenuCode(String menuCode, List<SetMenu> setMenus) {
        for (SetMenu setMenu : setMenus) {
            if (menuCode.equalsIgnoreCase(setMenu.getCode())) {
                return true;
            }
        }
        return false;
    }
    public static boolean isValidNumber(int number){
        return number>0;
    }

    public static boolean isValidDate(String date){
        try{
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate evenDate = LocalDate.parse(date,dateTimeFormatter);
            LocalDate now = LocalDate.now();
            Boolean check = evenDate.isAfter(now);
            if(check==true){
                return true;
            }
        }catch (DateTimeException e){
            System.out.println(e);
        }
        return false;
    }


    public static SetMenu getSetMenuByMenuCode(String menuCode, List<SetMenu> setMenus){
        for (SetMenu s: setMenus ){
            if(s.getCode().equals(menuCode)){
                return s;
            }
        }
        return null;
    }
    public static Customer getCustomerByCustomerCode(String customerCode, List<Customer> customers){
        for (Customer s: customers ){
            if(s.getCustomerCode().equals(customerCode)){
                return s;
            }
        }
        return null;
    }
}
