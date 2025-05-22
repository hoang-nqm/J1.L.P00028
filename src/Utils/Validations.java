package Utils;

public class Validations {

    public static boolean isValidateCustomerCode(String customerCode) {
        String regex = "^[CGK]\\d{4}$";
        if(!customerCode.matches(regex)) {
            return false;
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
        String regex = "^(0?)(3[2-9]|5[689]|7(?:0|[6-9])|8[1-9]|9\\\\d)\\\\d{7}$";
        if(!customerPhone.matches(regex)) {
            return false;
        }
        return true;
    }

    public static boolean isValidateEmail(String customerEmail) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n";
        if(!customerEmail.matches(regex)) {
            return false;
        }
        return true;

    }
}
