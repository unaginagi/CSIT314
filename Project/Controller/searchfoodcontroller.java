package Controller;

import Entity.foodItem;
import java.sql.SQLException;
import java.util.List;

public class searchfoodcontroller {

    public searchfoodcontroller() {
    }

    public List<foodItem> searchfoodItem(String name){
        foodItem f1 = new foodItem();
        List<foodItem> output = f1.searchfood(0, name, "", 0.0);
        return output;
    }

}
