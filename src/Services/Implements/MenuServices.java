package Services.Implements;

import Model.SetMenu;
import Services.Interfaces.IMenuServices;
import Utils.FileUltils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuServices implements IMenuServices {

    List<SetMenu> setMenus;

    public final String menuFilePath ="src/Resources/FeastMenu.csv";

public MenuServices() {
    setMenus = FileUltils.loadSetMenusFromFile(menuFilePath);
}


    @Override
    public void displayMenu() {
        System.out.println("------------------------------------------------");
        System.out.println("List Of Set Menus for ordering party");
        System.out.println("------------------------------------------------");

        List<SetMenu> sortedMenu = setMenus.stream()
                .sorted((menu1, menu2) -> Double.compare(menu1.getPrice(), menu2.getPrice()))
                .collect(Collectors.toList());

        for (SetMenu setMenu : sortedMenu) {
            System.out.println("Code: " + setMenu.getCode());
            System.out.println("Name: " + setMenu.getName());
            System.out.println("Price: " + setMenu.getPrice());
            System.out.println("Ingredients: ");

            for (String ingredient : setMenu.getIngredients()) {
                System.out.println("     " + ingredient);
            }

            System.out.println("------------------------------------------------");
        }
    }
}
