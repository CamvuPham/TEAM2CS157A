
import java.sql.Timestamp;
import java.util.ArrayList;

public class Controller {
   
   StoreModel model = new StoreModel(null, null);
    
    public void createUser(String username, String password, String email) {
        model.createUser(username, password, email);
    }
    

    public void editUser(String username, String password, String email, int isEmployee, int uID) {
        model.editUser(username,  password,  email,  isEmployee,  uID);
    }
    

    public void getUser(String username, String password) {
        model.getUser(username,password);
    }
    

    public void makeOrder(int uID, int price) {
        model.makeOrder( uID,  price);
    }
    

    public void editOrder(int oID, int uID, int price) {
        model.editOrder( oID,  uID,  price);
    }


    public void addFruit(String name, int price) {
        model.addFruit( name,  price);
    }
    

    public void getListOfFruit() {
        model.getListOfFruit();
    }
    
    // ...
 
}
