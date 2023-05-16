package foodItem;

import java.sql.SQLException;

public class retrievefoodcontroller {
    public retrievefoodcontroller() {
            
    }

    public foodItem retrievefoodItem (int id) throws SQLException, ClassNotFoundException
    {
        foodItem f1 = new foodItem ();
        foodItem output = f1.getFood (id);
        return output;
    }
}


