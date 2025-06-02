package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Orders implements Serializable {
    private String orderID;
    private  Customer customer;
    private SetMenu setMenu;
    private int numberOfTable;
    private double totalPrice;
    private LocalDate orderDate;

    public Orders(String orderID, Customer customer, SetMenu setMenu, int numberOfTable, double totalPrice, LocalDate orderDate) {
        this.orderID = orderID;
        this.customer = customer;
        this.setMenu = setMenu;
        this.numberOfTable = numberOfTable;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SetMenu getSetMenu() {
        return setMenu;
    }

    public void setSetMenu(SetMenu setMenu) {
        this.setMenu = setMenu;
    }

    public int getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
