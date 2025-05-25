package Services.Implements;

import Model.SetMenu;
import Services.Interfaces.IMenuService;
import Utils.FileUltils;

import java.util.List;
import java.util.stream.Collectors;

public class MenuService implements IMenuService {

    List<SetMenu> setMenus;

    private final String menuFilePath = "src/resources/FeastMenu.csv";

    public MenuService(){
        setMenus= FileUltils.loadSetMenusFromFile(menuFilePath);
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
            System.out.println("Price: " + (int)setMenu.getPrice());
            System.out.println("Ingredients: ");

            for (String ingredient : setMenu.getIngredients()) {
                System.out.println("     " + ingredient);
            }

            System.out.println("------------------------------------------------");
        }
    }



}
