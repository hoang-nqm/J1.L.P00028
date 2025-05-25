package Utils;

import Model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUltils {

    public static void saveCustomerListToFile(List<Customer> customers, String filePath) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(customers);
            System.out.println("Save CustomerList successfully at: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Customer> loadCustomersFromFile(String filePath) {
        List<Customer> customers = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return customers;
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            customers = (List<Customer>) ois.readObject();
            return customers;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
