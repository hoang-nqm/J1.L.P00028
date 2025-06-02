package Utils;

import Model.Customer;
import Model.Orders;
import Model.SetMenu;

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

    public static void saveOrdersToFile(List<Orders> orders, String filePath) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(orders);
            System.out.println("Save Orders successfully at: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Customer> loadCustomersFromFile(String filePath) {
        List<Customer> customers = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists() || file.length()==0) {
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
    public static List<Orders> loadOrderFromFile(String filePath) {
        List<Orders> orders = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()||file.length()==0) {
            return orders;
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            orders = (List<Orders>) ois.readObject();
            return orders;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<SetMenu> loadSetMenusFromFile(String filePath) {
        List<SetMenu> setMenus = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File "+filePath+" does not exist");
        }



        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",", 4);
                if (parts.length == 4) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());

                    List<String> ingredients = new ArrayList<>();
                    String cleanedIngredients = parts[3].replace("\"", "");
                    String[] ingredientLines = cleanedIngredients.split("#");
                    for (String ing : ingredientLines) {
                        ingredients.add(ing.trim());
                    }
                    setMenus.add(new SetMenu(code, name, price, ingredients));
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return setMenus;
    }



}
