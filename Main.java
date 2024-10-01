// Restaurant Check Program
// --------------------------------------------------------
// This program allows customers to order items from a
// restaurant's menu and display the orders placed and
// the final check. (Main dishes only, not drinks/desserts)
//
// The restaurant is assumed to only have tables for four.
// --------------------------------------------------------
// Menu Item Info
// item_name, item_ID, num_calories, price, heart_healthy
//
// NOTE: item ID not displayed to user, used internally.
// ........................................................
// The menu should be displayed as follows:
// ........................................................
// MENU
// 1 - Braised Short Ribs (450 cal.) $26.00
// 2 - Baked Salmon (260 cal.) (Heart Healthy) $23.50
// 3 - Scallop Risotto (340 cal.) $22.50
// etc.
// ........................................................
// The check should be displayed as follows:
// ........................................................
// ORDERS
// Baked Salmon $23.50
// Scallop Risotto $22.50
// Braised Short Ribs $26.00
// Braised Short Ribs $26.00
//
// SUBTOTAL (before tax)      $34.00
// TAX (6%)                    $2.04
// TOTAL                      $36.04
//
// RECOMMENDED TIPS
// 10% $3.60                  $39.64
// 15% $5.41                  $41.45
// 20% $7.21                  $43.25
// --------------------------------------------------------
// User Options
// 1 - Display menu
// 2 - Place order
// 3 - Display orders
// 4 - Request check
// 5 - Quit
// --------------------------------------------------------
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
  
  // Global constants
  public final static int MAX_MENU_ITEMS = 10;
  public final static int MAX_NUM_ORDERS = 4;
  public final static double TAX_RATE = 0.06;
  public final static String[] SERVER_NAMES = {
    "Joann", "Katie", "James", "William",
    "Casey", "Olivia", "Asher", "Noah"};

  // Create randon number generator object
  public static Random rand = new Random();

  public static void main(String[] args) {

    // Declare / Init
    int selection;
    boolean valid_input = false;
    boolean quit = false;
    Scanner input = new Scanner(System.in);

    // Create Menu and Orders objects
    Menu menu = new Menu();
    Orders orders = new Orders(MAX_NUM_ORDERS);

    // Populate menu
    populateMenu(menu);
  
    // Welcome message
    System.out.println("\n\nWelcome to Downtown Bistro");

    // Display and execute user options
    while(!quit) {
      valid_input = false;

      while(!valid_input) {
        try {
          displayUserOptions();
          selection = getSelection(7, input);
          valid_input  = true;

          System.out.println();
  
          switch(selection) {
            case 1: displayMenu(menu); break;
            case 2: displayHeartHealthyMenu(menu); break;
            case 3: placeOrder(menu, orders, input); break;
            case 4: displayOrders(menu, orders); break;
            case 5: displayCheck(menu, 
                      new Check(menu, orders, TAX_RATE)); break;
            case 6: cancelOrders(orders); break;
            case 7: quit = true; break;
          
          }
        }
        catch(InputMismatchException e) {
          // Exception handler for invalid (non-digit) characters
          // (thrown by call to getSelection)
          System.out.println("Invalid input - Please re-enter");
          input.nextLine(); // scan past invalid input
        }
        catch(NumberOrdersExceededException e) {
          // Exception handler for exceeded number of orders
          // (thrown by call to placeOrder)
          System.out.println("Number of orders exceeded");
        }
      }
    }
  }

  public static String monetary_format(double amt) {
  // -----------------------------------------------------------
  // Returns amt as string formatted with two decimal places
  // and preceding dollar sign.
  // -----------------------------------------------------------
    return String.format("$%.2f", amt);
  }
  
  public static String pad_left(String str, int width) {
  // -----------------------------------------------------------
  // Returns str right-justified in provided field width,
  // left-padded with spaces.
  // -----------------------------------------------------------
      
    // Create array of chars
    char[] pad_chars = new char[width];
    Arrays.fill(pad_chars, ' ');

    // Convert to String type
    String pad_str = String.valueOf(pad_chars);

    // Return left-padded string
    int begin_index = str.length();
    return (pad_str + str).substring(begin_index);
  }
  
  public static String getServer() {
  // -----------------------------------------------------------
  // Returns random server name.
  // -----------------------------------------------------------
    return SERVER_NAMES[rand.nextInt(SERVER_NAMES.length - 1)];
  }

  public static void populateMenu(Menu menu)
                      throws NumberMenuItemsExceededException{
  // -----------------------------------------------------------
  // Fills menu with hard-coded set of MenuItems.
  //
  // Terminates program with Fatal Error if number of menu
  // items exceeded.
  // -----------------------------------------------------------
    final boolean HEART_HEALTHY = true;
    final boolean NOT_HEART_HEALTHY = false;

    try {
      menu.add(new MenuItem("Braised Short Ribs",
                            "#101", "450", 
                            26.00, NOT_HEART_HEALTHY));

      menu.add(new MenuItem("Baked Salmon", 
                            "#205", "260", 
                            23.50, HEART_HEALTHY));

      menu.add(new MenuItem("Scallop Risotto", "#180", "340", 
                            25.50, NOT_HEART_HEALTHY));

      menu.add(new MenuItem("Herb Chicken", "#310", "280",
                            22.50, HEART_HEALTHY));
    }
    catch (NumberMenuItemsExceededException e) {
      System.out.println(
        "\n* FATAL ERROR: Number of Menu Items Exceeded " +
                          "in Method populateMenu *\n");
      // terminate program
      System.exit(0);
    }
  }

  public static void displayUserOptions() {
  // -----------------------------------------------------------
  // Displays numbered list of user options.
  // -----------------------------------------------------------
    System.out.println("\nOptions");
    System.out.println("  1 - Display Menu");
    System.out.println("  2 - Display Heart Healthy Menu");
    System.out.println("  3 - Place Order");
    System.out.println("  4 - Display Orders");
    System.out.println("  5 - Request Check");
    System.out.println("  6 - Cancel Order");
    System.out.println("  7 - Quit");
  }

  public static int getSelection(int end, Scanner input) 
                              throws InputMismatchException {
  // -----------------------------------------------------------
  // Prompts the user and returns entered integer value in the
  // range 1 .. end (inclusive).
  //
  // Throws InputMismatchException if input contains a non-digit.
  // -----------------------------------------------------------
    int selection = 0;

    do {
      System.out.print(": ");
      selection = input.nextInt(); // throws InputMismatchException
                                      // when non-digit read
      if(selection < 1 || selection > end)
        System.out.println("* Invalid Selection *");
    }
    while(selection < 1 || selection > end);

    return selection;
  }

  public static void displayMenu(Menu menu) {
  // -----------------------------------------------------------
  // Displays numbered list of menu items.
  // -----------------------------------------------------------
    int i = 1;
    
    System.out.println("Menu");
    menu.reset();

    while(menu.hasNext()) {
      System.out.println(i + " - " + menu.next());
      i = i + 1;
    }
  }
  public static void displayHeartHealthyMenu(Menu menu) {
    // -----------------------------------------------------------
    // Displays numbered list of menu items.
    // -----------------------------------------------------------
      int i = 1;
      
      System.out.println("Menu");
      menu.reset();
  
      while(menu.hasNext()) {
      MenuItem menuItem = menu.next();
      if (menuItem.isHeartHealthy()){

        System.out.println(i + " - " + menuItem);
        i = i + 1;
      }
      }
    }

  public static void placeOrder(Menu menu, Orders orders, 
                                Scanner input) 
                        throws NumberOrdersExceededException {
  // -----------------------------------------------------------
  // Prompts for number of menu item to order and adds to orders.
  //
  // Throws NumberOrdersExceededException if number of orders
  // exceeds limit.
  // -----------------------------------------------------------
    int item_num;
    boolean valid_item_num = false;
    MenuItem menu_item;
    String item_ID;

    System.out.println("Placing order ...\n");

    while(!valid_item_num) {
      try {
          displayMenu(menu);
          System.out.print("\nSelect item number");
          
          // Get selection
          // (throws InputMismatchException if non-digit read)
         item_num = getSelection(menu.numItems(), input); 
              
          menu_item = menu.getMenuItemByListing(item_num);
          item_ID = menu_item.getItemID();

          // Add order to collection of orders
          // (throws NumberOrdersExceededException)
          orders.add(item_ID);

          valid_item_num = true;
      }
      catch(InputMismatchException e) {
        System.out.println("* Invalid Input *\n");
        input.nextLine();  // scan past invalid input
      }
    }

    System.out.println("* Order Placed *");
  }

  public static void displayOrders(Menu menu, Orders orders) {
  // -----------------------------------------------------------
  // Displays list of ordered item names and price. (Does not
  // display calories or hearthy healthy designation).
  //
  // If no orders exist, displays "* No Orders Found *".
  // -----------------------------------------------------------
    MenuItem menu_item;
    String menu_item_str;

    // Display orders header
    System.out.println("ORDERS");
      
    // Check if any current orders
    if(orders.numOrders() == 0)
      System.out.println("* No Orders Found *");
    else {
      // Reset to start at first order
      orders.reset();

      // Display description for each menu item ID in orders
      while(orders.hasNext()) {
        menu_item = menu.getMenuItemByID(orders.next());
        menu_item_str = menu_item.getBasicDescription();

        System.out.println(menu_item_str);
      }
    }
  }

  public static void displayTipAndTotal(double total, double tip,
                                        String percent_label, 
                                        int field_width) {
  // -----------------------------------------------------------
  // Displays line containing tip amount and total with tip
  // label within provided field_width,
  //     10%    $5.00                            $57.47
  // -----------------------------------------------------------
    String str1, str;
    String formatted_tip = monetary_format(tip);
    String formatted_total_w_tip = monetary_format(total + tip);

    str1 = percent_label + "%    " + formatted_tip;
    str = str1 + pad_left(formatted_total_w_tip, 
                          field_width - str1.length() + 1);

    System.out.println(str);
  }

  public static void displayCheck(Menu menu, Check check) {
  // -----------------------------------------------------------
  // Displays check including order items, subtotal (without tax)
  // total (with tax), and suggested tip percentages.
  //
  // If no orders exist, displays "* No Orders Found *"
  // -----------------------------------------------------------
    String div = "---------------------------" +
                 "---------------------------";
    int field_width = div.length();

    // Display check heading
    System.out.println(div);

    String heading = 
      "Check" + pad_left("Server: " + getServer(), 
                          field_width - 5);

    System.out.println(heading);
    System.out.println(div);

    // Check for exsiting orders
    if(check.getOrders().numOrders() == 0)
      System.out.println("* No Orders Found *");
    else {
      // Display orders
      displayOrders(menu, check.getOrders());

      // Display subtotal, tax and total
      String str;
      int tally_field_width = 46;
  
      str = "\nSUBTOTAL(before tax)  " +
            pad_left(monetary_format(check.subtotal()),
                      tally_field_width - 21);     

      System.out.println(str);

      str = "TAX (" + (int)(check.getTaxRate() * 100) + "%" + ")" +
            pad_left(monetary_format(check.tax()), 
                    tally_field_width - 7);   

      System.out.println(str);
        
      str = "TOTAL " + 
            pad_left(monetary_format(check.total()), 
                    tally_field_width - 5);   
      
      System.out.println(str);

      // Display recommended tips
      System.out.println("\nRECOMMENDED TIP");
      
      // 10% tip line
      displayTipAndTotal(check.total(), check.compute_tip(10),
                        "10%", tally_field_width);

      // 15% tip line
      displayTipAndTotal(check.total(), check.compute_tip(15),
                        "15%", tally_field_width);

      // 20% tip line
      displayTipAndTotal(check.total(), check.compute_tip(20),
                        "20%", tally_field_width);

      System.out.println(div);
    }
  }
  public static void cancelOrders(Orders orders){
    orders.cancel();
  }
}