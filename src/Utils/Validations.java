package Utils;

import Model.Customer;
import Model.SetMenu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public static boolean isValidateCustomerCodeOrder(String customerCode, List<Customer> customers) {
        String regex = "^[CGK]\\d{4}$";
        if(!customerCode.matches(regex)) {
            return false;
        }

        for (Customer c:customers){
            if(!c.getCustomerCode().equals(customerCode)){
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

    public static boolean isValidateMenuCode(String menuCode , List<SetMenu> setMenus) {
        for (SetMenu setMenu:setMenus){
            if(setMenu.getCode().equals(menuCode)){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidNumber(int number ) {
        return number > 0;
    }
// 26/5  25/5
    public static boolean isValidDate(String date) {
       try {
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
           LocalDate localDate = LocalDate.parse(date, formatter);
           LocalDate today = LocalDate.now();
           Boolean check = localDate.isAfter(today);
           if(check==true) {
               return true;
           }
       }catch (Exception e) {
           e.printStackTrace();
       }
       return false;
    }
     public static Customer getCustomerByCustomerCode(String customerCode, List<Customer> customers) {
      for (Customer c:customers){
         if(c.getCustomerCode().equals(customerCode)){
             return c;
         }
      }
      return null;
     }
    public static SetMenu getSetMenuBySetMenuCode(String menuCode, List<SetMenu> setMenus) {
        for (SetMenu setMenu:setMenus){
            if(setMenu.getCode().equals(menuCode)){
                return setMenu;
            }
        }
        return null;
    }


}
