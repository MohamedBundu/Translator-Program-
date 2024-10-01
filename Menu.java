public class Menu {
  
  private MenuItem[] menu_items;
  
  private int current_index = 0; // to only be used by
                                 // iterator methods below
  // Constructor
  public Menu() {
  // -----------------------------------------------------------
  // Creates and initializes menu_items array to size 10 with
  // all nulls.
  // -----------------------------------------------------------
  // (to complete)
  menu_items = new MenuItem[12];
  menu_items[0] = new MenuItem("Braised Short Ribs", "101", "450", 26.00, false);
  menu_items[1] = new MenuItem("Baked Salmon", "102", "260", 23.50, true);
  menu_items[2] = new MenuItem("Grilled Chicken", "103", "300", 20.00, true);
  menu_items[3] = new MenuItem("Fettuccine Alfredo", "104", "800", 25.00, false);
  menu_items[4] = new MenuItem("Caesar Salad", "105", "350", 12.50, true);
  menu_items[5] = new MenuItem("BBQ Ribs", "106", "700", 28.00, false);
  menu_items[6] = new MenuItem("Vegetable Lasagna", "107", "750", 22.00, true);
  menu_items[7] = new MenuItem("Penne Primavera", "108", "600", 20.00, true);
  menu_items[8] = new MenuItem("Grilled Veggie Burger", "109", "400", 15.00, true);
  menu_items[9] = new MenuItem("Tiramisu", "110", "350", 7.00, true);
  menu_items[10] = new MenuItem("Chocolate Mousse", "111", "250", 6.00, false);
  menu_items[11] = new MenuItem("Cheese Plate", "112", "500", 8.00, true);
  }

  // Method
  public int numItems() {
  // -----------------------------------------------------------
  // Returns the current number of items in the menu.
  // -----------------------------------------------------------
  // (to complete)
int count = 0;
for(int i = 0; i < menu_items.length; i++) {
  if(menu_items[i] != null) {
    count++;
  }
}
return count;
  }

  // Method
  public void add(MenuItem menu_item) {
  // -----------------------------------------------------------
  // Adds menu item to next available space in array menu_items.
  //
  // Throws NumberMenuItemsExceededException if menu_items array
  // is full.
  // -----------------------------------------------------------
  // (to complete)
  if (numItems() == menu_items.length) {
   new NumberMenuItemsExceededException();
  }
  for(int i = 0; i < menu_items.length; i++) {
    if (menu_items[i] == null) {
      menu_items[i] = menu_item;
      break;
    }
  }
  }

  // Method
  public MenuItem getMenuItemByListing(int n) throws MenuItemNotFoundException {
  // -----------------------------------------------------------
  // Returns nth MenuItem (object) from the listed menu items.
  //
  // Throws MenuItemNotFoundException if n is outside the
  // range of 1..numItems().
  // -----------------------------------------------------------
  // (to complete)
    MenuItem found_item = null;
    if (numItems() - 1 < menu_items.length) {
      found_item = menu_items[n];
      } else {
        throw new MenuItemNotFoundException();
      }
    return found_item;
  }

  // Method
  public MenuItem getMenuItemByID(String item_ID) 
                            throws MenuItemNotFoundException {
  // -----------------------------------------------------------
  // Returns MenuItem (object) for given item_ID.
  //
  // Throws MenuItemNotFoundException if menu item with item_ID
  // not found.
  // -----------------------------------------------------------
  // (to complete)
  if (item_ID == null || item_ID.isEmpty()) {
   new MenuItemNotFoundException();
  }
  for (int i = 0; i < menu_items.length; i++) {
    if(menu_items[i] != null && menu_items[i].getItemID().equals(item_ID)) {
      return menu_items[i];
    }
  }
  return null;
}
  

  // -----------------------------------------------------------
  // Iterator Methods
  // -----------------------------------------------------------
  // These methods are used to iterate over the menu items.
  // Items may be iterated over multiple times by calling reset
  // to begin at the first menu item again.
  //
  // NOTE: These methods use instance variable current_index
  //       which no other methods should use.
  // -----------------------------------------------------------
  
  public void reset() {
  // -----------------------------------------------------------
  // Resets to first order of list of menu items.
  // -----------------------------------------------------------
  // (to complete)
  current_index = 0;
  }

  public boolean hasNext() {
  // -----------------------------------------------------------
  // Returns true if there exists another menu item to retrieve.
  // Otherwise, returns false.
  // -----------------------------------------------------------
  // (to complete)
  for(int i = current_index; i < menu_items.length; i++) {
    if(menu_items[i] != null) {
      return true;
    }
  }
  return false;
  }
  
public MenuItem[] getOrders() {
  MenuItem[] orders = new MenuItem[numItems()];
  int index = 0;
  for(MenuItem item: menu_items) {
    if(item != null) {
      orders[index] = item;
      index++;
    }
  }
  return orders;
}

  public MenuItem next() {
  // -----------------------------------------------------------
  // Returns next menu item in list of menu items.
  // (Must only be called when hasNext() is true.)
  // -----------------------------------------------------------
  // (to complete)
  for (int i = current_index; i < menu_items.length; i++) {
    if (menu_items[i] != null) {
      MenuItem menuItem = menu_items[i];
      current_index++;
      return menuItem;
    }
  }
  return null;
}

  public void updateMenu(int options, int option) {
    System.out.println("Menu: ");
    for(MenuItem item: menu_items) {
      if(item == null) {
        break;
      }
      if (option == 1 || option == 2) {
        System.out.println(item.toString());
      }
      if(option == 2) {
        if(item.isHeartHealthy()) {
          System.out.println(item.getBasicDescription());
        }
      }
      if(option == 3) {
        if(option == 3 != item.isHeartHealthy()) {
          continue;
        }
      }
    }
  }
}