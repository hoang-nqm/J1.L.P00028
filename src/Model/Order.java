package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Order implements Serializable {
    private String orderId;
    private Customer customer;
    private LocalDate evenDate;
    private int numberOfTable;
    private SetMenu setMenu;
    private double totalCost;

    public Order(String orderId, Customer customer, LocalDate evenDate, int numberOfTable, SetMenu setMenu, double totalCost) {
        this.orderId = orderId;
        this.customer = customer;
        this.evenDate = evenDate;
        this.numberOfTable = numberOfTable;
        this.setMenu = setMenu;
        this.totalCost = totalCost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getEvenDate() {
        return evenDate;
    }

    public void setEvenDate(LocalDate evenDate) {
        this.evenDate = evenDate;
    }

    public int getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public SetMenu getSetMenu() {
        return setMenu;
    }

    public void setSetMenu(SetMenu setMenu) {
        this.setMenu = setMenu;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
