package org.example;
import java.util.ArrayList;
import java.util.List;

// import org.example.Supo1;
import org.example.Supo2.Question9.*;

public class Main {
    public static void main(String[] args) {

        // implemented a shop where we have milk, a manager of a hr department and a shop assistant.
        // printed the experience of the manager, and added a discount, then printed whether there is one or not and the new price

        // prints "junior", "1.89", "true", "0.00188..."
        
        Shop sainsburys = new Shop("Sainsbury's", "27 Eddington Avenue, Cambridge, CB3 1SE");
        sainsburys.addDepartment(new Department("HR", sainsburys));
        sainsburys.addDepartment(new Department("customer service", sainsburys));
        List<Shop> shops = new ArrayList<Shop>();
        shops.add(sainsburys);
        sainsburys.addItem(new Item("milk", 1.89F, false, shops));
        sainsburys.getDepartments().get(0).setManager(new Manager("Baylee Neal", "hr manager", 100000, sainsburys.getDepartments().get(0), "junior"));
        sainsburys.getDepartments().get(0).addAssistant(new Assistant("Faustina Das", "cashier", 2000, sainsburys.getDepartments().get(1)));

        System.out.println(sainsburys.getDepartments().get(0).getManager().getExperience()); ;
        System.out.println(sainsburys.getItems().get(0).getValue());

        sainsburys.getItems().get(0).addDiscount(0.999F);
        System.out.println(sainsburys.getItems().get(0).getDiscount());
        System.out.println(sainsburys.getItems().get(0).getValue());

    }
}