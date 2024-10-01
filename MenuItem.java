import java.util.HashMap;
import java.util.Map;
public class MenuItem {
    private static final Map<String, MenuItem> items = new HashMap<>();

    private String item_name;
    private String item_ID;
    private String num_calories;
    private double price;
    private boolean heart_healthy;
  
    // Constructor 
    public MenuItem (String item_name, String item_ID, 
                     String num_calories, double price, 
                     boolean heart_healthy) {
    // ------------------------------------------------------
    // Initializes instance vars to parameter values passed.
    // ------------------------------------------------------
    // 
    items.put(item_ID, this);
    this.item_name = item_name;
    this.item_ID = item_ID;
    this.num_calories = num_calories;
    this.price = price;
    this.heart_healthy = heart_healthy;
    }
  
    // Getters
  
    public String getItemID() {
    // ------------------------------------------------------
    // Returns value of instance var Item_ID.
    // ------------------------------------------------------
    // 
    return item_ID;
    }
    public static MenuItem getByid(String id) {
        return items.get(id);
        }
    public double getPrice() {
    // ------------------------------------------------------
    // Returns value of instance var price.
    // ------------------------------------------------------  
    //
    return price;
    }
  
    public boolean isHeartHealthy() {
    // ------------------------------------------------------
    // Returns value of instance var heart_healthy.
    // ------------------------------------------------------  
    return heart_healthy;
    }
  
    public String getBasicDescription() {
    // ------------------------------------------------------
    // Returns description containing only the item name
    // and the price, e.g., "Baked Salmon $23.50".
    // ------------------------------------------------------
    // 
    return item_name + " $" + price;
    }
  
    // toString method
    
    public String toString() {
    // ------------------------------------------------------
    // Returns string of the form:
    //
    // "Braised Short Ribs (450 cal.) $26.00"
    // "Baked Salmon (260 cal.) $23.50 (Heart Healthy)"
    //
    // Note that item_ID NOT part of the returned string.
    // (It is only used internally)
    // ------------------------------------------------------
    //
    String heartHealthy = " ";
    if(heart_healthy) {
      heartHealthy = " (Heart Healthy)";
    }
    return item_name + " (" + num_calories + " cal.) $" + price + heartHealthy;
    }
    
  
    public static void main(String[] args) {
      MenuItem[] menuItems = new MenuItem[6];
      menuItems[0] = new MenuItem("Braised Short Ribs", "101", "450", 26.00, false);
      menuItems[1] = new MenuItem("Baked Salmon", "102", "260", 23.50, true);
      menuItems[2] = new MenuItem("Grilled Chicken", "103", "300", 20.00, true);
      menuItems[3] = new MenuItem("Fettuccine Alfredo", "104", "800", 25.00, false);
      menuItems[4] = new MenuItem("Caesar Salad", "105", "350", 12.50, true);
      menuItems[5] = new MenuItem("BBQ Ribs", "106", "700", 28.00, false);
      for (MenuItem item : menuItems) {
        System.out.println(item.toString());
      }
    }
  }