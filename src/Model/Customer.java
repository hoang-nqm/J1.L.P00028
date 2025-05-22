package Model;

public class Customer extends Person{

    private String customerCode;

    public Customer(String name, String phoneNumber, String email,String customerCode) {
        super(name, phoneNumber, email);
        this.customerCode = customerCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public void displayInfor() {
        System.out.println("CustomerCode: " + this.getCustomerCode());
        System.out.println("Name: " + this.getName());
        System.out.println("PhoneNumber: " + this.getPhoneNumber());
        System.out.println("Email: " + this.getEmail());

    }
}
